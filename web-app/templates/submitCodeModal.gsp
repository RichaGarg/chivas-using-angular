<div class="modal-body register-popup clearfix popup" ng-controller="SubmitCodeCtrl">
    <button type="button" class="close" id="closeUniqueCodePopUpCross" ng-click="closeUniqueCodePopUp();"><span
            aria-hidden="true"></span></button>
    <span class="text">To register, please provide the following information:</span>
    <form name="userData" class="form" ng-submit="userData.$valid && submitCode()" noValidate>
        <ul>
            <span ng-show="userData.uniqueCode.$touched && userData.uniqueCode.$error.required" class="errorMsg">Please enter Unique Code..!</span>
            %{--<span ng-show="userData.uniqueCode.$error.validUniqueCode" class="errorMsg">Please enter valid Unique Code..!</span>--}%
            <li class="clearfix">
                <div class="label">
                    <label>
                        Your Unique Code
                    </label>
                </div>
                <input type="text" name="uniqueCode" placeholder="" class="textbox" ng-model="uCode.uniqueCode" required>
                %{--<input type="text" name="uniqueCode" placeholder="" class="textbox" ng-model="uCode.uniqueCode" valid-unique-code>--}%
            </li>

            <span ng-show="userData.barName.$touched && userData.barName.$error.required" class="errorMsg">Please enter Bar Name..!</span>
            <li class="clearfix">
                <div class="label">
                    <label>
                        Name of Bar{{uCode.accepted}}
                    </label>
                </div>
                <input type="text" name="barName" placeholder="" class="textbox" ng-model="uCode.barName" required>
            </li>

            <span ng-show="userData.name.$touched && userData.name.$error.required" class="errorMsg">Please enter your Name..!</span>
            <li class="clearfix">
                <div class="label">
                    <label>
                        Your name
                    </label>
                </div>
                <input type="text" name="name" placeholder="" class="textbox" ng-model="uCode.name" required>
            </li>

            <span ng-show="userData.nric.$touched && userData.nric.$error.required" class="errorMsg">Please enter NRIC..!</span>
            <span ng-show="userData.nric.$error.validNric" class="errorMsg">Please enter valid NRIC..!</span>
            <li class="clearfix">
                <div class="label">
                    <label>
                        Your IC number
                    </label>
                </div>
                <input type="text" name="nric" placeholder="" class="textbox" ng-model="uCode.nric" valid-nric required>
            </li>

            <span ng-show="userData.phoneNumber.$touched && userData.phoneNumber.$error.required" class="errorMsg">Please enter your Mobile Number..!</span>
            <span ng-show="userData.phoneNumber.$error.pattern" class="errorMsg">Phone Number contains only numeric digits and must starts with [6,8,9]..!</span>
            <li class="clearfix">
                <div class="label">
                    <label>
                        Your mobile number
                    </label>
                </div>
                <input type="text" name="phoneNumber" placeholder="" class="textbox" ng-model="uCode.phoneNumber" required ng-pattern="/^[689]\d{7}$/">
            </li>

            <span ng-show="userData.email.$touched && userData.email.$error.required" class="errorMsg">Please enter EmailId..!</span>
            <span ng-show="userData.email.$error.email" class="errorMsg">Please enter valid Email Id..!</span>
            <li class="clearfix">
                <div class="label">
                    <label>
                        Your email address
                    </label>
                </div>
                <input type="email" name="email" placeholder="" class="textbox" ng-model="uCode.email" required>
            </li>

            <li class="clearfix alC">
                <span class="tc">
                    <input type="checkbox" name="tc" style="display: inline" ng-model="accepted" required>
                    I agree to
                    <a href="javascript:void(0);" ng-click="showTermsAndConditionsPopUp()">
                        Terms & Conditions
                    </a>
                    and
                    <a href="javascript:void(0);" ng-click="showPrivacyPolicyPopUp()">
                        Privacy Policy.
                    </a>
                </span>
            </li>

            <li class="clearfix alC">
                <input type="submit" value="SUBMIT">
                <span class="spinner" id="submitCodeSpinner" style="display: none">
                    <img class="loading" src="${asset.assetPath(src: 'spinner.gif')}" alt="Processing.."/>
                </span>
            </li>

        </ul>
    </form>
</div>
