import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';

(function () {

    function removeItemFromCart(itemId) {

        if(Cookies.get('cart')) {

            let cart = Cookies.get('cart');
            let cartItems = JSON.parse(cart);

            let cartItemIndex = cartItems.indexOf(itemId);
            cartItems.splice(cartItemIndex, 1);

            Cookies.set('cart', JSON.stringify(cartItems));

        }

    }

    function fetchItemFromServer(itemId) {

        let itemElement = document.createElement("div");
        itemElement.id = itemId;
        itemElement.classList.add("innermost-color");
        itemElement.classList.add("rounded-corners");

        let request = new XMLHttpRequest();

        request.addEventListener("load", function() {

            if(request.status === 200) {

                let item = JSON.parse(request.response);

                let itemTitleElement = document.createElement("p");
                let itemTitleContent = document.createTextNode(item.name);
                itemTitleElement.appendChild(itemTitleContent);

                let itemDescriptionElement = document.createElement("p");
                let itemDescriptionContent = document.createTextNode(item.description);
                itemDescriptionElement.appendChild(itemDescriptionContent);

                let itemPriceElement = document.createElement("p");
                let itemPriceContent = document.createTextNode("$" + item.price);
                itemPriceElement.appendChild(itemPriceContent);

                itemElement.appendChild(itemTitleElement);
                itemElement.appendChild(itemDescriptionElement);
                itemElement.appendChild(itemPriceElement);

                // When the user clicks on the menu item, we will check to see if they would like to remove the
                // item from their cart.
                itemElement.addEventListener("click", function () {
                    if (confirm(`Would you like remove add the ${item.name} from your cart?`) === true) {
                        removeItemFromCart(itemElement.id);
                        refreshDisplay();
                    }
                });
            }
        });

        request.open("GET", `${urlPath}/customer/items/${itemId}`);
        request.setRequestHeader("Authorization", `Bearer ${Cookies.get("jwtToken")}`);
        request.send();

        return itemElement;
    }

    function refreshDisplay() {
        let headerElement = document.getElementById('dynamicContent');
        headerElement.innerHTML = "";

        if (Cookies.get('jwtToken')) {

            if(Cookies.get('cart')) {

                let menuContainerElement = document.createElement("div");
                menuContainerElement.classList.add("inner-color");
                menuContainerElement.classList.add("rounded-corners");

                let cartItems = JSON.parse(Cookies.get('cart'));
                cartItems.forEach(item => {
                    menuContainerElement.appendChild(fetchItemFromServer(item));
                });

                headerElement.appendChild(menuContainerElement);

            }

        } else {
            userNotAuthenticated(headerElement);
        }
    }

    if (checkCookieConsent() === true) {

        refreshDisplay();

    }

}());
