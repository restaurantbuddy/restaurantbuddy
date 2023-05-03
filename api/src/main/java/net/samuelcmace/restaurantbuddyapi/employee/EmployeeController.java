package net.samuelcmace.restaurantbuddyapi.employee;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Employee;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller to handle all employee-related requests.
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    /**
     * Method called to test whether the user is authenticated as an employee.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an employee.
     */
    @GetMapping("/")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Employee.TABLE_NAME).build();
    }

}
