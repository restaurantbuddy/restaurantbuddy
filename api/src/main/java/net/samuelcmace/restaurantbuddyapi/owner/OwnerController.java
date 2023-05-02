package net.samuelcmace.restaurantbuddyapi.owner;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller to handle all owner-related requests.
 */
@RestController
public class OwnerController {

    /**
     * Method called to test whether the user is authenticated as an owner.
     *
     * @return A JSON object containing a boolean indicating whether the user is authenticated as an owner.
     */
    @GetMapping(value = "/owner", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isOwner", true);

        return result.toString();

    }

    @RequestMapping(value = "/items/add-new-item")
    public boolean addNewMenuItem()
    {


        return true;
    }

}
