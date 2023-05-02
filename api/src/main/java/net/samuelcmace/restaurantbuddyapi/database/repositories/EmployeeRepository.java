package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the EMPLOYEE table in the database.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
