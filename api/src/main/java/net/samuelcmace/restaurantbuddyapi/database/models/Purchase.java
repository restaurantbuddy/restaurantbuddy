package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA mapper entity which represents the PURCHASE table in the database.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PURCHASE")
public class Purchase {

    /**
     * The primary key for the table.
     */
    @Id
    @Column(name = "PURCHASE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the time the purchase was made.
     */
    @Column(name = "PURCHASE_TIMESTAMP")
    private Time timestamp;

    /**
     * Field representing the customer who placed the order.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    /**
     * Field containing a collection of items purchased in the order.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PURCHASE_ITEM", joinColumns = @JoinColumn(name = "PURCHASE_ID", referencedColumnName = "PURCHASE_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID"))
    private List<Item> items;

    /**
     * Method to add an item to the purchase.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
    }

}
