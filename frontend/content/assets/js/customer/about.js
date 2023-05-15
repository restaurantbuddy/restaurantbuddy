import '../include/js.cookie.min.js';
import {checkCookieConsent} from "../shared/eu-cookie-prompt.js";
import {buildLocation} from "../shared/component/location.js";
import {urlPath} from "../shared/configuration.js";

(function () {

    if (checkCookieConsent() === true) {

        let headerElement = document.getElementById('dynamicContent');

        let locationsRequest = new XMLHttpRequest();
        locationsRequest.addEventListener("load", function () {

            if (locationsRequest.status === 200) {

                let locationsContainerElement = document.createElement("div");
                locationsContainerElement.classList.add("inner-color");
                locationsContainerElement.classList.add("rounded-corners");

                let jsonResponse = JSON.parse(locationsRequest.response);
                jsonResponse.locations.forEach(location => {

                    let locationElement = buildLocation(location);

                    locationElement.addEventListener("click", function () {
                        window.open(`https://www.google.com/maps/search/?api=1&query=${locationElement.getAttribute("data-location-info")}`, "_blank");
                    })

                    locationsContainerElement.appendChild(locationElement);

                });

                headerElement.appendChild(locationsContainerElement);

            }

        });

        locationsRequest.open("GET", `${urlPath}/about/locations`);
        locationsRequest.setRequestHeader("Authorization", `Bearer ${Cookies.get('jwtToken')}`);
        locationsRequest.send();

    }

}());
