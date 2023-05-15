import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {

            let locationsRequest = new XMLHttpRequest();
            locationsRequest.addEventListener("click", function () {

                if(locationsRequest.status === 200) {

                    let locationsContainerElement = document.createElement("div");
                    locationsContainerElement.classList.add("inner-color");
                    locationsContainerElement.classList.add("rounded-corners");



                }

            });

            locationsRequest.open("GET", `${urlPath}/owner/locations`);
            locationsRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            locationsRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
