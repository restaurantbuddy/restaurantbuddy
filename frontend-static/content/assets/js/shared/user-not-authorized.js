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
    let informationDescriptionContent = document.createTextNode("This portion of the website looks like it contains restricted content. You can feel free to do so after you are logged into the system.");
    informationDescriptionElement.appendChild(informationDescriptionContent);

    informationTitleContainer.appendChild(informationTitleElement);
    informationTitleContainer.appendChild(informationDescriptionElement);

    headerElement.appendChild(informationTitleContainer);

}