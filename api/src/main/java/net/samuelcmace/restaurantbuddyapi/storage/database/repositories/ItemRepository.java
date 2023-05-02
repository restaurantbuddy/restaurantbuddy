package net.samuelcmace.restaurantbuddyapi.storage.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the ITEM table in the database.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
