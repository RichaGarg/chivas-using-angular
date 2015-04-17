package com.chivas.common

import com.chivas.user.RegisterCoupon
import grails.async.Promise
import grails.transaction.Transactional
//import groovyx.net.http.HTTPBuilder
//import groovyx.net.http.Method
import org.codehaus.groovy.grails.web.json.JSONObject

import static grails.async.Promises.task

@Transactional
class MessageApiService {

    def logSMSStatusService
    def sessionFactory

    String appId
    String accessToken

    def sendSuccessMsg(Long registerCouponId, String message) {
        RegisterCoupon registerCoupon = getRegisterCouponFromId(registerCouponId)
        Promise p = task {
            String url = 'https://secure.hoiio.com/open/sms/send?dest=+65' + registerCoupon.phoneNumber + '&msg=' + URLEncoder.encode(message, 'UTF-8') + '&access_token=' + accessToken + '&app_id=' + appId + '&sender_name=ChivasSG'
            JSONObject jsonObject = null

            /*HTTPBuilder http = new HTTPBuilder(url)
            http.request(Method.GET) {
                response.success = { resp, json ->
                    jsonObject = json
                }
            }*/
            if (jsonObject) {
                logSMSStatusService.logResponse(registerCoupon, jsonObject)
            }
            return jsonObject
        }

        p.onError { Throwable err ->
            String errMsg = "on error :: User having register coupon id $registerCouponId, SMS not sent. Request fail.${err.getMessage()}"
            logSMSStatusService.unableToSendSMS(errMsg)
        }

        p.onComplete { result ->
            if (!result) {
                String errMsg = "User having register coupon id $registerCouponId, SMS not sent. Request fail. "
                logSMSStatusService.unableToSendSMS(errMsg)
            }
        }
        p.get()
    }

    RegisterCoupon getRegisterCouponFromId(Long registerCouponId) {
        return RegisterCoupon.get(registerCouponId)
    }
}
