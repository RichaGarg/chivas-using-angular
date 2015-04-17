package com.chivas.util

class Utils {

    static String getIpAddress(def request) {
        String ipListAsString = request.getHeader("x-forwarded-for") ?: request.remoteAddr
        ipListAsString?.split(",")?.collect { it.trim() }?.first()
    }
}
