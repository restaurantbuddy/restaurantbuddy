package net.samuelcmace.restaurantbuddyapi.database.repositories;

import net.samuelcmace.restaurantbuddyapi.database.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * JPA repository for the PURCHASE table in the database.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    /**
     * Custom JPQL query to fetch all orders placed by a specific customer.
     *
     * @param username The login username corresponding to the customer that placed the order.
     * @return A set of purchases corresponding to a given username.
     */
    @Query("select purchase from Purchase purchase inner join Customer customer on purchase.customer = customer inner join User user on customer.user = user inner join Login login on user.login = login where login.username = ?1")
    List<Purchase> findAllByUsername(String username);

    /**
     * Native SQL query to retrieve a set of purchases made by a customer on a given day.
     *
     * @param datePlaced The date the customer placed the order.
     * @return The set of purchases that the customer made.
     */
    @Query(value = "SELECT PURCHASE.* FROM PURCHASE WHERE DATE(PURCHASE.PURCHASE_PLACEMENT_TIME) = ?1", nativeQuery = true)
    List<Purchase> findByDatePlaced(LocalDate datePlaced);

    /**
     * Custom JPQL query to fetch all orders that have not yet been completed.
     *
     * @return A set containing all orders that remain open.
     */
    @Query("select purchase from Purchase purchase where purchase.timeCompleted is null")
    List<Purchase> findOpenOrders();

}
