package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the PURCHASE table in the database.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
