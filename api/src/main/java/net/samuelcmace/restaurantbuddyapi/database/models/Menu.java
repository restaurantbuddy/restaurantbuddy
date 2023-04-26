package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA mapper entity representing the MENU table in the database.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")
public class Menu {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "MENU_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the name of the given menu (for example: breakfast, lunch, dinner, specialty, etc.)
     */
    @Column(name = "MENU_NAME", nullable = false)
    private String name;

    /**
     * Collection representing the items being offered on the menu.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "MENU_ITEM",
            joinColumns = @JoinColumn(
                    name = "MENU_ID",
                    referencedColumnName = "MENU_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ITEM_ID",
                    referencedColumnName = "ITEM_ID"
            )
    )
    private List<Item> items;

    /**
     * Method to add a new item to the menu.
     *
     * @param item The item to be added to the menu.
     */
    public void addItem(Item item) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
    }

}
