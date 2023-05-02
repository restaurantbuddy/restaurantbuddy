package net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model associated with assigning the Customer role to an existing User.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class RegisterExistingCustomerRequest extends RegisterExistingRequest {


}
