<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>


    <meta property="og:url"
          content="${grailsApplication.config.grails.serverURL}"/>
    <meta property="og:site_name"
          content="Chivas Staging"/>
    <meta property="fb:app_id"
          content="${grailsApplication.config.facebook.appId}"/>
    <meta property="og:title"
          content="Win your own private island for a luxury weekend"/>
    <meta property="og:description"
          content="Buy a bottle of Chivas 18 and you and 9 friends could be jetting off for a weekend of relaxation, partying and indulgence – all expenses paid, where the glorious Pulau Joyo awaits you. "/>
    <meta property="og:image"
          content="${grailsApplication.config.grails.serverURL}${asset.assetPath(src: 'chivas-fb-share-img.jpg')}"/>
    <meta property="og:type" content="website"/>

    <meta charset="utf-8">
    <meta name="description"
          content="Buy a bottle of Chivas 18 and you and 9 friends could be jetting off for a weekend of relaxation, partying and indulgence – all expenses paid, where the glorious Pulau Joyo awaits you. "/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Win your own private island for a luxury weekend</title>
    <style>
    #tc-error {
        display: none !important;
    }

    .help-block-error {
        display: block;
        padding-top: 3px;
        font-size: 12px;
        padding-left: 135px;
        color: #ff0000;
        font-weight: bold;
    }
    </style>
    <g:if env="production">
        <g:render template="googleAnalytics"
                  model="[trackingId: grailsApplication.config.google.analytics.tracking.id]"/>
        <asset:javascript src="user/googleAnalyticsFunction.js"/>
    </g:if>
    <g:else>
        <script type="text/javascript">
            function googleAnalyticsSave(msg) {
                return true;
            }
        </script>
    </g:else>
</head>

<body>
<div class="main" id="userApp">
    <section class="section-1">
        <div class="container clearfix">
            <img src="${asset.assetPath(src: 'logo.png')}" class="desktopNone logo"
                 srcset="${asset.assetPath(src: 'logo@2x.png')} 2x" alt="Chivas Logo">

            <div class="col">
                <p>
                    Chivas 18 is our most awarded blend.
                    It’s a uniquely rich and multi-layered whisky that includes over twenty of Scotland’s rarest single malt Scotch whiskies.
                </p>
            </div>

            <div class="center-col">
                <div class="inner">
                    <img src="${asset.assetPath(src: 'main-bottle.png')}" srcset="${asset.assetPath(src: 'main-bottle-mobile.png')} 2x"  alt="Main Chivas Bottle"/>
                </div>

            </div>

            <div class="col-right">
                <p>
                    With 85 flavour notes in every drop, each sip is a new discovery.
                </p>
                <img src="${asset.assetPath(src: 'logo.png')}" srcset="${asset.assetPath(src: 'logo@2x.png')} 2x"
                     class="mobileNone" alt="Chivas Logo">
            </div>
            <a href="javascript:void(0)" class="down-arrow" id="downToSection2" onclick="moveDown();">
                &nbsp;
            </a>
        </div>
    </section>

    <section class="section-2">
        <div class="container clearfix">
            <a href="javascript:void(0)" class="up-arrow" id="upToSection1" onclick="moveUp()">
                &nbsp;
            </a>

            <div class="win-msg">
                WIN your OWN<br/>
                <strong>
                    private<br/>
                    island
                </strong>
                for a luxury<br/>
                <span>
                    weekend.
                </span>
            </div>

            <div class="image-container">
                <img src="${asset.assetPath(src: 'island.png')}" alt="Island Round Image">
            </div>

            <div class="button">
                <a href="javascript:void(0);" class="common-btn" id="enterUniqueCodeButton" onclick="openUniqueCodePopUp();">
                    Enter unique code
                </a>
            </div>

            <a href="javascript:void(0);" id="checkIslandLink" class="checkout" onclick="moveDown();">
                Check out the island
            </a>

            <a href="javascript:void(0);" class="down-arrow" id="downToSection3" onclick="moveDown();">
                &nbsp;
            </a>
        </div>
    </section>

    <section class="section-3">
        <div class="container">
            <a href="javascript:void(0)" class="up-arrow" id="upToSection2" onclick="moveUp()">
                &nbsp;
            </a>

            <p>
                Buy a bottle of Chivas 18 and you and 9 friends could
                <br>
                be jetting off for a weekend of relaxation, partying and indulgence – all expenses paid.
            </p>
            <a href="javascript:void(0);" class="common-btn" id="fullDetailsButton" onclick="showContentDetailsPopUp()">
                full details
            </a>
            <a href="javascript:void(0);" class="down-arrow" id="downToSection4" onclick="moveDown();">
                &nbsp;
            </a>
        </div>
    </section>

    <section class="section-4">
        <div class="container">
            <a href="javascript:void(0)" class="up-arrow" id="upToSection3" onclick="moveUp()">
                &nbsp;
            </a>

            <p>
                When is a paradise island more than a paradise island? When you’ve got it all to yourself and    <br>
                9 of your very best friends! Check out the glorious Pulau Joyo and the activities that await you.
            </p>

            <div id="wrapper_bu">
                <div id="bu1"><img src="${asset.assetPath(src: 'image6.jpg')}" alt="Island Image 6"></div>

                <div id="bu2"><img src="${asset.assetPath(src: 'image7.jpg')}" alt="Island Image 7"></div>

                <div id="bu3"><img src="${asset.assetPath(src: 'image8.jpg')}" alt="Island Image 8"></div>

                <div id="bu4"><img src="${asset.assetPath(src: 'image9.jpg')}" alt="Island Image 9"></div>

                <div id="bu5"><img src="${asset.assetPath(src: 'image1.jpg')}" alt="Island Image 1"></div>

                <div id="bu6"><img src="${asset.assetPath(src: 'image2.jpg')}" alt="Island Image 2"></div>

                <div id="bu7"><img src="${asset.assetPath(src: 'image3.jpg')}" alt="Island Image 3"></div>

                <div id="bu8"><img src="${asset.assetPath(src: 'image4.jpg')}" alt="Island Image 4"></div>

                <div id="bu9"><img src="${asset.assetPath(src: 'image5.jpg')}" alt="Island Image 5"></div>

            </div>

            <div class="controls">
                <a href="javascript:void(0);" id="carouselLeftImage" class="previous"></a>
                <a href="javascript:void(0);" id="carouselRightImage" class="next"></a>
            </div>
            <a href="javascript:void(0);" class="common-btn" id="seeIslandDetailsButton" onclick="showIslandDetailsPopUp()">
                see the island
            </a>
            <a href="javascript:void(0);" class="down-arrow" id="downToSection5" onclick="moveDown();">
                &nbsp;
            </a>
        </div>
    </section>

    <section class="section-5">
        <div class="container">
            <div class="right-col">
                <a href="javascript:void(0)" class="up-arrow" id="upToSection4" onclick="moveUp()">
                    &nbsp;
                </a>

                <p class="highlighted">
                    At Chivas Regal, we are always looking for new ways to give you the ultimate drinking experience.
                    Our recent partnership with Pininfarina to create ‘the drop’ is an example of two brands working in perfect harmony.
                </p>

                <div class="forMobile"><span class="title">
                    Discover the drop
                </span>

                    <p>
                        Born of a partnership between craftsmen of excellence, The Drop is a work of race-car-quality streamlined engineering.
                        Now available in an exclusive selection of the finest bars and restaurants in Singapore,
                        The Drop takes 90 seconds to deliver a single ice-cube uniquely designed to bring out the best in every glass of Chivas Regal 18.
                    </p>
                    <a href="javascript:void(0);" id="seeInActionYTVideoButton" class="common-btn" onclick="showVideoPopUp()">
                        See it in action
                    </a>

                    <p>
                        To savour the difference yourself, simply visit one of
                        <a href="javascript:void(0);" id="openEstablishmentLink" onclick="showEstablishmentDetailsPopUp()">
                            these establishments.
                        </a>
                    </p></div>

            </div>
        </div>
    </section>
</div>

<asset:javascript src="user/home-file.js"/>

<script>
    Conclave.auto();
    window.fbAsyncInit = function () {
        FB.init({
            appId: "${grailsApplication.config.facebook.appId}",
            xfbml: true,
            version: 'v2.1'
        });
    };
    // Load the SDK Asynchronously
    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
</body>
</html>
