package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    /**
     * The primary key associated with the employee.
     */
    private Long id;

    /**
     * The employee's first name.
     */
    private String firstName;

    /**
     * The employee's last name.
     */
    private String lastName;

    /**
     * The employee's email address.
     */
    private String email;

    /**
     * The employee's phone number.
     */
    private String phone;

    /**
     * The address at which the employee lives.
     */
    private String address;

    /**
     * The city in which the employee lives.
     */
    private String city;

    /**
     * The state in which the employee lives.
     */
    private String state;

    /**
     * The zip code in which the employee lives.
     */
    private String zip;

    /**
     * The list of roles for which the user is authorized.
     */
    private List<String> roles;

    /**
     * The login username associated with the user.
     */
    private String username;

    /**
     * The login password associated with the user.
     */
    private String password;

}
