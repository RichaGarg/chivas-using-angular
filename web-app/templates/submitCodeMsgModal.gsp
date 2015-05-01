<div id="fb-root"></div>
<div class="cong-msg-popup clearfix popup">
    <div class="inner">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                ng-click="closeSubmitCodeMsgPopUp()"><span
                aria-hidden="true"></span></button>

        <span class="title cong-msg-title">
            CONGRATUlations!
        </span>

        <p ng-model="requestMessage" class="text">
            hoiiiiiiiii
        </p>
        <g:render template="shareWithFbFriends" model="[fbAppId: '655971917858143', serverUrl: 'http://www.example.com']"/>
    </div>
</div>