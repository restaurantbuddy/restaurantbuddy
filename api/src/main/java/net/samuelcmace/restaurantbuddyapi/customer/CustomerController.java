package net.samuelcmace.restaurantbuddyapi.customer;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.customer.models.OrderModel;
import net.samuelcmace.restaurantbuddyapi.database.models.Customer;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.AllItemsModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.ItemModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.purchase.AllPurchasesModel;
import net.samuelcmace.restaurantbuddyapi.shared.service.ItemReadService;
import net.samuelcmace.restaurantbuddyapi.shared.service.PurchaseReadService;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller used to manage all customer-related requests.
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    /**
     * ItemReadService instance associated with the service.
     */
    private final ItemReadService itemReadService;

    /**
     * PurchaseReadService instance associated with the service.
     */
    private final PurchaseReadService purchaseReadService;

    /**
     * CustomerService instance associated with the service.
     */
    private final CustomerService customerService;

    /**
     * Method called to test whether the user is authenticated as a customer.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as a customer.
     */
    @GetMapping("/")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Customer.TABLE_NAME).build();
    }

    /**
     * MVC route to view all menu items stored in the database.
     *
     * @return A JSON object containing the menu items.
     */
    @GetMapping("/menu/read/all")
    public AllItemsModel readAllItems() {
        return itemReadService.findAllMenuItems();
    }

    /**
     * MVC route to view all menu items stored in the database.
     *
     * @param primaryKey The primary key to be queried.
     * @return A JSON object containing the menu item.
     */
    @GetMapping("/menu/read/{primaryKey}")
    public ItemModel readMenuItemById(
            @PathVariable Long primaryKey
    ) {
        return itemReadService.findMenuItem(primaryKey);
    }

    /**
     * MVC route to view all menu items stored in the database.
     *
     * @param request The JSON model containing the order to be placed.
     * @return A JSON object containing the menu item.
     */
    @PostMapping("/order/create")
    public GenericResponseModel placeOrder(
            @RequestBody OrderModel request
    ) {
        try {
            customerService.placeOrder(request);
            return GenericResponseModel.builder().successMessage("All operations completed successfully!").build();
        } catch (Exception e) {
            return GenericResponseModel.builder().errorMessage(e.getLocalizedMessage()).build();
        }
    }

    /**
     * Method to retrieve all the orders placed by the customer.
     *
     * @return An object containing the entire order history of an authenticated customer.
     */
    @GetMapping("/order/read/all")
    public AllPurchasesModel getAllOrders() {
        return purchaseReadService.getPurchasesByCustomer();
    }

}
