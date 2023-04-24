package net.samuelcmace.restaurantbuddyapi.owner;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

    @GetMapping(value = "/owner", produces = "application/json")
    public String index() {

        JSONObject result = new JSONObject();

        result.put("isOwner", true);

        return result.toString();

    }

}
