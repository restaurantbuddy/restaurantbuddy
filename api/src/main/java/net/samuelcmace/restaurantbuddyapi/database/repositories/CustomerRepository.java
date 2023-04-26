package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the CUSTOMER table in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
