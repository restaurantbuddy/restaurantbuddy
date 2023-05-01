package net.samuelcmace.restaurantbuddyapi.customer;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller used to manage all customer-related requests.
 */
@RestController
public class CustomerController {

    /**
     * Method called to test whether the user is authenticated as a customer.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as a customer.
     */
    @GetMapping(value = "/customer", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isCustomer", true);

        return result.toString();

    }

}
