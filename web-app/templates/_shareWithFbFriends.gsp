<div class="share-fb" ng-hide="showDiv.fbShare">
    <span class="title">
        double your chances:
    </span>

    <p class="text">
        Tell five friends about the competition and you get a second entry for yourself.
    </p>
    <a href="javascript:void(0)" ng-click="shareOnFacebook('http://www.example.com')">
        <img src="${asset.assetPath(src: 'fb-btn.png')}" alt="Share On Facebook">
    </a>
    <span class="spinner" ng-show="showDiv.fbSpinner">
        <img class="loading" src="${asset.assetPath(src: 'spinner.gif')}" alt="Processing.."/>
    </span>
</div>