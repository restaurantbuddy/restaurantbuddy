package net.samuelcmace.restaurantbuddyapi.auth.models.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model for a customer authentication request to the server.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor
public class AuthenticateEmployeeRequest extends AuthenticationRequest {
}
