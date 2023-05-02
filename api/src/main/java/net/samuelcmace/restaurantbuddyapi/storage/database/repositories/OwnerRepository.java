package net.samuelcmace.restaurantbuddyapi.storage.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the OWNER table in the database.
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
