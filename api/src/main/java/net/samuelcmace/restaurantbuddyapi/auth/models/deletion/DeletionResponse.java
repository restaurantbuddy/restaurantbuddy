package net.samuelcmace.restaurantbuddyapi.auth.models.deletion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MVC model to be sent back in all user-deletion-related requests sent to the server.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletionResponse {

    /**
     * The success message for the corresponding deletion request.
     */
    private String successMessage;

    /**
     * An object containing any potential error messages to be sent to the client.
     */
    private String errorMessage;

}
