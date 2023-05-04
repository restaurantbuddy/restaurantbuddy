package net.samuelcmace.restaurantbuddyapi.shared.model.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * MVC model encapsulating a set of items to be returned to the client in JSON format.
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AllItemsModel {

    /**
     * The set of items in question.
     */
    private List<ItemModel> items;

}
