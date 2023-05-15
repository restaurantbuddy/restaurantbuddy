import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";
import {drawUser} from "../shared/component/user.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {

            let usersRequest = new XMLHttpRequest();
            usersRequest.addEventListener("load", function() {

                if(usersRequest.status === 200) {

                    let userContainerElement = document.createElement("div");
                    userContainerElement.classList.add("inner-color");
                    userContainerElement.classList.add("rounded-corners");

                    let jsonResponse = JSON.parse(usersRequest.response);
                    jsonResponse.users.forEach(user => {
                        userContainerElement.appendChild(drawUser(user));
                    });

                    headerElement.appendChild(userContainerElement);

                }

            });

            usersRequest.open("GET", `${urlPath}/owner/users`);
            usersRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            usersRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
