package net.samuelcmace.restaurantbuddyapi.customer;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Customer;
import net.samuelcmace.restaurantbuddyapi.shared.GenericResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller used to manage all customer-related requests.
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    /**
     * Method called to test whether the user is authenticated as a customer.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as a customer.
     */
    @GetMapping("/")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Customer.TABLE_NAME).build();
    }

}
