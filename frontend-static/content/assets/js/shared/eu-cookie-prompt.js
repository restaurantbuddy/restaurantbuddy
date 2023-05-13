import '../include/js.cookie.min.js';

export function checkCookieConsent() {

    if (!Cookies.get('cookieConsent') || Cookies.get('cookieConsent') === false) {
        const cookiePrompt = "This website uses cookies to store data locally on your computer.\nThis data is used only for normal functions of the application and is not sold to adverts.\nBy continuing to use this website, you consent this website to use cookies in your web browser.";
        if (confirm(cookiePrompt) == true) {
            Cookies.set('cookieConsent', true)
            return true
        } else {
            return false
        }
    } else if (Cookies.get('cookieConsent') === true) {
        return true
    }

}