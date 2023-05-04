package net.samuelcmace.restaurantbuddyapi.about;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import net.samuelcmace.restaurantbuddyapi.shared.model.location.AllLocationsModel;
import net.samuelcmace.restaurantbuddyapi.shared.service.LocationReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * MVC controller containing informational requests that all parties can access (regardless of whether the user
 * is authenticated). These API routes may contain information about the server or about the restaurant.
 */
@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutController {

    /**
     * AboutService instance used by the AboutController.
     */
    private final LocationReadService aboutService;

    /**
     * MVC route displaying the status of the server.
     *
     * @return A GenericResponseModel containing a string indicating the server is running, along with the time that the request was received.
     */
    @GetMapping("/status")
    public GenericResponseModel getStatus() {
        return GenericResponseModel.builder().successMessage("As of " + LocalDateTime.now() + ", the server is online.").build();
    }

    /**
     * MVC route to retrieve the locations of the restaurant.
     *
     * @return A JSON model containing all the restaurant's locations.
     */
    @GetMapping("/locations")
    public AllLocationsModel getLocations() {
        return aboutService.getAllLocations();
    }

}
