import { userNotAuthorized } from '../shared/user-not-authorized.js';

(function () {

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

        request.open("GET", "http://localhost:8888/api/v1/customer/items");
        request.setRequestHeader("Authorization", "Bearer " + Cookies.get("jwtToken"));
        request.send();

    } else {

        userNotAuthorized(headerElement);

    }

}());
