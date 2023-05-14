package net.samuelcmace.restaurantbuddyapi.employee;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Purchase;
import net.samuelcmace.restaurantbuddyapi.database.repositories.PurchaseRepository;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    /**
     * Instance of PurchaseRepository used by the service.
     */
    private final PurchaseRepository purchaseRepository;

    /**
     * Method for an employee to complete an order.
     *
     * @param orderIndex The index that corresponds to the primary key of the order to be completed.
     * @return A GenericResponseModel indicating whether the operation succeeded.
     */
    public GenericResponseModel completeOrder(Long orderIndex) {

        Optional<Purchase> purchaseQuery = purchaseRepository.findById(orderIndex);

        if (purchaseQuery.isPresent()) {

            Purchase purchase = purchaseQuery.get();

            LocalDateTime localDateTime = LocalDateTime.now();
            purchase.setTimeCompleted(localDateTime);

            purchaseRepository.save(purchase);

            return GenericResponseModel.builder().successMessage("The order ID of " + orderIndex + " was completed on " + localDateTime + ".").build();

        } else {
            return GenericResponseModel.builder().errorMessage("The order ID with the specified index of  " + orderIndex + " could not be found!").build();
        }

    }

}
