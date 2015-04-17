<script>
    var submitCodeUrl = "${createLink(controller: 'user',action: 'submitCode')}";
    var checkRedeemCodeUrl = "${createLink(controller: 'outlet',action: 'checkCodeValidity')}";
    var redeemCodeUrl = "${createLink(controller: 'outlet',action: 'redeemCode')}";
    var registerUserOnFacebook = "${createLink(controller: 'user',action: 'fbLogin')}";
</script>