package net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.RegisterRequest;

/**
 * MVC model associated with registering an existing User new with a new role.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public abstract class RegisterExistingRequest extends RegisterRequest {

}
