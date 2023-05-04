package net.samuelcmace.restaurantbuddyapi.customer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MVC model containing a list of items for which the customer would like to place an order.
 * This model is separate and distinct from the PurchaseModel, which deals with orders that have already been placed.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    /**
     * A set of primary keys representing the menu items in question.
     */
    private List<Long> menuItems;

}
