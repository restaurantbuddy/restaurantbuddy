package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.User;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void printAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("users = " + users);
    }

}