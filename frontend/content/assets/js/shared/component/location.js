export function buildLocation(location) {

    let locationContainerElement = document.createElement("div");
    locationContainerElement.classList.add("innermost-color");
    locationContainerElement.classList.add("rounded-corners");
    locationContainerElement.setAttribute("data-location", location.id);
    locationContainerElement.setAttribute("data-location-info", `${location.name}, ${location.city}, ${location.state} ${location.zip}`);

    let locationNameElement = document.createElement("p");
    let locationNameElementContent = document.createTextNode(location.name);
    locationNameElement.appendChild(locationNameElementContent);
    locationContainerElement.appendChild(locationNameElement);

    let locationAddressElement = document.createElement("p");
    let locationAddressElementContent = document.createTextNode(location.address);
    locationAddressElement.appendChild(locationAddressElementContent);
    locationContainerElement.appendChild(locationAddressElement);

    let locationRegionElement = document.createElement("p");
    let locationRegionElementContent = document.createTextNode(`${location.city}, ${location.state} ${location.zip}`);
    locationRegionElement.appendChild(locationRegionElementContent);
    locationContainerElement.appendChild(locationRegionElement);

    return locationContainerElement;

}
