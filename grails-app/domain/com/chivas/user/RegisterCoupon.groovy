package com.chivas.user

import com.chivas.common.Coupon

class RegisterCoupon {

    String email
    String phoneNumber
    Coupon coupon
    String barName
    Integer totalNoOfChance = 1
    Date dateCreated
    Date lastUpdated

    static hasMany = [facebookShares: FacebookShare]

    static belongsTo = [user: User]

    static constraints = {
        email email: true, blank: false
        phoneNumber blank: false
        barName blank: false
    }
}
