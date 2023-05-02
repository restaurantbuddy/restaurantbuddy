package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Customer;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void printAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("customers = " + customers);
    }

}