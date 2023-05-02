package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Employee;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void printAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("employees = " + employees);
    }

}