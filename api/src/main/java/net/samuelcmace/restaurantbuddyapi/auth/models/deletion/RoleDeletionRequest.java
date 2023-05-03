package net.samuelcmace.restaurantbuddyapi.auth.models.deletion;

import lombok.*;

/**
 * MVC model associated with removing a role from a user.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDeletionRequest extends DeletionRequest {

    /**
     * The role (or authority) in question. This value corresponds to the table name in the database.
     */
    public String role;

}
