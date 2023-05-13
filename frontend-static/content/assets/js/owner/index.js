import '../include/js.cookie.min.js';
import {userNotAuthorized} from '../shared/user-not-authorized.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {


        } else {
            userNotAuthorized(headerElement);
        }

    }

}());
