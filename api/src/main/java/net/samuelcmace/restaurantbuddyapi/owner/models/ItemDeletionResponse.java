package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MVC model encapsulating any response messages created during the deletion of a menu item.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDeletionResponse {

    /**
     * A string containing any messages of importance to be sent to the client.
     * These are mainly used for debugging purposes.
     */
    private String successMessage;

    /**
     * A string containing any error messages to be displayed to the client.
     */
    private String errorMessage;

}
