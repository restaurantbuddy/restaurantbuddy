package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing the LOCATION table in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Location.TABLE_NAME)
public class Location {

    /**
     * Field identifying the name of the table in the relational database.
     */
    public static final String TABLE_NAME = "LOCATION";

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "LOCATION_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the name of the location.
     */
    @Column(name = "LOCATION_NAME", nullable = false)
    private String name;

    /**
     * Field representing the address of the location (assuming a US-based address).
     */
    @Column(name = "LOCATION_ADDRESS", nullable = false)
    private String address;

    /**
     * Field representing the city of the location (assuming a US-based address).
     */
    @Column(name = "LOCATION_CITY", nullable = false)
    private String city;

    /**
     * Field representing the state of the location (assuming a US-based address).
     */
    @Column(name = "LOCATION_STATE", nullable = false)
    private String state;

    /**
     * Field representing the zip code of the location (assuming a US-based address).
     */
    @Column(name = "LOCATION_ZIP", nullable = false)
    private String zip;

}
