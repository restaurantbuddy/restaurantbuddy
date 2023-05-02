package net.samuelcmace.restaurantbuddyapi.storage.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the CUSTOMER table in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
