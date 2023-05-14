import "../include/js.cookie.min.js";
import {userNotAuthenticated} from "../shared/user-not-authenticated.js";
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";
import {drawOrder} from "../shared/component/order.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById("dynamicContent");

        if (Cookies.get("jwtToken")) {

            let orderRequest = new XMLHttpRequest();
            orderRequest.addEventListener("load", function () {

                if (orderRequest.status === 200) {

                    let jsonResponse = JSON.parse(orderRequest.response);
                    let purchases = jsonResponse.purchases;

                    let purchasesContainerElement = document.createElement("div");
                    purchasesContainerElement.classList.add("inner-color");
                    purchasesContainerElement.classList.add("rounded-corners");

                    purchases.forEach(purchase => {

                        purchasesContainerElement.appendChild(drawOrder(purchase));

                    });

                    headerElement.appendChild(purchasesContainerElement);

                }

            });

            orderRequest.open("GET", `${urlPath}/customer/orders`);
            orderRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get("jwtToken")}`);
            orderRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
