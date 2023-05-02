package net.samuelcmace.restaurantbuddyapi.auth.models.registration.newuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.auth.models.registration.RegisterRequest;

/**
 * MVC model for registering a new user on the system.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RegisterNewRequest extends RegisterRequest {

    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's phone number.
     */
    private String phone;

    /**
     * The address at which the user lives.
     */
    private String address;

    /**
     * The city in which the user lives.
     */
    private String city;

    /**
     * The state in which the user lives.
     */
    private String state;

    /**
     * The zip code in which the user lives.
     */
    private String zip;

    /**
     * The user's chosen password that will be used to log in to the system. This password will be hashed and salted
     * using the Spring Boot Blowfish password hashing tools.
     */
    private String password;

}
