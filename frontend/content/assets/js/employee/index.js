import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";
import {drawOrder} from "../shared/component/order.js";

(function () {

    let headerElement = document.getElementById('dynamicContent');

    function completeOrder(orderId) {

        let orderCompletionHttpRequest = new XMLHttpRequest();
        orderCompletionHttpRequest.addEventListener("load", function () {

            if (orderCompletionHttpRequest.status === 200) {

                let response = JSON.parse(orderCompletionHttpRequest.response);
                refreshDisplay();

                if (response.successMessage) {
                    alert(response.successMessage);
                } else if (response.errorMessage) {
                    alert(response.errorMessage);
                }

            }

        });

        orderCompletionHttpRequest.open("PATCH", `${urlPath}/employee/orders/${orderId}`);
        orderCompletionHttpRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
        orderCompletionHttpRequest.send();

    }

    function refreshDisplay() {

        headerElement.innerHTML = "";

        if (Cookies.get('jwtToken')) {

            let purchasesContainerElement = document.createElement("div");
            purchasesContainerElement.classList.add("inner-color");
            purchasesContainerElement.classList.add("rounded-corners");

            let ordersRequest = new XMLHttpRequest();
            ordersRequest.addEventListener("load", function () {

                if (ordersRequest.status === 200) {

                    let jsonResponse = JSON.parse(ordersRequest.response);

                    jsonResponse.purchases.forEach(order => {

                        let orderElement = drawOrder(order);

                        orderElement.addEventListener("click", function () {
                            if (confirm("Would you like to complete this order?") === true) {
                                completeOrder(orderElement.getAttribute("data-order"));
                            }
                        });

                        purchasesContainerElement.appendChild(orderElement);

                    });

                    headerElement.appendChild(purchasesContainerElement);

                } else {
                    alert("The request was refused by the server: " + ordersRequest.response);
                }

            });

            ordersRequest.open("GET", `${urlPath}/employee/orders/open`);
            ordersRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            ordersRequest.send();

            headerElement.appendChild(purchasesContainerElement);

        } else {

            userNotAuthenticated(headerElement);

        }

    }

    if (checkCookieConsent() === true) {

        refreshDisplay();

        setInterval(function () {
            refreshDisplay();
        }, 30000);

    }

}());
