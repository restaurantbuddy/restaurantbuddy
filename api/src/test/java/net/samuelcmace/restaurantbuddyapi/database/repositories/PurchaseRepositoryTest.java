package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.storage.database.models.Purchase;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PurchaseRepositoryTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void printAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        System.out.println("purchases = " + purchases);
    }

}