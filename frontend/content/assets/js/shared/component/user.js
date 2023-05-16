export function drawUser(user) {

    let userElement = document.createElement("div");
    userElement.classList.add("innermost-color");
    userElement.classList.add("rounded-corners");
    userElement.setAttribute("data-user", user.id);
    userElement.setAttribute("data-user-username", user.username);

    let userFullNameElement = document.createElement("p");
    let userFullNameContent = document.createTextNode(`${user.firstName} ${user.lastName}`);
    userFullNameElement.appendChild(userFullNameContent);
    userElement.appendChild(userFullNameElement);

    let userEmailElement = document.createElement("p");
    let userEmailContent = document.createTextNode(user.email);
    userEmailElement.appendChild(userEmailContent);
    userElement.appendChild(userEmailElement);

    let userPhoneElement = document.createElement("p");
    let userPhoneContent = document.createTextNode("");

    // If the user's phone number is formatted properly in the JSON model, display it in the DOM using the standard
    // US-based phone number formatting standard like so:
    if (user.phone.length === 10) {
        userPhoneContent.textContent = `(${user.phone.substring(0, 3)}) ${user.phone.substring(3, 6)}-${user.phone.substring(6, 10)}`;
    } else {
        userPhoneElement.textContent = user.phone;
    }

    userPhoneElement.appendChild(userPhoneContent);
    userElement.appendChild(userPhoneElement);

    let userAddressElement = document.createElement("p");
    let userAddressContent = document.createTextNode(user.address);
    userAddressElement.appendChild(userAddressContent);
    userElement.appendChild(userAddressElement);

    let userRegionElement = document.createElement("p");
    let userRegionContent = document.createTextNode(`${user.city}, ${user.state} ${user.zip}`);
    userRegionElement.appendChild(userRegionContent);
    userElement.appendChild(userRegionElement);

    let userRolesElement = document.createElement("p");
    let userRolesContent = document.createTextNode("Roles: " + user.roles);
    userRolesElement.appendChild(userRolesContent);
    userElement.appendChild(userRolesElement);

    user.roles.forEach(role => {
        userElement.setAttribute("data-is-" + role, "true");
    });

    let userUsernameElement = document.createElement("p");
    let userUsernameContent = document.createTextNode(`Username: ${user.username}`);
    userUsernameElement.appendChild(userUsernameContent);
    userElement.appendChild(userUsernameElement);

    return userElement;

}
