package net.samuelcmace.restaurantbuddyapi.auth.models.registration;

/**
 * Functional interface for assigning a new employee role.
 */
@FunctionalInterface
public interface ISalaried {

    /**
     * Functional interface to retrieve the salary from an MVC employee registration model.
     *
     * @return The salary of the employee to be added.
     */
    Double getSalary();

}
