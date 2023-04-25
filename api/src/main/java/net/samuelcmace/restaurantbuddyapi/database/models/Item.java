package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA mapper entity associated with the ITEM table in the database.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the name of the item.
     */
    @Column(name = "ITEM_NAME")
    private String name;

    /**
     * Field representing the cost to the user (before tax) of the item in question.
     */
    @Column(name = "ITEM_COST")
    private Double cost;

    /**
     * Field representing a detailed description of the item in question.
     */
    @Column(name = "ITEM_DESCRIPTION")
    private String description;

    /**
     * Field representing a collection containing the menus which contain the item in question.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MENU_ITEM",
            joinColumns = @JoinColumn(
                    name = "ITEM_ID",
                    referencedColumnName = "ITEM_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "MENU_ID",
                    referencedColumnName = "MENU_ID"
            )
    )
    private List<Menu> menus;
    /**
     * Field containing a collection of all the transactions in which the item was purchased.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PURCHASE_ITEM",
            joinColumns = @JoinColumn(
                    name = "ITEM_ID",
                    referencedColumnName = "ITEM_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "PURCHASE_ID",
                    referencedColumnName = "PURCHASE_ID"
            )
    )
    private List<Purchase> purchases;

    /**
     * Method to add the item in question to a menu.
     *
     * @param menu The item in which the item is to be placed.
     */
    public void addMenu(Menu menu) {
        if (this.menus == null) this.menus = new ArrayList<>();
        this.menus.add(menu);
    }

    /**
     * Method to add this item to a purchase that a customer makes.
     *
     * @param purchase The purchase to which the item is to be attached.
     */
    public void addPurchase(Purchase purchase) {
        if (this.purchases == null) this.purchases = new ArrayList<>();
        this.purchases.add(purchase);
    }

}
