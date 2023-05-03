package net.samuelcmace.restaurantbuddyapi.auth.models.deletion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract MVC model encapsulating the fields necessary for any deletion-related request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DeletionRequest {

    /**
     * The username impacted by the deletion request.
     */
    private String username;

}
