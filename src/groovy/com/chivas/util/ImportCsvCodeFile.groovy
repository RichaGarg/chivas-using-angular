package com.chivas.util

import com.chivas.common.Coupon
import com.chivas.common.Prize
import grails.util.Holders
import org.apache.commons.io.IOUtils
import org.springframework.web.context.support.ServletContextResource

class ImportCsvCodeFile {


    static void importFileOnDevelopmentEnvironment() {
        def grailsApplication = Holders.getGrailsApplication()
        ServletContextResource resource = grailsApplication.mainContext.getResource("/20150211_Random_codes_for_bottle_stickers.csv")
        File file = resource.file
        int i = 0
        Prize prize75cl = Prize.findByProductName('75cl')
        Prize prize5cl = Prize.findByProductName('5cl')
        file.splitEachLine(',') { row ->
            if (i == 0) {
                i++
            } else {
                i = Integer.parseInt(row[0])
                if (i <= 1450)
                    new Coupon(couponCode: row[1], prize: prize75cl).save(flush: true, failOnError: true)
                else if (i >= 1451 && i <= 4300)
                    new Coupon(couponCode: row[1], prize: prize5cl).save(flush: true, failOnError: true)
                else
                    new Coupon(couponCode: row[1]).save(flush: true, failOnError: true)
            }
        }
    }

    static void importFile() {
        String filePath = "/20150211_Random_codes_for_bottle_stickers.csv"
        String fileContent = readFileFromResource(filePath)
        int i = 0
        Prize prize75cl = Prize.findByProductName('75cl')
        Prize prize5cl = Prize.findByProductName('5cl')
        fileContent.split('\n').eachWithIndex { line, idx ->
            if (idx > 0) {
                List<String> content = line.split(',')
                i = Integer.parseInt(content[0])
                if (i <= 1450) {
                    new Coupon(couponCode: content[1], prize: prize75cl).save(flush: true, failOnError: true)
                } else if (i >= 1451 && i <= 4300) {
                    new Coupon(couponCode: content[1], prize: prize5cl).save(flush: true, failOnError: true)
                } else {
                    new Coupon(couponCode: content[1]).save(flush: true, failOnError: true)
                }
            }
        }
    }

    public static String readFileFromResource(String path) {
        InputStream is = Holders.servletContext.getResourceAsStream(path)
        return IOUtils.toString(is, "utf8")
    }
}
