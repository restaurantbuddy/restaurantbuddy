package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for the LOGIN table in the database.
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByUsername(String username);

}
