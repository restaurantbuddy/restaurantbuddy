package net.samuelcmace.restaurantbuddyapi.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MVC model associated with an authentication request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    /**
     * The newly-created JWT token to be used by the client in all subsequent API requests.
     * <br>
     * The API will then determine the user's privileges based on the JWT token.
     */
    private String jwtToken;

}
