package com.chivas.util

class UtilController {

    def bootstrapService

    def populateCoupons() {
        bootstrapService.generateCoupons()
        render "done"
    }

    def populationPrizes() {
        bootstrapService.generatePrizes()
        render "done"
    }

}
