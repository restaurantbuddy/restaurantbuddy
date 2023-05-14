import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {userNotAuthorized} from "../shared/user-not-authorized.js";
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';
import {drawItem} from "../shared/component/item.js";

(function () {

    function addItemToCart(itemId) {

        if (!Cookies.get('cart')) {

            let cartItems = [itemId];
            Cookies.set('cart', JSON.stringify(cartItems));

        } else {

            let cartItems = JSON.parse(Cookies.get('cart'));
            cartItems.push(itemId);

            Cookies.set('cart', JSON.stringify(cartItems));

        }

    }

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById("dynamicContent");

        if (Cookies.get('jwtToken')) {

            let request = new XMLHttpRequest();
            request.addEventListener("load", function () {

                if (request.status === 200) {

                    let jsonResponse = JSON.parse(request.response);
                    let items = jsonResponse.items;

                    let menuContainerElement = document.createElement("div");
                    menuContainerElement.classList.add("inner-color");
                    menuContainerElement.classList.add("rounded-corners");

                    items.forEach(item => {

                        let itemElement = drawItem(item.id, item.name, item.description, item.price);

                        // When the user clicks on the menu item, we will check to see if they would like to add the
                        // item to their cart.
                        itemElement.addEventListener("click", function () {
                            if (confirm(`Would you like to add the ${item.name} to your cart?`) === true) {
                                addItemToCart(itemElement.getAttribute("data-item"));
                            }
                        });

                        menuContainerElement.appendChild(itemElement);

                    });

                    headerElement.appendChild(menuContainerElement);

                } else {
                    userNotAuthorized(headerElement);
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
