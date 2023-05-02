package net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser;

import lombok.*;

/**
 * MVC model for registering a new employee.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterNewEmployeeRequest extends RegisterNewRequest {

    /**
     * The salary to be assigned to the new employee.
     */
    private double salary;

}
