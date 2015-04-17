package com.chivas.common

class Coupon {

    String couponCode
    Prize prize
    boolean redeemed = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        couponCode unique: true, blank: false
        prize nullable: true
    }
}
