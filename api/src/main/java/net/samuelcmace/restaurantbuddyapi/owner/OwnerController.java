package net.samuelcmace.restaurantbuddyapi.owner;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.AllLocationsModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.LocationModel;
import net.samuelcmace.restaurantbuddyapi.database.models.Owner;
import net.samuelcmace.restaurantbuddyapi.owner.models.AllUsersModel;
import net.samuelcmace.restaurantbuddyapi.owner.models.UserModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.AllItemsModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.item.ItemModel;
import net.samuelcmace.restaurantbuddyapi.shared.service.ItemReadService;
import net.samuelcmace.restaurantbuddyapi.shared.service.LocationReadService;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller to handle all owner-related requests.
 */
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    /**
     * OwnerService instance associated with the service.
     */
    private final OwnerService ownerService;

    /**
     * ItemReadService instance associated with the service.
     */
    private final ItemReadService itemReadService;

    /**
     * LocationReadService instance associated with the service.
     */
    private final LocationReadService locationReadService;

    /**
     * Method called to test whether the user is authenticated as an owner.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an owner.
     */
    @GetMapping("/")
    public GenericResponseModel index() {
        return GenericResponseModel.builder().successMessage("Controller: " + Owner.TABLE_NAME).build();
    }

    /**
     * MVC route to list all the users in the system.
     *
     * @return A model encapsulating a set containing all the users in the system and their details (in JSON format).
     */
    @GetMapping(value = "/user-management/read/all")
    public AllUsersModel listAllUsers() {
        return AllUsersModel
                .builder()
                .users(ownerService.findAllUsers())
                .build();
    }

    /**
     * MVC route to list the user details for one specific user (supplied in the route).
     *
     * @param id The primary key of the menu item (as supplied in the path of the request).
     * @return A JSON object containing details about the user.
     */
    @GetMapping(value = "/user-management/read/{id}")
    public UserModel listUserById(@PathVariable Long id) {
        return ownerService.findUser(id);
    }

    /**
     * MVC route to view all menu items stored in the database.
     *
     * @return A JSON object containing the menu items.
     */
    @GetMapping(value = "/item-management/read/all")
    public AllItemsModel readAllItems() {
        return AllItemsModel.builder()
                .items(itemReadService.findAllMenuItems())
                .build();
    }

    /**
     * MVC route to view all menu items stored in the database.
     *
     * @param primaryKey The primary key to be queried.
     * @return A JSON object containing the menu item.
     */
    @GetMapping(value = "/item-management/read/{primaryKey}")
    public ItemModel readMenuItemById(
            @PathVariable Long primaryKey
    ) {
        return itemReadService.findMenuItem(primaryKey);
    }

    /**
     * MVC route to add a new menu item (along with any corresponding menu names, if applicable).
     *
     * @param request The new item to be created.
     * @return A generic JSON model containing any relevant success or error messages.
     */
    @PostMapping(value = "/item-management/create")
    public GenericResponseModel addItem(
            @RequestBody ItemModel request
    ) {
        try {
            ownerService.createItem(request);
            return GenericResponseModel.builder()
                    .successMessage("Object created successfully!")
                    .build();
        } catch (Exception e) {
            return GenericResponseModel.builder()
                    .errorMessage(e.getLocalizedMessage())
                    .build();
        }
    }

    /**
     * MVC route to update an item in the database based on a supplied model.
     *
     * @param request The JSON model sent by the client.
     * @return A generic JSON response model containing any relevant error or debugging messages.
     */
    @PostMapping(value = "/item-management/update")
    public GenericResponseModel updateItem(
            @RequestBody ItemModel request
    ) {
        try {
            ownerService.updateItem(request);
            return GenericResponseModel.builder()
                    .successMessage("Object updated successfully!")
                    .build();
        } catch (Exception e) {
            return GenericResponseModel.builder()
                    .errorMessage(e.getLocalizedMessage())
                    .build();
        }
    }

    /**
     * MVC route to delete an item in the database based on a supplied primary key.
     *
     * @param primaryKey The primary key that corresponds to the item to be deleted.
     * @return A generic JSON response model containing any relevant error or debugging messages.
     */
    @PostMapping(value = "/item-management/delete/{primaryKey}")
    public GenericResponseModel deleteItem(
            @PathVariable Long primaryKey
    ) {
        try {
            ownerService.deleteItem(primaryKey);
            return GenericResponseModel.builder().successMessage("Operation succeeded!").build();
        } catch (Exception e) {
            return GenericResponseModel.builder().build();
        }
    }

    /**
     * MVC route to retrieve all restaurant locations.
     *
     * @return An MVC model corresponding to all the restaurant's locations.
     */
    @GetMapping("/location-management/read/all")
    public AllLocationsModel readAllLocations() {
        return locationReadService.getAllLocations();
    }

    /**
     * MVC route to retrieve a specific restaurant's ID (specified by the primary key).
     *
     * @return An MVC model corresponding to all the restaurant's locations.
     */
    @GetMapping("/location-management/read/{id}")
    public LocationModel readLocation(
            @PathVariable Long id
    ) {
        return locationReadService.getLocation(id);
    }

}
