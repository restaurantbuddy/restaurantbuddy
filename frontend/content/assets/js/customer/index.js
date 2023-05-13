import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById("dynamicContent");
        let request = new XMLHttpRequest();

        if (Cookies.get('jwtToken')) {

            request.addEventListener("load", function () {

                if (request.status === 200) {

                    let jsonResponse = JSON.parse(request.response);
                    let items = jsonResponse.items;

                    let menuContainerElement = document.createElement("div");
                    menuContainerElement.classList.add("inner-color");
                    menuContainerElement.classList.add("rounded-corners");

                    items.forEach(item => {

                        let itemElement = document.createElement("div");
                        itemElement.classList.add("innermost-color");
                        itemElement.classList.add("rounded-corners");

                        let itemTitleElement = document.createElement("p");
                        let itemTitleContent = document.createTextNode(item.name);
                        itemTitleElement.appendChild(itemTitleContent);

                        let itemDescriptionElement = document.createElement("p");
                        let itemDescriptionContent = document.createTextNode(item.description);
                        itemDescriptionElement.appendChild(itemDescriptionContent);

                        let itemPriceElement = document.createElement("p");
                        let itemPriceContent = document.createTextNode(item.price);
                        itemPriceElement.appendChild(itemPriceContent);

                        itemElement.appendChild(itemTitleElement);
                        itemElement.appendChild(itemDescriptionElement);
                        itemElement.appendChild(itemPriceElement);

                        menuContainerElement.appendChild(itemElement);

                    });

                    headerElement.appendChild(menuContainerElement);

                }

            });

            request.open("GET", `${urlPath}/customer/items`);
            request.setRequestHeader("Authorization", "Bearer " + Cookies.get("jwtToken"));
            request.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
