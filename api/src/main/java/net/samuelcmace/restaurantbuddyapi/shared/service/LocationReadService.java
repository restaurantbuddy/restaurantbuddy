package net.samuelcmace.restaurantbuddyapi.shared.service;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.Location;
import net.samuelcmace.restaurantbuddyapi.database.repositories.LocationRepository;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.AllLocationsModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.LocationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationReadService {

    /**
     * Dependency-injected instance of the LocationRepository.
     */
    private final LocationRepository locationRepository;

    /**
     * Method to retrieve all the restaurant's locations.
     *
     * @return An MVC model containing a set of restaurant locations.
     */
    public AllLocationsModel getAllLocations() {

        List<Location> locations = locationRepository.findAll();

        List<LocationModel> locationMVCModels = new ArrayList<>();

        for (Location location : locations) {
            locationMVCModels.add(LocationModel.builder()
                    .address(location.getAddress())
                    .city(location.getCity())
                    .state(location.getState())
                    .zip(location.getZip())
                    .build());
        }

        return AllLocationsModel.builder().locations(locationMVCModels).build();

    }

    /**
     * Method to retrieve a specific location of the restaurant (by primary key)
     *
     * @return An MVC model containing a set of restaurant locations.
     */
    public LocationModel getLocation(Long id) {

        Optional<Location> locationQuery = locationRepository.findById(id);

        // If the location is empty, return an empty LocationModel.
        if (locationQuery.isPresent()) {
            Location location = locationQuery.get();

            return LocationModel.builder()
                    .address(location.getAddress())
                    .city(location.getCity())
                    .state(location.getState())
                    .zip(location.getZip()).build();
        }

        // Otherwise, return an empty model to the client.
        else {
            return LocationModel.builder().build();
        }

    }

}
