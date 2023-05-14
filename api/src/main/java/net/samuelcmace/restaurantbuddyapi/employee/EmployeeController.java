package net.samuelcmace.restaurantbuddyapi.employee;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Employee;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.purchase.AllPurchasesModel;
import net.samuelcmace.restaurantbuddyapi.shared.service.PurchaseReadService;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller to handle all employee-related requests.
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    /**
     * Instance of EmployeeService used by the class.
     */
    private final EmployeeService employeeService;

    /**
     * Instance of PurchaseReadService used by the class.
     */
    private final PurchaseReadService purchaseReadService;

    /**
     * Method called to test whether the user is authenticated as an employee.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an employee.
     */
    @GetMapping("/")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Employee.TABLE_NAME).build();
    }

    /**
     * MVC route to fetch all open orders in the database for the employee to view.
     *
     * @return An AllPurchasesModel encapsulating all the purchases that remain open.
     */
    @GetMapping("/orders/open")
    public AllPurchasesModel getOpenOrders() {
        return purchaseReadService.getOpenPurchases();
    }

    /**
     * MVC route for an employee to complete an order. The order completion time will be assigned to the system time at
     * the time the order is sent in.
     *
     * @param orderId The order ID to be completed.
     * @return A GenericResponseModel indicating whether the operation succeeded.
     */
    @PatchMapping("/orders/{orderId}")
    public GenericResponseModel completeOrder(@PathVariable Long orderId) {
        return employeeService.completeOrder(orderId);
    }

}
