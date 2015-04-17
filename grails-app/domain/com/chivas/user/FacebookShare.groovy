package com.chivas.user

class FacebookShare {

    String name
    String facebookId
    Date dateCreated
    Date lastUpdated

    static belongsTo = [registerCoupon: RegisterCoupon]

    static constraints = {

    }
}
