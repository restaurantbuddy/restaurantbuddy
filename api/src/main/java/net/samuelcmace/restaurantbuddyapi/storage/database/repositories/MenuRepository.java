package net.samuelcmace.restaurantbuddyapi.storage.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the MENU table in the database.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
