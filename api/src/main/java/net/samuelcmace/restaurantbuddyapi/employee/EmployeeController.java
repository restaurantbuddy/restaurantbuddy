package net.samuelcmace.restaurantbuddyapi.employee;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller to handle all employee-related requests.
 */
@RestController
public class EmployeeController {

    /**
     * Method called to test whether the user is authenticated as an employee.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an employee.
     */
    @GetMapping(value = "/employee", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isEmployee", true);

        return result.toString();

    }

}
