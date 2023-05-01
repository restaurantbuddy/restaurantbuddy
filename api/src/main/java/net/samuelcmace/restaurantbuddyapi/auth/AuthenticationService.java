package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticateOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.authentication.AuthenticationRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.NewOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.RegisterRequest;
import net.samuelcmace.restaurantbuddyapi.config.JwtService;
import net.samuelcmace.restaurantbuddyapi.database.models.*;
import net.samuelcmace.restaurantbuddyapi.database.repositories.CustomerRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.EmployeeRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.LoginRepository;
import net.samuelcmace.restaurantbuddyapi.database.repositories.OwnerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The MVC Service called by the AuthenticationController in all of it's requests.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * The LoginRepository instance associated with the service.
     */
    private final LoginRepository loginRepository;

    /**
     * The CustomerRepository instance associated with the service.
     */
    private final CustomerRepository customerRepository;

    /**
     * The EmployeeRepository instance associated with the service.
     */
    private final EmployeeRepository employeeRepository;

    /**
     * The OwnerRepository instance associated with the service.
     */
    private final OwnerRepository ownerRepository;

    /**
     * The JwtService instance associated with the service.
     */
    private final JwtService jwtService;

    /**
     * The PasswordEncoder instance associated with the service.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The AuthenticationManager instance associated with the service.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Method called by the MVC controller to register a new user.
     *
     * @param request The request sent from the client.
     * @return A new response object containing the JWT token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists in the database.
     */
    public AuthenticationResponse register(RegisterRequest request) throws UsernameAlreadyExistsException {

        Optional<Login> login = loginRepository.findByUsername(request.getUsername());

        if (login.isPresent()) {
            throw new UsernameAlreadyExistsException("The requested username already exists!");
        } else {
            Login newLogin = Login
                    .builder()
                    .username(request.getUsername())
                    .passwordHash(passwordEncoder.encode(request.getPassword()))
                    .build();

            User newUser = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .city(request.getCity())
                    .state(request.getState())
                    .zip(request.getZip())
                    .login(newLogin)
                    .build();

            if (request.getClass() == NewCustomerRequest.class) {

                Customer newCustomer = Customer
                        .builder()
                        .user(newUser)
                        .build();

                customerRepository.save(newCustomer);

                var jwtToken = jwtService.generateToken(newCustomer);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == NewEmployeeRequest.class) {

                Employee newEmployee = Employee
                        .builder()
                        .user(newUser)
                        .salary(((NewEmployeeRequest) request).getSalary())
                        .build();

                employeeRepository.save(newEmployee);

                var jwtToken = jwtService.generateToken(newEmployee);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == NewOwnerRequest.class) {

                Owner newOwner = Owner
                        .builder()
                        .user(newUser)
                        .build();

                ownerRepository.save(newOwner);

                var jwtToken = jwtService.generateToken(newOwner);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else {
                return null;
            }
        }
    }

    /**
     * Method called by the MVC controller to authenticate an existing user.
     *
     * @param request The request sent by the client.
     * @return A new response object containing the JWT token.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var login = loginRepository.findByUsername(request.getUsername()).orElseThrow();

        if (request.getClass() == AuthenticateCustomerRequest.class) {

            var jwtToken = jwtService.generateToken(login.getUser().getCustomer());
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();


        } else if (request.getClass() == AuthenticateEmployeeRequest.class) {

            var jwtToken = jwtService.generateToken(login.getUser().getEmployee());
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();

        } else if (request.getClass() == AuthenticateOwnerRequest.class) {

            var jwtToken = jwtService.generateToken(login.getUser().getOwner());
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();

        } else {
            return null;
        }

    }

}
