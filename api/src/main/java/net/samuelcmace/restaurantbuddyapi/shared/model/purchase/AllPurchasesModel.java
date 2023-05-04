package net.samuelcmace.restaurantbuddyapi.shared.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MVC model containing a set of purchases the customer has made.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllPurchasesModel {

    /**
     * Field representing the purchases the customer has placed.
     */
    private List<PurchaseModel> purchases;

}
