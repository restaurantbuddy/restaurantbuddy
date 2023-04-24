package net.samuelcmace.restaurantbuddyapi.customer;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping(value = "/customer", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isCustomer", true);

        return result.toString();

    }

}
