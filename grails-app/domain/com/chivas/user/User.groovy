package com.chivas.user

class User {

    String name
    String nric
    Date dateCreated
    Date lastUpdated
    String facebookId

    static hasMany = [registerCoupons: RegisterCoupon]

    static constraints = {
        name blank: false
        nric blank: false, unique: true
        facebookId nullable: true
    }

    static mapping = {
        table("coupon_user")
    }
}
