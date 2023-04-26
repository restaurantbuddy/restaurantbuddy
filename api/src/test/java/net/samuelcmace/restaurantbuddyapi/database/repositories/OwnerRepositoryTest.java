package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void printAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        System.out.println("owners = " + owners);
    }

}