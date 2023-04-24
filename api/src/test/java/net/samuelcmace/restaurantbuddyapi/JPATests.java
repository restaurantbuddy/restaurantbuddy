package net.samuelcmace.restaurantbuddyapi;

import net.samuelcmace.restaurantbuddyapi.database.models.Login;
import net.samuelcmace.restaurantbuddyapi.database.models.Role;
import net.samuelcmace.restaurantbuddyapi.database.repositories.LoginRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JPATests {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void entityTest() {

        List<Login> logins = loginRepository.findAll();

        System.out.println("logins = " + logins);

        assert true;

    }

    @Test
    public void printRolesTest() {
        List<Role> roles = roleRepository.findAll();

        System.out.println("roles = " + roles);
    }

}
