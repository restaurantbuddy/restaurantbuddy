package net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser;


import lombok.*;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.ISalaried;

/**
 * MVC model associated with assigning the Employee role to an existing User.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterExistingEmployeeRequest extends RegisterExistingRequest implements ISalaried {

    /**
     * The salary associated with the new Employee role.
     */
    public Double salary;

    @Override
    public Double getSalary() {
        return this.salary;
    }

}
