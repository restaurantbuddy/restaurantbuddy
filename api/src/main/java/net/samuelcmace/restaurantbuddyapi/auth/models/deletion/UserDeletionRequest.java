package net.samuelcmace.restaurantbuddyapi.auth.models.deletion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model associated with deleting a user, and it's corresponding login and roles.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class UserDeletionRequest extends DeletionRequest {

}
