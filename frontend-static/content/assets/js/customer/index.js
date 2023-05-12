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

        let informationTitleContainer = document.createElement("div");
        informationTitleContainer.classList.add("innermost-color");
        informationTitleContainer.classList.add("rounded-corners");

        let informationTitleElement = document.createElement("h2");
        let informationTitleContent = document.createTextNode("Unauthorized");
        informationTitleElement.appendChild(informationTitleContent);

        let informationDescriptionElement = document.createElement("p");
        let informationDescriptionContent = document.createTextNode("This portion of the website looks like it contains restricted content. You can feel free to do so after you are logged into the system.");
        informationDescriptionElement.appendChild(informationDescriptionContent);

        informationTitleContainer.appendChild(informationTitleElement);
        informationTitleContainer.appendChild(informationDescriptionElement);

        headerElement.appendChild(informationTitleContainer);

    }

}());
