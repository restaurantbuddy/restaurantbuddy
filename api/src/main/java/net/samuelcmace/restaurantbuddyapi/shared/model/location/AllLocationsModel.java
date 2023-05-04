package net.samuelcmace.restaurantbuddyapi.shared.model.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.LocationModel;

import java.util.List;

/**
 * MVC model encapsulating a set of restaurant locations.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllLocationsModel {

    /**
     * Field containing the set of LocationModels.
     */
    private List<LocationModel> locations;

}
