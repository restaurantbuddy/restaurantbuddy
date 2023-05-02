package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewRequest;
import net.samuelcmace.restaurantbuddyapi.config.JwtService;
import net.samuelcmace.restaurantbuddyapi.storage.database.models.*;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The MVC Service called by the AuthenticationController in all of its requests.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * The LoginRepository instance associated with the service.
     */
    private final LoginRepository loginRepository;

    /**
     * The UserRepository instance associated with the service.
     */
    private final UserRepository userRepository;

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
     * @return A new JSON response object containing the JWT token.
     * @throws UsernameAlreadyExistsException Thrown if the given username already exists in the database.
     */
    public AuthenticationResponse registerNew(RegisterNewRequest request) throws UsernameAlreadyExistsException {

        Optional<Login> login = loginRepository.findByUsername(request.getUsername());

        if (login.isPresent()) {
            throw new UsernameAlreadyExistsException("The requested username already exists!");
        } else {

            User newUser = registerNewUser(request);

            if (request.getClass() == RegisterNewCustomerRequest.class) {

                Customer newCustomer = addCustomerRole(newUser);

                var jwtToken = jwtService.generateToken(newCustomer);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == RegisterNewEmployeeRequest.class) {

                Double salary = ((RegisterNewEmployeeRequest) request).getSalary();
                Employee newEmployee = addEmployeeRole(newUser, salary);

                var jwtToken = jwtService.generateToken(newEmployee);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == RegisterNewOwnerRequest.class) {

                Owner newOwner = addOwnerRole(newUser);

                var jwtToken = jwtService.generateToken(newOwner);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else {
                return null;
            }
        }
    }

    /**
     * Method to assign a new role to an existing User.
     *
     * @param request The JSON model sent by the client.
     * @return A new JSON response object containing the JWT token.
     */
    public AuthenticationResponse registerExisting(RegisterExistingRequest request) {

        Optional<Login> login = loginRepository.findByUsername(request.getUsername());

        if (login.isPresent()) {

            User existingUser = login.get().getUser();

            if (request.getClass() == RegisterExistingCustomerRequest.class) {

                Customer newCustomer = addCustomerRole(existingUser);

                var jwtToken = jwtService.generateToken(newCustomer);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == RegisterExistingEmployeeRequest.class) {

                Double salary = ((RegisterExistingEmployeeRequest) request).getSalary();
                Employee newEmployee = addEmployeeRole(existingUser, salary);

                var jwtToken = jwtService.generateToken(newEmployee);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else if (request.getClass() == RegisterExistingOwnerRequest.class) {

                Owner newOwner = addOwnerRole(existingUser);

                var jwtToken = jwtService.generateToken(newOwner);
                return AuthenticationResponse.builder().jwtToken(jwtToken).build();

            } else {
                return null;
            }
        } else {
            return null;
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

        var login = loginRepository
                .findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException("The requested username was not found!")
                );

        var jwtToken = jwtService.generateToken(login.getUser().getCustomer());
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();

    }

    /**
     * Private method to register a new user.
     *
     * @param registerNewRequest The JSON model associated with the registration request.
     * @return The newly-created and persisted user object.
     */
    private User registerNewUser(RegisterNewRequest registerNewRequest) {
        Login newLogin = Login
                .builder()
                .username(registerNewRequest.getUsername())
                .passwordHash(passwordEncoder.encode(registerNewRequest.getPassword()))
                .build();

        loginRepository.save(newLogin);

        User newUser = User.builder()
                .firstName(registerNewRequest.getFirstName())
                .lastName(registerNewRequest.getLastName())
                .email(registerNewRequest.getEmail())
                .phone(registerNewRequest.getPhone())
                .address(registerNewRequest.getAddress())
                .city(registerNewRequest.getCity())
                .state(registerNewRequest.getState())
                .zip(registerNewRequest.getZip())
                .login(newLogin)
                .build();

        userRepository.save(newUser);

        return newUser;
    }

    /**
     * Method to add the role of Customer to an existing User.
     *
     * @param existingUser The user for which the customer role should be added.
     * @return The new (or existing) Customer object associated with the User in question.
     */
    private Customer addCustomerRole(User existingUser) {
        if (existingUser.getCustomer() == null) {
            Customer newCustomer = Customer
                    .builder()
                    .user(existingUser)
                    .build();

            customerRepository.save(newCustomer);

            return newCustomer;
        } else {
            return existingUser.getCustomer();
        }
    }

    /**
     * Method to add the role of Employee to an existing User.
     *
     * @param existingUser   The user for which the customer role should be added.
     * @param employeeSalary The Employee's salary passed through the JSON model.
     * @return The new (or existing) Employee object associated with the User in question.
     */
    private Employee addEmployeeRole(User existingUser, Double employeeSalary) {
        if (existingUser.getEmployee() == null) {
            Employee newEmployee = Employee
                    .builder()
                    .user(existingUser)
                    .salary(employeeSalary)
                    .build();

            employeeRepository.save(newEmployee);

            return newEmployee;
        } else {
            return existingUser.getEmployee();
        }
    }

    /**
     * Method to add the role of Owner to an existing User.
     *
     * @param existingUser The user for which the customer role should be added.
     * @return The new (or existing) Owner object associated with the User in question.
     */
    private Owner addOwnerRole(User existingUser) {
        if (existingUser.getOwner() == null) {
            Owner newOwner = Owner
                    .builder()
                    .user(existingUser)
                    .build();

            ownerRepository.save(newOwner);

            return newOwner;
        } else {
            return existingUser.getOwner();
        }
    }

}
