import '../include/js.cookie.min.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";

(function () {

    if (checkCookieConsent() === true) {
        Cookies.remove('jwtToken');
    }

}());
