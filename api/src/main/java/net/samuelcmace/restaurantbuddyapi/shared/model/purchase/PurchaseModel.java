package net.samuelcmace.restaurantbuddyapi.shared.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.AllItemsModel;

import java.time.LocalDateTime;

/**
 * MVC model encapsulating a customer's purchase details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {

    /**
     * The primary key of the model.
     */
    private Long id;

    /**
     * The time the order was placed.
     */
    private LocalDateTime timePlaced;

    /**
     * The time the order was completed by the employee.
     */
    private LocalDateTime timeCompleted;

    /**
     * The username corresponding to the customer that placed the order.
     */
    private String customerUsername;

    /**
     * A set of MVC ItemModels contained in the order.
     */
    private AllItemsModel items;

}
