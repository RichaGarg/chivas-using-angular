package com.chivas.user.command

import com.chivas.common.Coupon
import com.chivas.user.RegisterCoupon
import com.chivas.user.User
import grails.validation.Validateable

@Validateable
class CouponSubmitCO {

    String uniqueCode
    String barName
    String name
    String nric
    String phoneNumber
    String email

    static constraints = {
        uniqueCode nullable: false, blank: false, validator: { val, obj ->
            Coupon coupon = Coupon.findByCouponCode(val)
            if (!coupon) {
                return "validator.invalid"
            } else if (RegisterCoupon.countByCoupon(coupon)) {
                RegisterCoupon registerCoupon = RegisterCoupon.findByCouponAndUser(coupon, User.findByNric(obj.nric))
                if (registerCoupon && registerCoupon?.totalNoOfChance == 1) {
                    return "validator.already.registered.not.shared"
                } else {
                    return "validator.already.registered"
                }
            } else if (coupon.redeemed) {
                return "validator.already.redeemed"
            }
        }
        barName nullable: false, blank: false
        name nullable: false, blank: false
        nric nullable: false, blank: false
        phoneNumber nullable: false, blank: false, validator: { val, obj ->
            if (!val.toString().matches(/^[689]\d{7}$/)) {
                return 'validator.pattern'
            } else if (val.toString().length() != 8) {
                return 'validator.length'
            }
        }
        email nullable: false, blank: false, email: true
    }

}
