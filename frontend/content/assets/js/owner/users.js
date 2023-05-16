import '../include/js.cookie.min.js';
import {userNotAuthenticated} from '../shared/user-not-authenticated.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from "../shared/configuration.js";
import {drawUser} from "../shared/component/user.js";
import {userNotAuthorized} from "../shared/user-not-authorized.js";

(function () {

    let headerElement = document.getElementById('dynamicContent');

    // Method to add or remove a certain role from a user.
    function toggleUserPrivileges(username, roleName, roleIsBeingAdded) {

        if (roleIsBeingAdded) {

            let roleAddRequest = new XMLHttpRequest();
            roleAddRequest.addEventListener("load", function () {

                if (roleAddRequest.status === 200) {

                    alert("Operation Succeeded!");

                } else {
                    alert("It looks like there was an error with the system.\nOperation failed!");
                }

            });

            let jsonRequest = {
                "username": username
            };

            // If the role addition is request, we will set the salary field to a default value of $0.00
            // (which can be updated through the database as needed).
            if (roleName.toUpperCase() === "EMPLOYEE") {
                jsonRequest["salary"] = 0.00;
            }

            alert(jsonRequest);

            roleAddRequest.open("PATCH", `${urlPath}/auth/register/${roleName.toLowerCase()}/existing`)
            roleAddRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            roleAddRequest.setRequestHeader("Content-Type", "application/json");
            roleAddRequest.send(JSON.stringify(jsonRequest));

        } else {

            let roleDeletionRequest = new XMLHttpRequest();
            roleDeletionRequest.addEventListener("load", function () {

                if (roleDeletionRequest.status === 200) {

                    let jsonResponse = JSON.parse(roleDeletionRequest.response);

                    alert("Operation Succeeded!");

                } else {
                    alert("It looks like there was an error with the system.\nOperation failed!");
                }

            });

            roleDeletionRequest.open("PATCH", `${urlPath}/auth/delete/role`)
            roleDeletionRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            roleDeletionRequest.setRequestHeader("Content-Type", "application/json");
            roleDeletionRequest.send(JSON.stringify({
                "username": username, "role": roleName.toUpperCase()
            }));

        }

    }

    if (checkCookieConsent() === true) {

        if (Cookies.get('jwtToken')) {

            let usersRequest = new XMLHttpRequest();
            usersRequest.addEventListener("load", function () {

                if (usersRequest.status === 200) {

                    let userContainerElement = document.createElement("div");
                    userContainerElement.classList.add("inner-color");
                    userContainerElement.classList.add("rounded-corners");

                    let jsonResponse = JSON.parse(usersRequest.response);
                    jsonResponse.users.forEach(user => {

                        let userElement = drawUser(user);

                        // Add a button to toggle the customer role.
                        let toggleCustomerRoleButton = document.createElement("button");
                        toggleCustomerRoleButton.classList.add("fancy-button");
                        let toggleCustomerRoleContent = document.createTextNode("");

                        if (userElement.getAttribute("data-is-customer") === "true") {

                            toggleCustomerRoleContent.textContent = "Remove CUSTOMER Role";
                            toggleCustomerRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "CUSTOMER", false);
                            });

                        } else {

                            toggleCustomerRoleContent.textContent = "Add CUSTOMER Role";
                            toggleCustomerRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "CUSTOMER", true);
                            });

                        }

                        toggleCustomerRoleButton.appendChild(toggleCustomerRoleContent);
                        userElement.appendChild(toggleCustomerRoleButton);

                        let toggleEmployeeRoleButton = document.createElement("button");
                        toggleEmployeeRoleButton.classList.add("fancy-button");
                        let toggleEmployeeRoleContent = document.createTextNode("");

                        if (userElement.getAttribute("data-is-employee") === "true") {

                            toggleEmployeeRoleContent.textContent = "Remove EMPLOYEE Role";
                            toggleEmployeeRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "EMPLOYEE", false);
                            });

                        } else {

                            toggleEmployeeRoleContent.textContent = "Add EMPLOYEE Role";
                            toggleEmployeeRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "EMPLOYEE", true);
                            });

                        }

                        toggleEmployeeRoleButton.appendChild(toggleEmployeeRoleContent);
                        userElement.appendChild(toggleEmployeeRoleButton);

                        let toggleOwnerRoleButton = document.createElement("button");
                        toggleOwnerRoleButton.classList.add("fancy-button");
                        let toggleOwnerRoleContent = document.createTextNode("");

                        if (userElement.getAttribute("data-is-owner") === "true") {

                            toggleOwnerRoleContent.textContent = "Remove OWNER Role";
                            toggleOwnerRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "OWNER", false);
                            });

                        } else {

                            toggleOwnerRoleContent.textContent = "Add OWNER Role";
                            toggleOwnerRoleButton.addEventListener("click", function () {
                                toggleUserPrivileges(userElement.getAttribute("data-user-username"), "OWNER", true);
                            });

                        }

                        toggleOwnerRoleButton.appendChild(toggleOwnerRoleContent);
                        userElement.appendChild(toggleOwnerRoleButton);

                        userContainerElement.appendChild(userElement);

                    });

                    headerElement.appendChild(userContainerElement);

                } else {
                    userNotAuthorized(headerElement);
                }

            });

            usersRequest.open("GET", `${urlPath}/owner/users`);
            usersRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
            usersRequest.send();

        } else {
            userNotAuthenticated(headerElement);
        }

    }

}());
