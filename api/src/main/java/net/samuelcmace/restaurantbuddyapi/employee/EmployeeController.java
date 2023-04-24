package net.samuelcmace.restaurantbuddyapi.employee;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(value = "/employee", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isEmployee", true);

        return result.toString();

    }

}
