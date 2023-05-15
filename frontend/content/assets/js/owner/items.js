import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";
import {drawItem} from "../shared/component/item.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        if (Cookies.get('jwtToken')) {

            let itemsRequest = new XMLHttpRequest();
            itemsRequest.addEventListener("load", function () {

                if (itemsRequest.status === 200) {

                    let response = JSON.parse(itemsRequest.response);

                    let menuContainerElement = document.createElement("div");
                    menuContainerElement.classList.add("inner-color");
                    menuContainerElement.classList.add("rounded-corners");

                    response.items.forEach(item => {

                        let itemElement = drawItem(item.id, item.name, item.description, item.price);
                        menuContainerElement.appendChild(itemElement);

                    });

                    headerElement.appendChild(menuContainerElement);

                }

            });

            itemsRequest.open("GET", `${urlPath}/owner/items`)
            itemsRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            itemsRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
