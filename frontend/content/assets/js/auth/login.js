import '../include/js.cookie.min.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {urlPath} from '../shared/configuration.js';

(function () {

    if (checkCookieConsent() === true) {

        let usernameTextField = document.getElementById("username");
        let passwordTextField = document.getElementById("password");

        let submitButtonElement = document.getElementById("submit");

        submitButtonElement.addEventListener("click", function (event) {

            event.preventDefault();

            let loginRequest = new XMLHttpRequest();

            loginRequest.addEventListener("load", function () {

                if (loginRequest.status === 200) {

                    let jsonResponse = JSON.parse(loginRequest.response);
                    Cookies.set('jwtToken', jsonResponse.jwtToken);

                    // If the user is being redirected to the login form from another page,
                    // redirect them back to that page once they have been accepted.
                    if (Cookies.get('redirect')) {
                        let url = Cookies.get('redirect');
                        Cookies.remove('redirect');
                        window.location.replace(url);
                    }

                } else {

                    alert("Login unsuccessful!");

                }

            });

            loginRequest.open("POST", `${urlPath}/auth/authenticate`);
            loginRequest.setRequestHeader("Content-Type", "application/json");
            loginRequest.send(JSON.stringify({
                "username": usernameTextField.value,
                "password": passwordTextField.value
            }));

        });

    }

}());
