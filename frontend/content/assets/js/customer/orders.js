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

                if (orderRequest.status === 200) {

                    let jsonResponse = JSON.parse(orderRequest.response);
                    let purchases = jsonResponse.purchases;

                    let purchasesContainerElement = document.createElement("div");
                    purchasesContainerElement.classList.add("inner-color");
                    purchasesContainerElement.classList.add("rounded-corners");

                    purchases.forEach(purchase => {

                        let purchaseElement = document.createElement("div");
                        purchaseElement.id = purchase.id;
                        purchaseElement.classList.add("innermost-color");
                        purchaseElement.classList.add("rounded-corners");

                        let purchaseIdElement = document.createElement("p");
                        let purchaseIdElementContent = document.createTextNode(purchase.id);
                        purchaseIdElement.appendChild(purchaseIdElementContent);

                        let purchaseTimePlacedElement = document.createElement("p");
                        let purchaseTimePlacedElementContent = document.createTextNode(purchase.timePlaced);
                        purchaseTimePlacedElement.appendChild(purchaseTimePlacedElementContent);

                        let purchaseTimeCompletedElement = document.createElement("p");
                        let purchaseTimeCompletedElementContent = document.createTextNode(purchase.timeCompleted);
                        purchaseTimeCompletedElement.appendChild(purchaseTimeCompletedElementContent);

                        let purchaseCustomerUsernameElement = document.createElement("p");
                        let purchaseCustomerUsernameElementContent = document.createTextNode(purchase.customerUsername);
                        purchaseCustomerUsernameElement.appendChild(purchaseCustomerUsernameElementContent);

                        let purchaseItemsElement = document.createElement("div");

                        purchase.items.forEach(item => {

                            let purchaseItemElement = document.createElement("div");
                            purchaseItemElement.id = item.id;

                            let purchaseItemTitleElement = document.createElement("p");
                            let purchaseItemTitleContent = document.createTextNode(item.name);
                            purchaseItemTitleElement.appendChild(purchaseItemTitleContent);

                            let purchaseItemDescriptionElement = document.createElement("p");
                            let purchaseItemDescriptionContent = document.createTextNode(item.description);
                            purchaseItemDescriptionElement.appendChild(purchaseItemDescriptionContent);

                            let purchaseItemPriceElement = document.createElement("p");
                            let purchaseItemPriceContent = document.createTextNode("$" + item.price);
                            purchaseItemPriceElement.appendChild(purchaseItemPriceContent);

                            purchaseItemElement.appendChild(purchaseItemTitleElement);
                            purchaseItemElement.appendChild(purchaseItemDescriptionElement);
                            purchaseItemElement.appendChild(purchaseItemPriceElement);

                            purchaseItemsElement.appendChild(purchaseItemElement);

                        });

                        purchaseElement.appendChild(purchaseIdElement);
                        purchaseElement.appendChild(purchaseTimePlacedElement);
                        purchaseElement.appendChild(purchaseTimeCompletedElement);
                        purchaseElement.appendChild(purchaseCustomerUsernameElement);
                        purchaseElement.appendChild(purchaseItemsElement);

                        purchasesContainerElement.appendChild(purchaseElement);

                    });

                    headerElement.appendChild(purchasesContainerElement);

                }

            });

            orderRequest.open("GET", `${urlPath}/customer/orders`);
            orderRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            orderRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
