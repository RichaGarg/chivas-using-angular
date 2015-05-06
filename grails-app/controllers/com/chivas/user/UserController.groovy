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
        String msg = ''
        boolean isSharedOnFb = false
        Map responseMap = [:]
        if (couponSubmitCO.hasErrors()) {
            for (FieldError error : couponSubmitCO.errors.fieldErrors)
                msg = msg + "\n" + g.message(error: error)
            if (msg.contains("\nThis unique bottle code is already used. Please try another unique code!")) {
                isSharedOnFb = true
            }
            responseMap = [msg: msg, success: false, shared: isSharedOnFb]
        } else {
            Map data = couponService.couponRegistration(couponSubmitCO)
            responseMap = [msg: data.msg, success: true, nric: couponSubmitCO.nric, coupon: couponSubmitCO.uniqueCode, registerCouponId: data.registerCouponId, shared: isSharedOnFb]
        }
        render(responseMap as JSON)
    }

    def fbLogin(FacebookShareCO facebookShareCO) {
        boolean successShare = couponService.saveFacebookShareInformation(facebookShareCO)
        render([success: successShare] as JSON)
    }

    def isUniqueCodeValid() {
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
