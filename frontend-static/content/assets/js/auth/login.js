(function () {

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
                alert("You have been logged in!");

            }
            else {
                alert("Login unsuccessful!");
            }

        });

        loginRequest.open("POST", "http://localhost:8888/api/v1/auth/authenticate");
        loginRequest.setRequestHeader("Content-Type", "application/json");
        loginRequest.send(JSON.stringify({"username": usernameTextField.value, "password": passwordTextField.value}));

    });

}());
