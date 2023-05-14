import '../include/js.cookie.min.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';

(function () {

    if (checkCookieConsent() === true) {

        let firstNameTextField = document.getElementById("firstname");
        let lastNameTextField = document.getElementById("lastname");
        let emailTextField = document.getElementById("email");
        let phoneTextField = document.getElementById("phone");
        let addressTextField = document.getElementById("address");
        let cityTextField = document.getElementById("city");
        let stateTextField = document.getElementById("state");
        let zipCodeTextField = document.getElementById("zip");
        let usernameTextField = document.getElementById("username");
        let confirmUsernameTextField = document.getElementById("confirmUsername");
        let passwordTextField = document.getElementById("password");
        let confirmPasswordTextField = document.getElementById("confirmPassword");

        let submitButtonElement = document.getElementById("submit");

        submitButtonElement.addEventListener("click", function (event) {

            event.preventDefault();

            // Perform form validation on the different input fields.
            // Declare the regular expression field accordingly.

            const emailRegularExpression = /\w{2,}@\w{2,}.\w{2,}/;
            const phoneNumberRegularExpression = /\d{10}/;

            // First check to see if any fields are empty.
            if (firstNameTextField.value.length === 0) {
                alert("It looks like you forgot to input your first name!");
            } else if (lastNameTextField.value.length === 0) {
                alert("It looks like you forgot to input your last name!");
            } else if (emailTextField.value.length === 0) {
                alert("It looks like you forgot to input your email address!");
            } else if (phoneTextField.value.length === 0) {
                alert("It looks like you forgot to input your phone number!");
            } else if (addressTextField.value.length === 0) {
                alert("It looks like you forgot to input your postal address!");
            } else if (cityTextField.value.length === 0) {
                alert("It looks like you forgot to input your city!");
            } else if (stateTextField.value.length === 0) {
                alert("It looks like you forgot to input your state!");
            } else if (zipCodeTextField.value.length === 0) {
                alert("It looks like you forgot to input your zip code!");
            } else if (usernameTextField.value.length === 0) {
                alert("It looks like you forgot to provide a new username!");
            } else if (confirmUsernameTextField.value.length === 0) {
                alert("It looks like you forgot to confirm your new username!");
            } else if (passwordTextField.value.length === 0) {
                alert("It looks like you forgot to provide a new password!");
            } else if (confirmPasswordTextField.value.length === 0) {
                alert("It looks like you forgot to confirm your new password!");
            }

            // The next step is to check if the username and password confirmation fields match.
            else if (usernameTextField.value !== confirmUsernameTextField.value) {
                alert("Your username confirmation field doesn't match!");
            } else if (passwordTextField.value !== confirmPasswordTextField.value) {
                alert("Your password confirmation field doesn't match!");
            }

            // Then, check if the password field has fewer than eight digits.
            else if (passwordTextField.value.length < 8) {
                alert("Your password should contain at least eight digits to remain secure!");
            }

            // Then, check to see if the email address is in a valid format.
            else if (!emailRegularExpression.test(emailTextField.value)) {
                alert("It looks like your email address is formatted improperly.");
            }

            // Then, check to see if the phone number is exactly 10 digits long
            else if (!phoneNumberRegularExpression.test(phoneTextField.value) || phoneTextField.value.length !== 10) {
                alert("It looks like your phone number is formatted improperly.\nPlease enter a valid US-based phone number with the area code, but without any spaces.\nFor example, +1 (555) 555-5555 would be formatted as 5555555555");
            }

            // If all the forms are good to go, then we can submit a request to register a new user.
            else {

                let registrationRequest = new XMLHttpRequest();

                registrationRequest.addEventListener("load", function () {

                    if (registrationRequest.status === 200) {

                        let jsonResponse = JSON.parse(registrationRequest.response);

                        if (jsonResponse.errorMessage) {
                            alert(jsonResponse.errorMessage);
                        }

                        if (jsonResponse.jwtToken) {
                            Cookies.set('jwtToken', jsonResponse.jwtToken);
                            alert("You have been registered and logged in!");
                        }

                    } else {

                        alert("Registration unsuccessful!");

                    }

                });

                registrationRequest.open("POST", `${urlPath}/auth/register/customer/new`);
                registrationRequest.setRequestHeader("Content-Type", "application/json");
                registrationRequest.send(JSON.stringify({
                    "firstName": firstNameTextField.value,
                    "lastName": lastNameTextField.value,
                    "email": emailTextField.value,
                    "phone": phoneTextField.value,
                    "address": addressTextField.value,
                    "city": cityTextField.value,
                    "state": stateTextField.value,
                    "zip": zipCodeTextField.value,
                    "username": usernameTextField.value,
                    "password": passwordTextField.value
                }));

            }

        });

    }

}());
