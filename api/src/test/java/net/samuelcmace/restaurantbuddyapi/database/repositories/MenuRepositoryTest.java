package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Menu;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void printAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        System.out.println("menus = " + menus);
    }

}