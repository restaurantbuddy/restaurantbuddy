import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {

            let orderRequest = new XMLHttpRequest();
            orderRequest.addEventListener("load", function () {


            });

            orderRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            orderRequest.open("GET", `${urlPath}/customer/orders`);

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
