package com.chivas.user

import com.chivas.user.command.CouponSubmitCO
import com.chivas.user.command.FacebookShareCO
import grails.converters.JSON
import grails.util.Environment
import org.springframework.validation.FieldError

class UserController {

    def couponService

    def index() {

    }

    def submitCode(CouponSubmitCO couponSubmitCO) {
        println(couponSubmitCO.dump())
        String msg = ''
//        boolean isSharedOnFb = true
        Map responseMap = [:]
        /*if (couponSubmitCO.hasErrors()) {
            for (FieldError error : couponSubmitCO.errors.fieldErrors)
                msg = msg + "<br/>" + g.message(error: error)
            if (msg.equals("<br/>This unique bottle code is already used. Share on Facebook to increase your chances of winning!")) {
//                isSharedOnFb = false
            }
            responseMap = [msg: msg, success: false, shared: isSharedOnFb]
        } else {
            Map data = couponService.couponRegistration(couponSubmitCO)
            couponService.sendSms(data.smsMsg, data.registerCouponId)
            responseMap = [msg: data.msg, success: true, nric: couponSubmitCO.nric, coupon: couponSubmitCO.uniqueCode, registerCouponId: data.registerCouponId]
        }*/
        responseMap = [success: true]
        render(responseMap as JSON)
    }
/*
    def fbLogin(FacebookShareCO facebookShareCO) {
        boolean productionEnv = false
        if (Environment.current == Environment.PRODUCTION)
            productionEnv = true
        boolean successShare = couponService.saveFacebookShareInformation(facebookShareCO)
        render([success: successShare, productionEnv: productionEnv] as JSON)
    }

    def facebookShare() {
        println params
    }*/

    def isUniqueCodeValid() {
        println(params.dump())
        boolean success = false
        String msg
        if(params.ucode) {
            Map data = couponService.isCodeValid(params.ucode)
            success = data.success
            msg = data.msg
        }

        render([success: success, msg: msg] as JSON)
    }
}
