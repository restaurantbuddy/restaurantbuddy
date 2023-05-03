package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MVC model representing an employee in the database.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EmployeeModel extends UserModel {

    /**
     * The salary of the employee.
     */
    private Double salary;

}
