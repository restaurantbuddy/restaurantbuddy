import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {userNotAuthorized} from "../shared/user-not-authorized.js";
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';
import {drawItem} from "../shared/component/item.js";

(function () {

    let headerElement = document.getElementById('dynamicContent');

    function removeItemFromCart(itemId) {

        if (Cookies.get('cart')) {

            let cart = Cookies.get('cart');
            let cartItems = JSON.parse(cart);

            let cartItemIndex = cartItems.indexOf(itemId);
            cartItems.splice(cartItemIndex, 1);

            if (cartItems.length === 0) Cookies.remove('cart'); else Cookies.set('cart', JSON.stringify(cartItems));

        }

    }

    function clearCart() {

        if (Cookies.get('cart')) {
            Cookies.remove('cart');
        }

    }

    function refreshDisplay() {

        headerElement.innerHTML = "";

        if (Cookies.get('jwtToken')) {

            if (Cookies.get('cart')) {

                let menuContainerElement = document.createElement("div");
                menuContainerElement.classList.add("inner-color");
                menuContainerElement.classList.add("rounded-corners");

                let cartItems = JSON.parse(Cookies.get('cart'));
                cartItems.forEach(item => {

                    let request = new XMLHttpRequest();

                    request.addEventListener("load", function () {

                        if (request.status === 200) {

                            let item = JSON.parse(request.response);
                            let itemElement = drawItem(item.id, item.name, item.description, item.price);

                            // When the user clicks on the menu item, we will check to see if they would like to remove the
                            // item from their cart.
                            itemElement.addEventListener("click", function () {
                                if (confirm(`Would you like remove add the ${item.name} from your cart?`) === true) {
                                    removeItemFromCart(itemElement.getAttribute("data-item"));
                                    refreshDisplay();
                                }
                            });

                            menuContainerElement.append(itemElement);

                        } else {
                            userNotAuthorized(headerElement);
                        }

                    });

                    request.open("GET", `${urlPath}/customer/items/${item}`);
                    request.setRequestHeader("Authorization", `Bearer ${Cookies.get("jwtToken")}`);
                    request.send();

                });

                headerElement.appendChild(menuContainerElement);

                // Add a button to add a new order.
                let orderRequestButton = document.createElement("button");
                orderRequestButton.classList.add("fancy-button");
                let orderRequestButtonText = document.createTextNode("Place Order");
                orderRequestButton.appendChild(orderRequestButtonText);

                orderRequestButton.addEventListener("click", function () {
                    placeOrder();
                    clearCart();
                    refreshDisplay();
                });

                headerElement.appendChild(orderRequestButton);

                // Add a button to clear the shopping cart.
                let clearCartButton = document.createElement("button");
                clearCartButton.classList.add("fancy-button");
                let clearCartButtonText = document.createTextNode("Clear Cart");
                clearCartButton.appendChild(clearCartButtonText);

                clearCartButton.addEventListener("click", function () {
                    clearCart();
                    refreshDisplay();
                });

                headerElement.appendChild(clearCartButton);

            } else {
                alert("It looks like your cart is empty.\nYou can navigate to the homepage to start shopping for items.");
            }

        } else {

            userNotAuthenticated(headerElement);

        }

    }

    function placeOrder() {

        if (Cookies.get('jwtToken')) {

            if (Cookies.get('cart')) {

                let cartItems = JSON.parse(Cookies.get('cart'));

                if (cartItems.size <= 0) {
                    alert("You can't place an empty order!\nYou can add items to your cart by navigating to the homepage and clicking on the items from there.");
                } else {

                    let orderRequest = new XMLHttpRequest();
                    orderRequest.addEventListener("load", function () {

                        if (orderRequest.status === 200) {

                            let response = JSON.parse(orderRequest.response)
                            if (response.successMessage) {
                                alert(response.successMessage);
                            }
                            if (response.errorMessage) {
                                alert(response.errorMessage);
                            }

                        } else {
                            userNotAuthorized(headerElement);
                        }

                    });

                    orderRequest.open("POST", `${urlPath}/customer/order`);
                    orderRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get("jwtToken")}`);
                    orderRequest.setRequestHeader("Content-Type", "application/json");
                    orderRequest.send(JSON.stringify({
                        "menuItems": cartItems,
                    }));

                }

            }

        } else {
            userNotAuthenticated(headerElement);
        }

    }

    if (checkCookieConsent() === true) {

        refreshDisplay();

    }

}());
