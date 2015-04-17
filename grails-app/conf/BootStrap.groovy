import grails.util.Environment

class BootStrap {

    def bootstrapService

    def init = { servletContext ->
        if (Environment.currentEnvironment.equals(Environment.DEVELOPMENT)) {
            bootstrapService.generatePrizes()
            bootstrapService.generateCouponsForDevEnvironment()
        }
    }

    def destroy = {
    }
}
