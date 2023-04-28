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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(
            @RequestBody NewCustomerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/register/employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(
            @RequestBody NewEmployeeRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/register/owner")
    public ResponseEntity<AuthenticationResponse> registerOwner(
            @RequestBody NewOwnerRequest request
    ) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate/customer")
    public ResponseEntity<AuthenticationResponse> authenticateCustomer(
            @RequestBody AuthenticateCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/authenticate/employee")
    public ResponseEntity<AuthenticationResponse> authenticateEmployee(
            @RequestBody AuthenticateEmployeeRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/authenticate/owner")
    public ResponseEntity<AuthenticationResponse> authenticateOwner(
            @RequestBody AuthenticateOwnerRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
