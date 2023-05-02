package net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser;


import lombok.*;

/**
 * MVC model associated with assigning the Employee role to an existing User.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterExistingEmployeeRequest extends RegisterExistingRequest {

    /**
     * The salary associated with the new Employee role.
     */
    public Double salary;

}
