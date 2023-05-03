package net.samuelcmace.restaurantbuddyapi.owner;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.database.models.User;
import net.samuelcmace.restaurantbuddyapi.database.repositories.*;
import net.samuelcmace.restaurantbuddyapi.owner.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MVC service associated with the OwnerController.
 */
@Service
@RequiredArgsConstructor
public class OwnerService {

    /**
     * The LoginRepository instance used by the service.
     */
    private final LoginRepository loginRepository;

    /**
     * The UserRepository instance used by the service.
     */
    private final UserRepository userRepository;

    /**
     * The CustomerRepository instance used by the service.
     */
    private final CustomerRepository customerRepository;

    /**
     * The EmployeeRepository instance used by the service.
     */
    private final EmployeeRepository employeeRepository;

    /**
     * The OwnerRepository instance used by the service.
     */
    private final OwnerRepository ownerRepository;

    /**
     * Method to query all users in the database.
     *
     * @return A set of user models to be sent back to the user.
     */
    public List<UserModel> findAllUsers() {

        List<User> userCollection = userRepository.findAll();
        List<UserModel> userModelCollection = new ArrayList<UserModel>();

        for (User user : userCollection) {
            userModelCollection.add(buildUserModel(user));
        }

        return userModelCollection;

    }

    /**
     * Method to retrieve a new user by the primary key.
     *
     * @param userId The user id in question.
     * @return A new MVC object containing the new user.
     */
    public UserModel getUser(Long userId) {

        Optional<User> userRequest = userRepository.findById(userId);

        if (userRequest.isPresent()) {
            return buildUserModel(userRequest.get());
        } else {
            return UserModel.builder().build();
        }

    }

    /**
     * Method to build a JSON model out of a User JPA entity.
     *
     * @param user The User JPA entity.
     * @return A new JSON object containing the user details.
     */
    private UserModel buildUserModel(User user) {

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority authority : user.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        return UserModel
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .city(user.getCity())
                .state(user.getState())
                .zip(user.getZip())
                .roles(roles)
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

}
