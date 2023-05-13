package net.samuelcmace.restaurantbuddyapi.customer;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.customer.models.OrderModel;
import net.samuelcmace.restaurantbuddyapi.database.models.Customer;
import net.samuelcmace.restaurantbuddyapi.database.models.Item;
import net.samuelcmace.restaurantbuddyapi.database.models.Login;
import net.samuelcmace.restaurantbuddyapi.database.models.Purchase;
import net.samuelcmace.restaurantbuddyapi.database.repositories.ItemRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.LoginRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.PurchaseRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service bean for the Customer controller. This service is mainly responsible for placing orders, and other things
 * that would be related strictly to the customer role.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    /**
     * The LoginRepository instance associated with the service.
     */
    private final LoginRepository loginRepository;

    /**
     * The ItemRepository instance associated with the service.
     */
    private final ItemRepository itemRepository;

    /**
     * The PurchaseRepository instance associated with the service.
     */
    private final PurchaseRepository purchaseRepository;

    /**
     * Method called by the customer to place an order.
     *
     * @param request A JSON object containing the order to be placed.
     */
    public void placeOrder(OrderModel request) throws Exception {

        // The first step is to retrieve the customer id that placed the order.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // The next step is to query the database for the login that made the request.
        Optional<Login> loginQuery = loginRepository.findByUsername(username);

        if (loginQuery.isPresent()) {

            // If the user has access to the controller, it is safe to assume that
            // the user has CUSTOMER privileges, so there should be no need to check for null values.
            Customer associatedCustomer = loginQuery.get().getUser().getCustomer();

            List<Item> menuItems = new ArrayList<>();

            for (Long primaryKey : request.getMenuItems()) {
                Optional<Item> items = itemRepository.findById(primaryKey);

                if (items.isPresent()) {
                    menuItems.add(items.get());
                } else {
                    throw new Exception("Error: The menu item with the primary key of " + primaryKey + " could not be found!");
                }
            }

            Purchase purchase = Purchase.builder()
                    .customer(associatedCustomer)
                    .timePlaced(LocalDateTime.now())
                    .items(menuItems)
                    .build();

            for (Long item : request.getMenuItems()) {

                Optional<Item> itemEntity = itemRepository.findById(item);

                if (itemEntity.isPresent()) {
                    purchase.addItem(itemEntity.get());
                } else {
                    throw new Exception("The item with the primary key of " + item + " could not be found!");
                }

            }

            purchaseRepository.save(purchase);

        } else {
            throw new RuntimeException("There was an error in placing the order!");
        }
    }

}
