package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for the MENU table in the database.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);

}
