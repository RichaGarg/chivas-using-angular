<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description"
          content="Buy a bottle of Chivas 18 and you and 9 friends could be jetting off for a weekend of relaxation, partying and indulgence – all expenses paid, where the glorious Pulau Joyo awaits you. "/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Win your own private island for a luxury weekend</title>
    <meta name="layout" content="main"/>
    <style>
    .page:nth-child(2n) {
        background-color: darkblue !important;
    }


    </style>
</head>

<body class="main">
<fullpage class="instant-size" page-class="page">
    <section class="page" id="page1">
        <section class="container">
            <span ng-controller='AgeGatewayCtrl'>
                <age-gateway></age-gateway>
            </span>
        </section>
    </section>
    <section class="page" id="page2">
        <section class="container">
            <span ng-controller='SubmitCodeCtrl'>
                <submit-unique-code></submit-unique-code>
            </span>
        </section>
    </section>
    <section class="page" id="page3">
        <section class="container">
            <span ng-controller='FullDetailsCtrl'>
                <full-details></full-details>
            </span>
        </section>
    </section>
    <section class="page" id="page4">
        <section class="container">
            <span ng-controller='IslandDetailsCtrl'>
                <island-detail></island-detail>
            </span>
        </section>
    </section>
    <section class="page" id="page5">
        <section class="container">
            <span ng-controller='YoutubeVideoCtrl'>
                <drop-yt></drop-yt>
            </span>
        </section>
    </section>
</fullpage>
</body>
</html>