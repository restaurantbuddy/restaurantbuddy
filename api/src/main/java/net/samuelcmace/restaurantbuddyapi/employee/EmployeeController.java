package net.samuelcmace.restaurantbuddyapi.employee;

import net.samuelcmace.restaurantbuddyapi.database.models.Customer;
import net.samuelcmace.restaurantbuddyapi.shared.GenericResponseModel;
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
    @GetMapping(value = "/employee")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Customer.TABLE_NAME).build();
    }

}
