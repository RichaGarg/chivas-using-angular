package com.chivas.common

import com.chivas.admin.SmsInfo
import com.chivas.user.RegisterCoupon
import com.chivas.util.Constant
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional
class LogSMSStatusService {

    def asynchronousMailService

    String mailIds

    def logResponse(RegisterCoupon registerCoupon, JSONObject jsonObject) {
        log.info "Got response from sms service for ${registerCoupon?.coupon?.couponCode} as ${jsonObject}"
        String status = jsonObject.get('status')
        if (status) {
            saveSmsStatusInfo(registerCoupon, status)
        } else {
            String errMsg = "User having register coupon id ${registerCoupon.id}, SMS not sent. Request fail. "
            unableToSendSMS(errMsg)
        }
    }

    def saveSmsStatusInfo(RegisterCoupon registerCoupon, String statusCode) {
        SmsInfo.withTransaction {
            registerCoupon = RegisterCoupon.get(registerCoupon.id)
            SmsInfo smsInfo = new SmsInfo(statusCode: statusCode, registerCoupon: registerCoupon)
            smsInfo.save(failOnError: true, flush: true)
            // send mail only msg not sent
            if (!(statusCode == Constant.SMS_SUCCESS_CODE)) {
                String errMsg = "User having coupon $registerCoupon.coupon.couponCode, SMS not sent. Returned status code is $statusCode"
                unableToSendSMS(errMsg)
            }
        }
    }

    def unableToSendSMS(String msg) {
        List<String> recipients = mailIds.split(',').collect { it.trim() }
        asynchronousMailService.sendMail {
            to recipients
            subject 'Unable to send SMS';
            html "<body>$msg</body>";
        }
    }
}
