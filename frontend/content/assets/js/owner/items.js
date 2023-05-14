import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {

            let itemsRequest = new XMLHttpRequest();
            itemsRequest.addEventListener("load", function() {

            });

            itemsRequest.open("GET", `${urlPath}/owner/items`)
            itemsRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
