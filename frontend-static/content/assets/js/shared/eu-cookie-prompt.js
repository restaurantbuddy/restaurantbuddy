import '../include/js.cookie.min.js';

export function checkCookieConsent() {

    if (Cookies.get('cookieConsent') === "true") {
        return true
    } else {
        const cookiePrompt = "This website uses cookies to store data locally in your browser.\nThis data is used only for the normal operation of the application and is not used for advertising purposes.\nBy continuing to use this website, you consent this website to use cookies in your web browser.";
        if (confirm(cookiePrompt) === true) {
            Cookies.set('cookieConsent', "true")
            return true
        } else {
            return false
        }
    }

}
