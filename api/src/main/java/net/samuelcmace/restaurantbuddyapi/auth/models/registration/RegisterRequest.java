package net.samuelcmace.restaurantbuddyapi.auth.models.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RegisterRequest {

    /**
     * The user's chosen unique username that will be used to log in to the system. As of the time of writing, this
     * username will be permanent and not be changeable once created.
     */
    private String username;

}
