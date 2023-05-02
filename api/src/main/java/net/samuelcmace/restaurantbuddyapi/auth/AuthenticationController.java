package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.exception.UsernameAlreadyExistsException;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewOwnerRequest;
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
     * MVC route called to register a new customer.
     *
     * @param request The request used to authenticate a new customer.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/customer/new")
    public ResponseEntity<AuthenticationResponse> registerNewCustomer(
            @RequestBody RegisterNewCustomerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * MVC route called to assign the Customer role to an existing User.
     *
     * @param request The request used to assign the role.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/register/customer/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingCustomer(
            @RequestBody RegisterExistingCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * MVC route called to register a new employee.
     *
     * @param request The request used to authenticate a new employee.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/employee/new")
    public ResponseEntity<AuthenticationResponse> registerNewEmployee(
            @RequestBody RegisterNewEmployeeRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * MVC route called to assign the role of Employee to an existing User.
     *
     * @param request The request used to assign the role.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/register/employee/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingEmployee(
            @RequestBody RegisterExistingEmployeeRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * MVC route called to register a new owner.
     *
     * @param request The request used to authenticate a new owner.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/owner/new")
    public ResponseEntity<AuthenticationResponse> registerNewOwner(
            @RequestBody RegisterNewOwnerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.registerNew(request));
    }

    /**
     * MVC route called to register a new owner.
     *
     * @param request The request used to authenticate a new owner.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/owner/existing")
    public ResponseEntity<AuthenticationResponse> registerExistingOwner(
            @RequestBody RegisterExistingOwnerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerExisting(request));
    }

    /**
     * MVC route called to authenticate an existing customer.
     *
     * @param request The request used to authenticate an existing customer.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/authenticate/customer/new")
    public ResponseEntity<AuthenticationResponse> authenticateCustomer(
            @RequestBody AuthenticateCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * MVC route called to authenticate an existing employee.
     *
     * @param request The request used to authenticate an existing employee.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/authenticate/employee/new")
    public ResponseEntity<AuthenticationResponse> authenticateEmployee(
            @RequestBody AuthenticateEmployeeRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * MVC route called to authenticate an existing owner.
     *
     * @param request The request used to authenticate an existing owner.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/authenticate/owner/new")
    public ResponseEntity<AuthenticationResponse> authenticateOwner(
            @RequestBody AuthenticateOwnerRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
