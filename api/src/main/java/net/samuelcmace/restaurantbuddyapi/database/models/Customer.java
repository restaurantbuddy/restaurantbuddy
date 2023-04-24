package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA mapper entity associated with the CUSTOMER table in the database.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "CUSTOMER_ID")
    private Integer id;

    /**
     * Field representing the user associated with the given customer (non-nullable from this direction).
     */
    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

}
