package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the ROLE table in the database.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
