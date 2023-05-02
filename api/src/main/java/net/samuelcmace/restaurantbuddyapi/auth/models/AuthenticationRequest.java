package net.samuelcmace.restaurantbuddyapi.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract MVC model for an authentication request to the server.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * The username associated with the request.
     */
    private String username;

    /**
     * The password to be sent to the server (before hashing).
     */
    private String password;

}
