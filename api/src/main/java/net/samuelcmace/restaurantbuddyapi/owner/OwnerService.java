package net.samuelcmace.restaurantbuddyapi.owner;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Item;
import net.samuelcmace.restaurantbuddyapi.database.models.Location;
import net.samuelcmace.restaurantbuddyapi.database.models.Menu;
import net.samuelcmace.restaurantbuddyapi.database.models.User;
import net.samuelcmace.restaurantbuddyapi.database.repositories.ItemRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.LocationRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.MenuRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.UserRepository;
import net.samuelcmace.restaurantbuddyapi.owner.models.AllUsersModel;
import net.samuelcmace.restaurantbuddyapi.owner.models.UserModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.ItemModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.LocationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MVC service associated with the OwnerController.
 */
@Service
@RequiredArgsConstructor
public class OwnerService {

    /**
     * The UserRepository instance used by the service.
     */
    private final UserRepository userRepository;

    /**
     * The ItemRepository instance used by the service.
     */
    private final ItemRepository itemRepository;

    /**
     * The MenuRepository instance used by the service.
     */
    private final MenuRepository menuRepository;

    /**
     * The LocationRepository instance used by the service.
     */
    private final LocationRepository locationRepository;

    /**
     * Method to query all users in the database.
     *
     * @return A set of user models to be sent back to the user.
     */
    public AllUsersModel findAllUsers() {

        List<User> userCollection = userRepository.findAll();
        List<UserModel> userModelCollection = new ArrayList<UserModel>();

        for (User user : userCollection) {
            userModelCollection.add(buildUserModel(user));
        }

        return AllUsersModel.builder().users(userModelCollection).build();

    }

    /**
     * Method to retrieve a new user by the primary key.
     *
     * @param userId The user id in question.
     * @return A new MVC object containing the new user.
     */
    public UserModel findUser(Long userId) {

        Optional<User> userRequest = userRepository.findById(userId);

        if (userRequest.isPresent()) {
            return buildUserModel(userRequest.get());
        } else {
            return UserModel.builder().build();
        }

    }

    /**
     * Method to build a JSON model out of a User JPA entity.
     *
     * @param user The User JPA entity.
     * @return A new JSON object containing the user details.
     */
    private UserModel buildUserModel(User user) {

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority authority : user.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        return UserModel
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .city(user.getCity())
                .state(user.getState())
                .zip(user.getZip())
                .roles(roles)
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

    /**
     * Method to build a new menu JPA entity from a passed in MVC model.
     *
     * @param itemModel The model from which the JPA entity will be created.
     * @throws RuntimeException Thrown if a menu item with the same name already exists in the database.
     */
    public void createItem(ItemModel itemModel) throws Exception {
        // Check JPA repository to see if menu item name already exists.
        List<Item> dbItems = itemRepository.findAll();

        // First, check to see if a menu item with the same name already exists in the database.
        // If there is no match, then add a new menu item. Otherwise, throw an exception.
        if (dbItems.stream().noneMatch(item -> item.getName().equals(itemModel.getName()))) {
            Item item = Item.builder()
                    .name(itemModel.getName())
                    .cost(itemModel.getPrice())
                    .description(itemModel.getDescription())
                    .build();

            itemRepository.save(item);

        } else {
            throw new Exception("A menu with the same name already exists!");
        }
    }

    /**
     * Method to update a given JPA entity based on a JSON model, using the primary key as the point of reference.
     *
     * @param itemModel The request sent by the client.
     * @throws Exception Thrown if the primary key is not present in the database.
     */
    public void updateItem(ItemModel itemModel) throws Exception {

        Optional<Item> jpaItem = itemRepository.findById(itemModel.getId());

        if (jpaItem.isPresent()) {

            Item item = jpaItem.get();

            item.setName(itemModel.getName());
            item.setDescription(itemModel.getDescription());
            item.setCost(itemModel.getPrice());
            updateMenus(itemModel, item);

            itemRepository.save(item);

        } else {
            throw new Exception("Error: The requested primary key is not present in the database! The model could not be updated.");
        }
    }

    /**
     * Method to remove the JPA entity from the database based on the primary key.
     *
     * @param primaryKey The primary key corresponding to the item in question.
     * @throws Exception Thrown if the primary key is not present in the database.
     */
    public void deleteItem(Long primaryKey) throws Exception {
        Optional<Item> jpaItem = itemRepository.findById(primaryKey);

        if (jpaItem.isPresent())
            itemRepository.deleteById(primaryKey);
        else
            throw new Exception("The given primary key was not found in the database. Either the item doesn't exist, or it was deleted by other means.");
    }

    /**
     * Private method to synchronize the menus attached to any given item.
     *
     * @param itemModel The JSON-supplied item model.
     * @param item      The JPA item to be updated.
     */
    private void updateMenus(ItemModel itemModel, Item item) {

        // The fist step is to loop through each of the model menus and check for overlap with the JPA menus.
        itemModel.getMenus().forEach(modelMenu -> {

            // If the menu does not already exist in the database, create a new model and bind it to the new
            // item. Otherwise, link up the existing menu object to the item.
            if (menuRepository.findAll().stream().noneMatch(existingMenu -> existingMenu.getName().equals(modelMenu))) {
                Menu newMenu = Menu.builder().name(modelMenu).build();
                item.addMenu(newMenu);
                menuRepository.save(newMenu);
            } else {
                Optional<Menu> menuRequest = menuRepository.findByName(modelMenu);

                if (menuRequest.isPresent()) {
                    Menu existingMenu = menuRequest.get();
                    item.addMenu(existingMenu);
                }
            }

        });

        // Finally, if the JPA entity contains any more entities that the model does not have, remove them.
        item.getMenus().removeIf(menu -> itemModel.getMenus().contains(menu.getName()));
    }

    /**
     * Method to add a new location to the restaurant.
     *
     * @param newLocation The location supplied in JSON format from the client.
     * @return A GenericResponseModel indicating whether the operation succeeded.
     */
    public GenericResponseModel addNewLocation(LocationModel newLocation) {

        try {

            Location location = Location.builder()
                    .name(newLocation.getName())
                    .address(newLocation.getName())
                    .city(newLocation.getCity())
                    .state(newLocation.getState())
                    .zip(newLocation.getZip())
                    .build();

            this.locationRepository.save(location);

            return GenericResponseModel.builder().successMessage("The new location " + newLocation.getName() + " was saved to the database!").build();

        } catch (Exception e) {

            return GenericResponseModel.builder().errorMessage(e.getLocalizedMessage()).build();

        }

    }

    /**
     * Method to update an existing location in the database.
     *
     * @param locationModel The location model sent from the client.
     * @return A GenericResponseModel indicating whether the operation succeeded.
     */
    public GenericResponseModel updateLocation(LocationModel locationModel) {

        try {

            Optional<Location> locationQuery = this.locationRepository.findById(locationModel.getId());

            if (locationQuery.isPresent()) {

                Location location = locationQuery.get();

                location.setName(locationModel.getName());
                location.setAddress(locationModel.getAddress());
                location.setCity(locationModel.getCity());
                location.setState(locationModel.getState());
                location.setZip(locationModel.getZip());

                this.locationRepository.save(location);

                return GenericResponseModel.builder().successMessage("The location with the primary key of " + locationModel.getId() + " was successfully persisted to the database!").build();

            } else {

                return GenericResponseModel.builder().errorMessage("A location with the specified primary key of " + locationModel.getId() + " could not be found!").build();

            }

        } catch (Exception e) {

            return GenericResponseModel.builder().errorMessage(e.getLocalizedMessage()).build();

        }

    }

    /**
     * Method to delete the specified location from the database.
     *
     * @param id The ID corresponding to the primary key in question.
     * @return A GenericResponseModel indicating whether the operation succeeded.
     */
    public GenericResponseModel deleteLocation(Long id) {

        Optional<Location> locationQuery = this.locationRepository.findById(id);

        if (locationQuery.isPresent()) {

            this.locationRepository.delete(locationQuery.get());
            return GenericResponseModel.builder().successMessage("The location with the primary key of " + id + " was deleted successfully!").build();

        } else {

            return GenericResponseModel.builder().errorMessage("The location with the primary key of " + id + " does not exist!").build();

        }

    }

}
