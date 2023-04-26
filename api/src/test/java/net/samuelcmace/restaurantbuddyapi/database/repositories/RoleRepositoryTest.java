package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void printAllRoles() {
        List<Role> roles = roleRepository.findAll();
        System.out.println("roles = " + roles);
    }

}