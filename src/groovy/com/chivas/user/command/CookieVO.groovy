package com.chivas.user.command

class CookieVO {

    String barName
    String name
    String nric
    String phoneNumber
    String email

    String getBarName() {
        return barName?.replace('+', ' ')
    }

    String getName() {
        return name?.replace('+', ' ')
    }

    String getEmail() {
        return email?.replace('%40', '@')
    }
}
