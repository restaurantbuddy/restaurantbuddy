(function () {

    if (Cookies.get('jwtToken')) {
        alert("The JWT Token is " + Cookies.get('jwtToken'));
    } else {
        alert("You need to sign in to view this page!");
    }

}());
