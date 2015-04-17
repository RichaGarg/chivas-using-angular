package com.chivas.common

class Prize {

    String productName
    Integer quantity
    Date dateCreated
    Date lastUpdated

    static constraints = {
        productName blank: false
        quantity blank: false
    }
}
