package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void printAllLogins() {
        List<Login> logins = loginRepository.findAll();
        System.out.println("logins = " + logins);
    }

}