package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.deletion.RoleDeletionRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.deletion.UserDeletionRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.shared.GenericResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC controller responsible for managing application authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * The ApplicationService object used by the controller in its requests.
     */
    private final AuthenticationService authenticationService;

    /**
     * Method to register a new user and assign it to the customer role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/customer/new")
    public ResponseEntity<AuthenticationResponse> registerNewCustomer(
            @RequestBody RegisterNewCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * Method to assign an existing user to the customer role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/customer/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingCustomer(
            @RequestBody RegisterExistingCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * Method to register a new user and assign it to the employee role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/employee/new")
    public ResponseEntity<AuthenticationResponse> registerNewEmployee(
            @RequestBody RegisterNewEmployeeRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * Method to assign an existing user to the employee role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/employee/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingEmployee(
            @RequestBody RegisterExistingEmployeeRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * Method to register a new user and assign it to the owner role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/owner/new")
    public ResponseEntity<AuthenticationResponse> registerNewOwner(
            @RequestBody RegisterNewOwnerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * Method to assign an existing user to the owner role.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/register/owner/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingOwner(
            @RequestBody RegisterExistingOwnerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * Method to remove a user, and it's corresponding login and roles.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object containing details about the operation.
     */
    @PostMapping("/delete/user")
    public ResponseEntity<GenericResponseModel> deleteUser(
            @RequestBody UserDeletionRequest request
    ) {
        return ResponseEntity.ok(authenticationService.delete(request));
    }

    /**
     * Method to remove role from a user.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object containing details about the operation.
     */
    @PostMapping("/delete/role")
    public ResponseEntity<GenericResponseModel> deleteRole(
            @RequestBody RoleDeletionRequest request
    ) {
        return ResponseEntity.ok(authenticationService.delete(request));
    }

    /**
     * Method to authenticate a user in the system.
     *
     * @param request The JSON request from the client.
     * @return A JSON response object that will (if successful) contain the JWT token.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
