package com.chivas.user

import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.http.HttpResponse
import org.apache.http.StatusLine
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet

class FacebookController {

    def grailsApplication

    String graphBaseUrl
    String redirectUrl
    String facebook_key
    String facebook_secret
    String serverUrl

    def fbLogin() {
        graphBaseUrl = grailsApplication.config.web.facebook.uri.graph
        facebook_key = grailsApplication.config.web.facebook.appId
        facebook_secret = grailsApplication.config.web.facebook.secretKey
        serverUrl = grailsApplication.config.grails.serverURL
        redirectUrl = URLEncoder.encode(serverUrl + grailsApplication.config.web.facebook.uri.callback, 'UTF-8') + "&scope=" + URLEncoder.encode(grailsApplication.config.web.facebook.scope, 'UTF-8') + "&client_id=" + grailsApplication.config.web.facebook.appId
        String url = grailsApplication.config.web.facebook.uri.auth + "?display=popup&redirect_uri=" + redirectUrl
        println('url-- ' + url)
        redirect(url: url.toURL())
    }

    def callback() {
        println('inside cALLBACK=-------')
        if (params.code) {
            println('code-- ' + params.code)
            def rootUrl = graphBaseUrl + "oauth/access_token?client_id=${facebook_key}&client_secret=${facebook_secret}"
            String accessTokenURL = rootUrl + "&redirect_uri=${redirectUrl}&code=${params.code.encodeAsURL()}"
            String result = new URL(accessTokenURL).getText()
            String accessToken = result.tokenize("&")[0].tokenize("=")[1]
            String longAccessToken = getLongTermToken(accessToken)
//            String userInfo = getUserInfo(longAccessToken)
//            Map map = JSON.parse(userInfo) as Map
            def responseObject
            def http = new HTTPBuilder("https://www.facebook.com/dialog/share")
            http.request(Method.POST) {
                requestContentType = 'application/x-www-form-urlencoded'
                body = [app_id      : facebook_key ,
                        display     : "popup",
                        href        : serverUrl ,
                        redirect_uri:  URLEncoder.encode(serverUrl + grailsApplication.config.web.facebook.uri.callback, 'UTF-8')]

                response.success = { resp, json ->
                    responseObject = json
                }
                response.failure = { resp, reder ->
                    println "Failure get access token from refresh token Response is " + reder + " --- " + resp
                }
            }
            println('fb response-- ' + responseObject)
            println('success login')
            render([success: true] as JSON)
        }
    }

    String getLongTermToken(String accessToken) {
        String longTokenUrl = graphBaseUrl + "oauth/access_token" +
                "?grant_type=fb_exchange_token" +
                "&client_id=${facebook_key}" +
                "&client_secret=${facebook_secret}" +
                "&fb_exchange_token=${accessToken}"
        String longToken = new URL(longTokenUrl).getText()
        String longAccessToken = longToken.tokenize("&")[0].tokenize("=")[1]
        return longAccessToken
    }

    String getUserInfo(String userToken) {
        HttpClient httpClient = null
        String response = "{}";
            String url = graphBaseUrl + 'me?access_token=' + userToken
            httpClient = HttpClientBuilder.create().build()
            HttpGet httpget = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpget);
            StatusLine statusLine = httpResponse.statusLine
            int statusCode = statusLine?.statusCode
            if (statusCode != 200) {
                throw new Exception("Non 200 response status received for ${url}. " +
                        "Status received is ${statusCode} with reason ${statusLine?.reasonPhrase}")
            }
            response = httpResponse.getEntity().content.text

        return response
    }

    String getGrantedPermissions(String longAccessToken) {
        String permissionsUrl = graphBaseUrl + "v2.2/me/permissions" +
                "?access_token=${longAccessToken}" +
                "&client_id=${facebook_key}" +
                "&client_secret=${facebook_secret}" +
                "&redirect_uri=${URLEncoder.encode(redirectUrl, 'UTF-8')}"
        String permissions = new URL(permissionsUrl).getText()
        Map permissionsMap = JSON.parse(permissions) as Map
        List<String> permissionGrantedList = []
        String grantedPermissions
        permissionsMap.data.each {
            String permissionGranted = it.status
            if (permissionGranted.equals('granted'))
                permissionGrantedList.add(it.permission)
            grantedPermissions = permissionGrantedList.join(',')
        }
        return grantedPermissions
    }


}
