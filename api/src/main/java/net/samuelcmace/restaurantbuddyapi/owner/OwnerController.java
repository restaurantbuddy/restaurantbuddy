package net.samuelcmace.restaurantbuddyapi.owner;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.owner.models.AllUsersModel;
import net.samuelcmace.restaurantbuddyapi.owner.models.UserModel;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller to handle all owner-related requests.
 */
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    /**
     * OwnerService
     */
    private final OwnerService ownerService;

    /**
     * Method called to test whether the user is authenticated as an owner.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an owner.
     */
    @GetMapping(value = "/", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isOwner", true);

        return result.toString();

    }

    @GetMapping(value = "/user-management/list-all-users", produces = "application/json")
    public AllUsersModel listAllUsers() {
        return AllUsersModel
                .builder()
                .users(ownerService.findAllUsers())
                .build();
    }

    @GetMapping(value = "/user-management/list-user-by-id/{id}", produces = "application/json")
    public UserModel listUserById(@PathVariable Long id) {
        return ownerService.getUser(id);
    }

    @RequestMapping(value = "/items/add-new-item")
    public boolean addNewMenuItem() {


        return true;
    }

}
