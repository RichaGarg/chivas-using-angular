package com.chivas.user.command

import com.chivas.common.Coupon
import grails.validation.Validateable

@Validateable
class RedeemCodeCO {

    String uniqueCode

    static constraints = {
        uniqueCode nullable: false, blank: false, validator: { val, obj ->
            Coupon coupon = Coupon.findByCouponCode(val)
            if (!coupon) {
                return "validator.invalid"
            }
        }
    }
}
