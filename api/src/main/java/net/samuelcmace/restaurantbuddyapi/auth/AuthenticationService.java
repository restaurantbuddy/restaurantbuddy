package net.samuelcmace.restaurantbuddyapi.auth;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.AuthenticationResponse;
import net.samuelcmace.restaurantbuddyapi.auth.models.deletion.DeletionRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.deletion.RoleDeletionRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.deletion.UserDeletionRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.ISalaried;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.RegisterRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.existinguser.RegisterExistingRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewCustomerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewEmployeeRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewOwnerRequest;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser.RegisterNewRequest;
import net.samuelcmace.restaurantbuddyapi.config.JwtService;
import net.samuelcmace.restaurantbuddyapi.database.models.*;
import net.samuelcmace.restaurantbuddyapi.database.repositories.*;
import net.samuelcmace.restaurantbuddyapi.shared.model.GenericResponseModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
     */
    public AuthenticationResponse registerNew(RegisterNewRequest request) {

        Optional<Login> login = loginRepository.findByUsername(request.getUsername());

        if (login.isPresent()) {
            return AuthenticationResponse.builder().errorMessage("The username already exists!").build();
        } else {
            User newUser = registerNewUser(request);
            return assignRole(request, newUser);
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
            return assignRole(request, existingUser);
        } else {
            return AuthenticationResponse.builder().errorMessage("The specified user does not exist!").build();
        }

    }

    /**
     * Internal method to assign a user to a role.
     *
     * @param request The MVC request that invoked the role assignment.
     * @param user    The user in question for which the role should be assigned.
     * @return The response object to be sent back to the client (which will, if successful, contain a valid JWT token).
     */
    private AuthenticationResponse assignRole(RegisterRequest request, User user) {

        if (request.getClass() == RegisterNewCustomerRequest.class ||
                request.getClass() == RegisterExistingCustomerRequest.class) {

            Customer newCustomer = addCustomerRole(user);

            var jwtToken = jwtService.generateToken(newCustomer);
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();

        } else if (request.getClass() == RegisterNewEmployeeRequest.class ||
                request.getClass() == RegisterExistingEmployeeRequest.class) {

            Double salary = ((ISalaried) request).getSalary();
            Employee newEmployee = addEmployeeRole(user, salary);

            var jwtToken = jwtService.generateToken(newEmployee);
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();

        } else if (request.getClass() == RegisterNewOwnerRequest.class ||
                request.getClass() == RegisterExistingOwnerRequest.class) {

            Owner newOwner = addOwnerRole(user);

            var jwtToken = jwtService.generateToken(newOwner);
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();

        } else {

            return AuthenticationResponse
                    .builder()
                    .errorMessage("Invalid role specified!")
                    .build();

        }

    }

    public GenericResponseModel delete(DeletionRequest request) {

        String responseMessage = "";

        Optional<Login> loginRequest = loginRepository.findByUsername(request.getUsername());

        if (loginRequest.isPresent()) {

            Login login = loginRequest.get();
            User user = login.getUser();

            if (request.getClass() == RoleDeletionRequest.class) {

                RoleDeletionRequest roleDeletionRequest = (RoleDeletionRequest) request;

                switch (roleDeletionRequest.getRole()) {
                    case Customer.TABLE_NAME -> {
                        responseMessage += "Deleting role '" + user.getCustomer().getId() + "'... ";
                        customerRepository.delete(user.getCustomer());
                    }
                    case Employee.TABLE_NAME -> {
                        responseMessage += "Deleting role '" + user.getEmployee().getId() + "'... ";
                        employeeRepository.delete(user.getEmployee());
                    }
                    case Owner.TABLE_NAME -> {
                        responseMessage += "Deleting role '" + user.getOwner().getId() + "'... ";
                        ownerRepository.delete(user.getOwner());
                    }
                    default -> {
                        return GenericResponseModel.builder().errorMessage("The API received an invalid role deletion request!").build();
                    }
                }

            } else if (request.getClass() == UserDeletionRequest.class) {

                if (user != null) {
                    if (user.getCustomer() != null) {
                        responseMessage += "Deleting role '" + user.getCustomer().getId() + "'... ";
                        customerRepository.delete(user.getCustomer());
                    }
                    if (user.getEmployee() != null) {
                        responseMessage += "Deleting role '" + user.getEmployee().getId() + "'... ";
                        employeeRepository.delete(user.getEmployee());
                    }
                    if (user.getOwner() != null) {
                        responseMessage += "Deleting role '" + user.getOwner().getId() + "'... ";
                        ownerRepository.delete(user.getOwner());
                    }

                    responseMessage += "Deleting user '" + user.getId() + "'... ";
                    userRepository.delete(user);
                }

                responseMessage += "Deleting login '" + login.getId() + "'... ";
                loginRepository.delete(login);

            } else {
                return GenericResponseModel.builder().errorMessage("The API received an invalid user deletion request!").build();
            }

        } else {
            return GenericResponseModel.builder().errorMessage("The specified user could not be found!").build();
        }

        return GenericResponseModel.builder().successMessage(responseMessage).build();
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

        Optional<Login> login = loginRepository.findByUsername(request.getUsername());

        if (login.isEmpty()) {
            return AuthenticationResponse.builder().errorMessage("The specified user does not exist!").build();
        } else {
            var jwtToken = jwtService.generateToken(login.get().getUser().getCustomer());
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();
        }

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
