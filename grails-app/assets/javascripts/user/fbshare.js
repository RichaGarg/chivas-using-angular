var userId = '';

function testLogin(serverUrl) {
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            var uid = response.authResponse.userID;
            userId = uid;
            var accessToken = response.authResponse.accessToken;
            share(serverUrl);
        } else if (response.status === 'not_authorized') {
            login(serverUrl);
        } else {
            login(serverUrl);
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
            url: '/user/fbLogin',
            type: 'POST',
            dataType: 'json',
            data: {tagList: tagList, facebookId: userId, nric: nric, coupon: coupon},
            success: function (data) {
            }
        });
    }
}
