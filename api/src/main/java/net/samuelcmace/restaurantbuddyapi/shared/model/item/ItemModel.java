package net.samuelcmace.restaurantbuddyapi.shared.model.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MVC model representing a menu item.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {

    /**
     * The ID corresponding to the item.
     */
    private Long id;

    /**
     * The name of the menu item.
     */
    private String name;

    /**
     * The price of the menu item.
     */
    private Double price;

    /**
     * The description of the item.
     */
    private String description;

    /**
     * The image data representing the item (stored in Base64).
     */
    private String image;

    /**
     * The menus on which the item is advertised.
     */
    private List<String> menus;

}
