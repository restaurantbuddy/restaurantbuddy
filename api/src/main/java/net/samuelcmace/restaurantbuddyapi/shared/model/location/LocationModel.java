package net.samuelcmace.restaurantbuddyapi.shared.model.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MVC model representing the location of the restaurant.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {

    /**
     * Field containing the primary key of the restaurant location.
     */
    private Long id;

    /**
     * Field containing the restaurant location's name.
     */
    private String name;

    /**
     * Field containing the restaurant location's address.
     */
    private String address;

    /**
     * Field containing the restaurant location's city.
     */
    private String city;

    /**
     * Field containing the state in which the restaurant location is located.
     */
    private String state;

    /**
     * Field containing the zip code of the restaurant location.
     */
    private String zip;

}
