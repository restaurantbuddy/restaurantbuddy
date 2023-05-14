import {drawItem} from "./item.js";

export function drawOrder(purchase) {

    let purchaseElement = document.createElement("div");
    purchaseElement.setAttribute("data-order", purchase.id);
    purchaseElement.classList.add("innermost-color");
    purchaseElement.classList.add("rounded-corners");

    let purchaseIdElement = document.createElement("p");
    let purchaseIdElementContent = document.createTextNode("Purchase ID: " + purchase.id);
    purchaseIdElement.appendChild(purchaseIdElementContent);
    purchaseElement.appendChild(purchaseIdElement);

    let purchaseTimePlacedElement = document.createElement("p");
    let purchaseTimePlacedElementContent = document.createTextNode("Time Placed: " + purchase.timePlaced[3] + ":" + purchase.timePlaced[4] + " on " + purchase.timePlaced[1] + "/" + purchase.timePlaced[2] + "/" + purchase.timePlaced[0]);
    purchaseTimePlacedElement.appendChild(purchaseTimePlacedElementContent);
    purchaseElement.appendChild(purchaseTimePlacedElement);

    if (purchase.timeCompleted) {
        let purchaseTimeCompletedElement = document.createElement("p");
        let purchaseTimeCompletedElementContent = document.createTextNode("Time Completed: " + +purchase.timeCompleted[3] + ":" + purchase.timeCompleted[4] + " on " + purchase.timeCompleted[1] + "/" + purchase.timeCompleted[2] + "/" + purchase.timeCompleted[0]);
        purchaseTimeCompletedElement.appendChild(purchaseTimeCompletedElementContent);
        purchaseElement.appendChild(purchaseTimeCompletedElement);
    }

    let purchaseCustomerUsernameElement = document.createElement("p");
    let purchaseCustomerUsernameElementContent = document.createTextNode("Username: " + purchase.customerUsername);
    purchaseCustomerUsernameElement.appendChild(purchaseCustomerUsernameElementContent);
    purchaseElement.appendChild(purchaseCustomerUsernameElement);

    let purchaseItemsElement = document.createElement("div");

    purchase.items.forEach(item => {
        purchaseItemsElement.appendChild(drawItem("i" + item.id, item.name, item.description, item.price));
    });

    purchaseElement.appendChild(purchaseItemsElement);

    return purchaseElement;

}