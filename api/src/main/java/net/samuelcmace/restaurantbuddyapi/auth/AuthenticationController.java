package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewOwnerRequest;
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
     * The ApplicationService object used by the controller in it's requests.
     */
    private final AuthenticationService authenticationService;

    /**
     * MVC route called to register a new customer.
     *
     * @param request The request used to authenticate a new customer.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(
            @RequestBody NewCustomerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * MVC route called to register a new employee.
     *
     * @param request The request used to authenticate a new employee.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(
            @RequestBody NewEmployeeRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * MVC route called to register a new owner.
     *
     * @param request The request used to authenticate a new owner.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists.
     */
    @PostMapping("/register/owner")
    public ResponseEntity<AuthenticationResponse> registerOwner(
            @RequestBody NewOwnerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * MVC route called to authenticate an existing customer.
     *
     * @param request The request used to authenticate an existing customer.
     * @return A new response object that will, if successful, contain a newly-instantiated (JWT) login token.
     */
    @PostMapping("/authenticate/customer")
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
    @PostMapping("/authenticate/employee")
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
    @PostMapping("/authenticate/owner")
    public ResponseEntity<AuthenticationResponse> authenticateOwner(
            @RequestBody AuthenticateOwnerRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
