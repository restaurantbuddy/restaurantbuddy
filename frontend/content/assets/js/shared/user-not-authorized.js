import '../include/js.cookie.min.js';

export function userNotAuthorized(headerElement) {

    // If the user is not authenticated, change the logout link to a login link
    let authenticationLink = document.getElementById("authenticationLink");
    authenticationLink.setAttribute("href", "/auth/login");
    authenticationLink.textContent = "Login";

    // Then, create elements to store an error message and append them to the DOM.
    let informationTitleContainer = document.createElement("div");
    informationTitleContainer.classList.add("innermost-color");
    informationTitleContainer.classList.add("rounded-corners");

    let informationTitleElement = document.createElement("h2");
    let informationTitleContent = document.createTextNode("Unauthorized");
    informationTitleElement.appendChild(informationTitleContent);

    let informationDescriptionElement = document.createElement("p");
    let informationDescriptionContent = document.createTextNode("Although you are logged into the system, it looks like you're trying to access a portion of the website that you are not allowed to access.");
    informationDescriptionElement.appendChild(informationDescriptionContent);

    informationTitleContainer.appendChild(informationTitleElement);
    informationTitleContainer.appendChild(informationDescriptionElement);

    headerElement.appendChild(informationTitleContainer);

    // In case the user clicks the login button, set a cookie storing the address to return to once the user is logged back in.
    Cookies.set('redirect', window.location.href);

}
