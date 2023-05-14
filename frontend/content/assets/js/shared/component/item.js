// Function to create a specified item in the DOM, with the item name, description, and cost provided as parameters.
export function drawItem(itemId, itemName, itemDescription, itemCost) {

    let purchaseItemElement = document.createElement("div");
    purchaseItemElement.classList.add("innermost-color");
    purchaseItemElement.classList.add("rounded-corners");
    purchaseItemElement.setAttribute("data-item", itemId);

    let purchaseItemTitleElement = document.createElement("p");
    let purchaseItemTitleContent = document.createTextNode(itemName);
    purchaseItemTitleElement.appendChild(purchaseItemTitleContent);
    purchaseItemElement.appendChild(purchaseItemTitleElement);

    let purchaseItemDescriptionElement = document.createElement("p");
    let purchaseItemDescriptionContent = document.createTextNode(itemDescription);
    purchaseItemDescriptionElement.appendChild(purchaseItemDescriptionContent);
    purchaseItemElement.appendChild(purchaseItemDescriptionElement);

    let purchaseItemPriceElement = document.createElement("p");
    let purchaseItemPriceContent = document.createTextNode("$" + itemCost);
    purchaseItemPriceElement.appendChild(purchaseItemPriceContent);
    purchaseItemElement.appendChild(purchaseItemPriceElement);

    return purchaseItemElement;

}
