package net.samuelcmace.restaurantbuddyapi.auth.models.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model for registering a new customer.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor
public class NewCustomerRequest extends RegisterRequest {

}
