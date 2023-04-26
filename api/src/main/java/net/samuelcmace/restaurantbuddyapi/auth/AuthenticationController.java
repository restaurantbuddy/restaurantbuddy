package net.samuelcmace.restaurantbuddyapi.auth;

import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/register/employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/register/owner")
    public ResponseEntity<AuthenticationResponse> registerOwner() {
        throw new UnsupportedOperationException();
    }

}
