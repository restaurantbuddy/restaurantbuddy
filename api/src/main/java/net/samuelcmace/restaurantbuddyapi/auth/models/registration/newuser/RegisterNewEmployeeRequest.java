package net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser;

import lombok.*;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.ISalaried;

/**
 * MVC model for registering a new employee.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterNewEmployeeRequest extends RegisterNewRequest implements ISalaried {

    /**
     * The salary to be assigned to the new employee.
     */
    private double salary;

    @Override
    public Double getSalary() {
        return this.salary;
    }

}
