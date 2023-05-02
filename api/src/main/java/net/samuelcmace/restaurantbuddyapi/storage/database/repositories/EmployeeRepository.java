package net.samuelcmace.restaurantbuddyapi.storage.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the EMPLOYEE table in the database.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
