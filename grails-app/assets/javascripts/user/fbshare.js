function showSubmitCodePopUp() {
    openJqueryPopUp('#submitCodeMsg');
}

function closeSubmitCodePopUp() {
    closeJqueryPopUp('#submitCodeMsg');
}

function showOptionToShareOnFb() {
    $('.share-fb').show();
}

function closeOptionToShareOnFb() {
    $('.share-fb').hide();
}

function shareOnFacebook(serverUrl) {
    console.log('inside fbshare function')
    showFbShareSpinner();
    testLogin(serverUrl);
}

function showFbShareSpinner() {
    jQuery('#fbShareSpinner').show();
}

function hideFbShareSpinner() {
    jQuery('#fbShareSpinner').hide();
}

var userId = '';

function testLogin(serverUrl) {
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            // the user is logged in and has authenticated your
            // app, and response.authResponse supplies
            // the user's ID, a valid access token, a signed
            // request, and the time the access token
            // and signed request each expire
            var uid = response.authResponse.userID;
            userId = uid;
            var accessToken = response.authResponse.accessToken;
            share(serverUrl);
        } else if (response.status === 'not_authorized') {
            login(serverUrl);
            // the user is logged in to Facebook,
            // but has not authenticated your app
        } else {
            login(serverUrl);
            // the user isn't logged in to Facebook.
        }
    });
}

function login(serverUrl) {
    FB.login(function (response) {
        if (response.authResponse) {
            userId = response.authResponse.userID;
            share(serverUrl)
        } else {
            window.location.reload();
        }
    }, {scope: 'publish_actions,read_stream'});
}

function share(serverUrl) {
    FB.ui({
        method: 'share',
        href: serverUrl
    }, function (response) {
        if (!response.error_code) {
            var postID = response.post_id;
            readPost(postID, userId)
        } else {
            hideFbShareSpinner();
            googleAnalyticsSave("User did not post on Facebook");
        }
    });
}

function readPost(postID, userId) {
    FB.api(
        "/" + postID,
        function (response) {
            if (response && !response.error) {
                var tags = response.tags;
                saveFriendsTaggedInThePost(tags, userId);
            }
        }
    );
}

function saveFriendsTaggedInThePost(tags, userId) {
    var nric = $('#nricVal').val();
    var coupon = $('#couponVal').val();
    if (tags) {
        var tagList = JSON.stringify(tags);
        $.ajax({
            url: registerUserOnFacebook,
            type: 'POST',
            dataType: 'json',
            data: {tagList: tagList, facebookId: userId, nric: nric, coupon: coupon},
            success: function (data) {
                if (data.productionEnv == true) {
                    if(data.success == true)
                        googleAnalyticsSave("Post Shared with Facebook friends");
                    else
                        googleAnalyticsSave("Post Shared but not with 5 friends");
                }
                hideFbShareSpinner();
                closeSubmitCodePopUp();
            }
        });
    }
    else
        googleAnalyticsSave("User did not share on Facebook with 5 friends");

}

jQuery(document).on("chivas.share", function (e, data) {
    share();
});