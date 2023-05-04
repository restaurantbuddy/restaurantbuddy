package net.samuelcmace.restaurantbuddyapi.shared.service;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Item;
import net.samuelcmace.restaurantbuddyapi.database.models.Menu;
import net.samuelcmace.restaurantbuddyapi.database.repositories.ItemRepository;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.ItemModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MVC service used to read menu items from the database.
 */
@Service
@RequiredArgsConstructor
public class ItemReadService {

    /**
     * The ItemRepository instance used by the service.
     */
    private final ItemRepository itemRepository;

    /**
     * Method to query all menu items in the database.
     *
     * @return A set of user models to be sent back to the user.
     */
    public List<ItemModel> findAllMenuItems() {

        List<Item> menuItemJPACollection = itemRepository.findAll();
        List<ItemModel> menuItemModelCollection = new ArrayList<>();

        for (Item item : menuItemJPACollection) {
            menuItemModelCollection.add(buildItemModel(item));
        }

        return menuItemModelCollection;

    }

    /**
     * Method to retrieve a menu item by the primary key.
     *
     * @param menuItemId The menu item id in question.
     * @return A new MVC object containing the new user.
     */
    public ItemModel findMenuItem(Long menuItemId) {

        Optional<Item> itemRequest = itemRepository.findById(menuItemId);

        if (itemRequest.isPresent()) {
            return buildItemModel(itemRequest.get());
        } else {
            return ItemModel.builder().build();
        }

    }

    /**
     * Private method to build an ItemModel out of an Item JPA entity.
     *
     * @param item The item JPA entity in question.
     * @return A new MVC model to be returned to the client in JSON format.
     */
    private ItemModel buildItemModel(Item item) {
        List<String> menus = new ArrayList<>();

        if (!item.getMenus().isEmpty()) {
            for (Menu menu : item.getMenus()) {
                menus.add(menu.getName());
            }
        }

        ItemModel itemModel = ItemModel.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getCost())
                .description(item.getDescription())
                .menus(menus)
                .build();

        return itemModel;
    }

}
