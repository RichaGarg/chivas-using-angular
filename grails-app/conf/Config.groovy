// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = ChivasAngular // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
                      all          : '*/*', // 'all' maps to '*' or the first available format in withFormat
                      atom         : 'application/atom+xml',
                      css          : 'text/css',
                      csv          : 'text/csv',
                      form         : 'application/x-www-form-urlencoded',
                      html         : ['text/html', 'application/xhtml+xml'],
                      js           : 'text/javascript',
                      json         : ['application/json', 'text/json'],
                      multipartForm: 'multipart/form-data',
                      rss          : 'application/rss+xml',
                      text         : 'text/plain',
                      hal          : ['application/hal+json', 'application/hal+xml'],
                      xml          : ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://www.example.com"

        grails {
            mail {
                host = "smtp.gmail.com"
                port = 465
                username = "chivas.staging@gmail.com"
                password = "igdefault"
                props = ["mail.smtp.auth"                  : "true",
                         "mail.smtp.socketFactory.port"    : "465",
                         "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
                         "mail.smtp.socketFactory.fallback": "false"]
            }
        }
        web {
            facebook {
                appId = '655971917858143'
                secretKey = "bc9136ca33328fdc5a67d7a7da3d1faf"
                baseUrl = 'https://graph.facebook.com/v2.3/'
                uri {
                    api = "https://graph.facebook.com/me"
                    graph = "https://graph.facebook.com/"
                    auth = "https://www.facebook.com/v2.3/dialog/oauth"
                    callback = "/facebook/callback"
                }
                scope = "email,publish_actions,read_stream"
            }
        }
    }
}
qa {
    grails.serverURL = "http://ts-chivas.qa3.intelligrape.net"
    grails.logging.jul.usebridge = false
    grails.plugin.console.enabled = true
    grails.dbconsole.enabled = true
    facebook.appId = "1557174327899853"
    grails {
        mail {
            host = "smtp.gmail.com"
            port = 465
            username = "chivas.staging@gmail.com"
            password = "igdefault"
            props = ["mail.smtp.auth"                  : "true",
                     "mail.smtp.socketFactory.port"    : "465",
                     "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
                     "mail.smtp.socketFactory.fallback": "false"]
        }
    }

    grails.assets.bundle = true
    grails.assets.minifyJs = true
    grails.assets.minifyCss = true
    grails.assets.minifyOptions = [
            strictSemicolons: false,
            mangleOptions   : [mangle: true, toplevel: false, defines: null, except: null, no_functions: false],
            genOptions      : [indent_start: 0, indent_level: 4, quote_keys: false, space_colon: false, beautify: false, ascii_only: false, inline_script: false]
    ]
}
production {
    grails.logging.jul.usebridge = false
    grails.serverURL = System.getenv('SERVER_URL')
    grails.plugin.console.enabled = true
    grails.dbconsole.enabled = true
    grails.plugin.springsecurity.controllerAnnotations.staticRules = [
            "/console/**"         : ['ROLE_ADMIN'],
            "/plugins/console*/**": ['ROLE_ADMIN']
    ]
    facebook {
        appId = '1560554877527593'
    }
    grails {
        mail {
            host = "smtp.gmail.com"
            port = 465
            username = "chivas.staging@gmail.com"
            password = "igdefault"
            props = ["mail.smtp.auth"                  : "true",
                     "mail.smtp.socketFactory.port"    : "465",
                     "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
                     "mail.smtp.socketFactory.fallback": "false"]
        }
    }
    google.analytics.tracking.id = 'UA-60729231-1'
    grails.assets.minifyCss = false
    grails.assets.minifyJs = false
//        grails.assets.excludes = ["**/*.eot", "**/*.otf", "**/*.svg", "**/*.ttf", "**/*.woff"]
//        grails.assets.url = "http://d21h87busxkco1.cloudfront.net/assets/"
//        cors.headers = [
//                'Access-Control-Allow-Origin': '*',
//                ]
}
log4j = {

    // Example of changing the log pattern for the default console appender:
    //
    def catalinaBase = System.properties.getProperty('catalina.base')
    if (!catalinaBase) catalinaBase = '.'   // just in case
    def logDirectory = "${catalinaBase}/logs"
    environments {
        production {
            appenders {
                rollingFile name: 'allLogs', maxFileSize: '50MB', maxBackupIndex: 100, file: "${logDirectory}/${applicationName}_app.log", {
                    layout:
                    pattern(
                            conversionPattern: '%%d{yyyy-MM-dd HH:mm:ss.SSS} | %p | %c | %t | %m | %x%n'
                    )
                }
                rollingFile name: 'chivas', maxFileSize: '50MB', maxBackupIndex: 100, file: "${logDirectory}/${applicationName}_Chivas_app.log", {
                    layout:
                    pattern(
                            conversionPattern: '%%d{yyyy-MM-dd HH:mm:ss.SSS} | %p | %c | %t | %m | %x%n'
                    )
                }
                console name: 'stdout', layout: pattern(conversionPattern: '%-5p %d{dd-MM-yyyy HH:mm:ss} -> %m%n')
            }

            error allLogs: ['org.codehaus.groovy.grails.web.servlet',  //  controllers
                            'org.codehaus.groovy.grails.web.pages', //  GSP
                            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
                            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                            'org.codehaus.groovy.grails.web.mapping', // URL mapping
                            'org.codehaus.groovy.grails.commons', // core / classloading
                            'org.codehaus.groovy.grails.plugins', // plugins
                            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
                            'org.springframework',
                            'org.hibernate',
                            'net.sf.ehcache.hibernate'
            ]

            debug allLogs: ['grails.app',
                            'grails.app.filters',
                            'com.chivas.filter'
            ]

            warn allLogs: ['org.mortbay.log']

            debug mediaIQ: ['com.chivas', 'org.springframework.security.acls']
        }
        development {
            appenders {
                console name: 'stdout', layout: pattern(conversionPattern: '%-5p %d{dd-MM-yyyy HH:mm:ss} -> %m%n')
            }
            debug console: ['com.chivas', 'org.springframework.security.acls']
        }
    }
}



grails.plugin.console.enabled = true


beans {
    logSMSStatusService {
        mailIds = 'richag@intelligrape.com,puneet@intelligrape.com,kartikey@intelligrape.com'
    }

    messageApiService {
        appId = System.getenv('HOIIO_APP_ID') ?: "R6gK79bGnnFiro49"
        accessToken = System.getenv('HOIIO_ACCESS_TOKEN') ?: "BEoAxww8qjd8vcl2"
    }
}
