package net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model for registering a new restaurant owner.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor
public class RegisterNewOwnerRequest extends RegisterNewRequest {

}
