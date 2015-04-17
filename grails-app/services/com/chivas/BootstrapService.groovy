package com.chivas

import com.chivas.common.Coupon
import com.chivas.common.Prize
import com.chivas.util.ImportCsvCodeFile

class BootstrapService {

    def grailsApplication

    def generatePrizes() {
        if (!Prize.count()) {
            new Prize(productName: '75cl', quantity: 1).save(flush: true, failOnError: true)
            new Prize(productName: '5cl', quantity: 1).save(flush: true, failOnError: true)
        }
    }

    def generateCoupons() {
        if (!Coupon.count()) {
            ImportCsvCodeFile.importFile()
        }
    }

    def generateCouponsForDevEnvironment() {
        if (!Coupon.count()) {
            ImportCsvCodeFile.importFileOnDevelopmentEnvironment()
        }
    }

}
