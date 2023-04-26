package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity associated with any given user in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the user's first name.
     */
    @Column(name = "USER_FIRST_NAME", nullable = false)
    private String firstName;

    /**
     * Field representing the user's last name (or surname).
     */
    @Column(name = "USER_LAST_NAME", nullable = false)
    private String lastName;

    /**
     * Field representing the user's email address.
     */
    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    /**
     * Field representing the user's phone number.
     * <br>
     * The phone number assumes a USA/Canadian country code (+1) and that only the digits of the phone number are stored
     * in the database (no spaces, dashes, or parenthesis).
     */
    @Column(name = "USER_PHONE", nullable = false)
    private String phone;

    /**
     * Field representing the address at which the user lives.
     */
    @Column(name = "USER_ADDRESS", nullable = false)
    private String address;

    /**
     * Field representing the city in which the user lives.
     */
    @Column(name = "USER_CITY", nullable = false)
    private String city;

    /**
     * Field representing the state in which the user lives.
     * <br>
     * The database assumes the user lives at a US-based address. The format is the standard for US addresses
     * (for example, "AL" is the abbreviation for the state of Alabama).
     */
    @Column(name = "USER_STATE", nullable = false)
    private String state;

    /**
     * Field representing the zip code in which the user lives.
     * <br>
     * The database assumes the user lives at a US address. The zip code length is only 5 digits in length.
     */
    @Column(name = "USER_ZIP", nullable = false)
    private String zip;

    /**
     * Field representing the login associated with the user.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LOGIN_ID", referencedColumnName = "LOGIN_ID", nullable = false)
    private Login login;

    /**
     * Field representing the possible owner associated with the user (if the user is an owner; this field could be null).
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Owner owner;

    /**
     * Field representing the possible employee associated with the user (if the user is an employee; this field could be null).
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Employee employee;

    /**
     * Field representing the possible customer associated with the user (if the user is an customer; this field could be null).
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Customer customer;

}
