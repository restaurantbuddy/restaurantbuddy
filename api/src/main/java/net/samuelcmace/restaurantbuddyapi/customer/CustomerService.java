package net.samuelcmace.restaurantbuddyapi.customer;

import net.samuelcmace.restaurantbuddyapi.database.models.Item;
import net.samuelcmace.restaurantbuddyapi.database.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service bean for the Customer controller.
 */
@Service
public class CustomerService {

    public List<Item> getItems(Long itemId)
    {
        throw new UnsupportedOperationException();
    }


}
