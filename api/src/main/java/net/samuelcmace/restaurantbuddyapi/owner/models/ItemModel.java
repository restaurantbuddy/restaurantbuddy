package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {

    /**
     * The ID corresponding to the item.
     */
    public String itemId;

    /**
     * The name of the menu item.
     */
    public String itemName;

    /**
     * The description of the item.
     */
    public String itemDescription;

    /**
     * The image data representing the item (stored in Base64).
     */
    public String itemImage;

}
