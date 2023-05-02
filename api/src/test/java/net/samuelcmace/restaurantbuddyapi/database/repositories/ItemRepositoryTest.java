package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Item;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void printAllItems() {
        List<Item> items = itemRepository.findAll();
        System.out.println("items = " + items);
    }

}