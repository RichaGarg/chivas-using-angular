package com.chivas.common

import com.chivas.user.FacebookShare
import com.chivas.user.RegisterCoupon
import com.chivas.user.User
import com.chivas.user.command.CookieVO
import com.chivas.user.command.CouponSubmitCO
import com.chivas.user.command.FacebookShareCO
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONArray

@Transactional
class CouponService {

    def grailsApplication
    def messageApiService
    def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
    def sessionFactory

    def couponRegistration(CouponSubmitCO couponSubmitCO) {
        RegisterCoupon registerCoupon = registerCouponCode(couponSubmitCO)
        String msg = ""
        String smsMsg = ""
        if (registerCoupon.coupon?.prize) {
            Prize prize = registerCoupon.coupon.prize
            Object[] args1 = [prize.productName] as Object[]
            msg = getMessage("couponSubmit.success.prize.won", args1)
            Object[] args2 = ["\"${registerCoupon.coupon.couponCode}\"", prize.productName] as Object[]
            smsMsg = getMessage("couponSubmit.success.prize.won.message", args2)
        } else {
            msg = getMessage("couponSubmit.success.register", null)
            Object[] args = [registerCoupon.coupon.couponCode] as Object[]
            smsMsg = getMessage("couponSubmit.success.register.message", args)
        }
        cleanUpGorm()
        return [msg: msg, smsMsg: smsMsg, registerCouponId: registerCoupon.id]
    }

    RegisterCoupon registerCouponCode(CouponSubmitCO couponSubmitCO) {
        Coupon coupon = getCoupon(couponSubmitCO.uniqueCode)
        User user = getUserByNric(couponSubmitCO.nric)
        if (!user) {
            user = createUser(couponSubmitCO)
        }
        RegisterCoupon registerCoupon = registerCode(user, coupon, couponSubmitCO)
        registerCoupon.save(failOnError: true, flush: true)
        return registerCoupon
    }

    void sendSms(String msg, Long registerCouponId) {
        messageApiService.sendSuccessMsg(registerCouponId, msg)
    }

    Coupon getCoupon(String uniqueCode) {
        return Coupon.findByCouponCode(uniqueCode)
    }

    User getUserByNric(String nric) {
        return User.findByNric(nric)
    }

    User createUser(CouponSubmitCO couponSubmitCO) {
        return new User(nric: couponSubmitCO.nric, name: couponSubmitCO.name).save(failOnError: true, flush: true)
    }

    RegisterCoupon registerCode(User user, Coupon coupon, CouponSubmitCO couponSubmitCO) {
        return new RegisterCoupon(user: user, coupon: coupon, barName: couponSubmitCO.barName, email: couponSubmitCO.email, phoneNumber: couponSubmitCO.phoneNumber)
    }

    private def getMessage(String code, Object[] args) {
        def appCtx = grailsApplication.getMainContext()
        return appCtx.getMessage(code, args, "default message", Locale.ENGLISH)
    }

    boolean saveFacebookShareInformation(FacebookShareCO facebookShareCO) {
        boolean success = false
        User user = getUserByNric(facebookShareCO.nric)
        Coupon coupon = getCoupon(facebookShareCO.coupon)
        RegisterCoupon registerCoupon = RegisterCoupon.findByCouponAndUser(coupon, user)
        if (!user.facebookId) {
            user.facebookId = facebookShareCO.facebookId
            user.save(flush: true, failOnError: true)
        }
        if (facebookShareCO.tagList) {
            JSONArray listOfFacebookFriends = JSON.parse(facebookShareCO.tagList)
            listOfFacebookFriends.each { facebookFriend ->
                FacebookShare facebookShare = FacebookShare.findOrCreateByFacebookIdAndRegisterCoupon(facebookFriend.id as String, registerCoupon)
                facebookShare.name = facebookFriend.name
                facebookShare.save(flush: true, failOnError: true)
            }
            Integer shareCount = registerCoupon?.facebookShares?.size()
            log.info "Share count is" + shareCount
            if (listOfFacebookFriends.size() >= 5 || shareCount >= 5) {
                registerCoupon.totalNoOfChance = 2
                registerCoupon.save(flush: true, failOnError: true)
                success = true
            }
        }
        return success
    }

    def isCodeValid(String uniqueCode) {
        Coupon coupon = Coupon.findByCouponCode(uniqueCode)
        String msg;
        boolean success = false;
        if (!coupon) {
            msg = getMessage("couponSubmitCO.uniqueCode.validator.invalid", null)
        } else if (RegisterCoupon.countByCoupon(coupon)) {
            RegisterCoupon registerCoupon = RegisterCoupon.findByCouponAndUser(coupon, User.findByNric(obj.nric))
            if (registerCoupon && registerCoupon?.totalNoOfChance == 1) {
                msg = getMessage("couponSubmitCO.uniqueCode.validator.already.registered.not.shared", null)
            } else {
                msg = getMessage("couponSubmitCO.uniqueCode.validator.already.registered", null)
            }
        } else if (coupon.redeemed) {
            msg = getMessage("couponSubmitCO.uniqueCode.validator.already.redeemed", null)
        } else
            success = true
        return [msg: msg,success: success]
    }


    public void cleanUpGorm() {
        def session = sessionFactory.currentSession
        session.flush()
        session.clear()
        propertyInstanceMap.get().clear()
    }

    Map processCookie(String cookieVal) {
        Map cookieValMap = [:]
        List<String> cookieArray = cookieVal.split("&")
        cookieArray.eachWithIndex { elem, idx ->
            List<String> cookieElements = elem.split('=')
            cookieValMap.put(cookieElements[0], cookieElements[1])
        }
        return cookieValMap
    }

    CookieVO fetchCookieVO(Map cookieValMap) {
        CookieVO cookieVO = new CookieVO(barName: cookieValMap['barName'],
                name: cookieValMap['name'],
                nric: cookieValMap['nric'],
                phoneNumber: cookieValMap['phoneNumber'],
                email: cookieValMap['email'])
        return cookieVO
    }
}

