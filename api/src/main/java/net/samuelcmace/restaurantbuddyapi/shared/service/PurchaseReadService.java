package net.samuelcmace.restaurantbuddyapi.shared.service;

import net.samuelcmace.restaurantbuddyapi.database.models.Purchase;
import net.samuelcmace.restaurantbuddyapi.database.repositories.PurchaseRepository;
import net.samuelcmace.restaurantbuddyapi.shared.model.purchase.AllPurchasesModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.purchase.PurchaseModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * MVC service associated with reading purchase-related inforamtion.
 */
@Service
public class PurchaseReadService {

    /**
     * The PurchaseRepository instance associated with the service.
     */
    private PurchaseRepository purchaseRepository;

    /**
     * The instance of ItemReadService associated with the instance.
     */
    private ItemReadService itemReadService;

    /**
     * Method to retrieve the orders placed by a specific customer.
     *
     * @return A AllPurchasesModel containing the purchases that match the user making the request.
     */
    public AllPurchasesModel getPurchasesByCustomer() {

        // First, retrieve the username of the user placing the request.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Then, place a custom JPQL query based on the customer's username.
        List<Purchase> customerPurchases = purchaseRepository.findAllByUsername(username);

        return buildPurchasesModels(customerPurchases);

    }

    /**
     * Method to retrieve the orders placed by all customers.
     *
     * @return A AllPurchasesModel containing the all the purchases the restaurant has received.
     */
    public AllPurchasesModel getAllPurchases() {
        return buildPurchasesModels(purchaseRepository.findAll());
    }

    /**
     * Method to retrieve the orders placed by all customers.
     *
     * @return A AllPurchasesModel containing the all the purchases the restaurant has received.
     */
    public AllPurchasesModel getDailyPurchases() {
        return buildPurchasesModels(purchaseRepository.findByDatePlaced(LocalDate.now()));
    }

    /**
     * Private method to build an MVC model from a set of purchases.
     *
     * @param purchases The set of purchases to be converted.
     * @return The model containing the purchases.
     */
    private AllPurchasesModel buildPurchasesModels(List<Purchase> purchases) {
        List<PurchaseModel> purchaseModels = new ArrayList<>();

        purchases.forEach(purchase -> {
            purchaseModels.add(buildPurchaseModel(purchase));
        });

        return AllPurchasesModel.builder().purchases(purchaseModels).build();
    }

    /**
     * Method to build an MVC model out of a JPA Purchase entity.
     *
     * @param purchase The Purchase in question.
     * @return A newly-instantiated JSON object.
     */
    private PurchaseModel buildPurchaseModel(Purchase purchase) {
        return PurchaseModel.builder()
                .id(purchase.getId())
                .timePlaced(purchase.getTimePlaced())
                .timeCompleted(purchase.getTimeCompleted())
                .items(itemReadService.buildAllItemsModel(purchase.getItems()))
                .customerUsername(purchase.getCustomer().getUsername())
                .build();
    }

}
