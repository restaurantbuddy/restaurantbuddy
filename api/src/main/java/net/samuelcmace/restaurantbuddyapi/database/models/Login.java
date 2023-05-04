package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * JPA entity representing the LOGIN table in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Login.TABLE_NAME)
public class Login implements UserDetails {

    /**
     * Field identifying the name of the table in the relational database.
     */
    public static final String TABLE_NAME = "LOGIN";

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "LOGIN_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the username of the login.
     */
    @Column(name = "LOGIN_USERNAME", nullable = false, unique = true)
    private String username;

    /**
     * Field representing the password hash as it is stored in the database.
     */
    @Column(name = "LOGIN_PASSWORD_HASH", nullable = false)
    private String passwordHash;

    /**
     * Field that represents the user associated with the login.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LOGIN_ID", referencedColumnName = "LOGIN_ID", nullable = false, unique = true)
    private User user;

    /**
     * Retrieves the roles associated with the user for authorization purposes.
     *
     * @return The roles that the user has been granted.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getUser().getAuthorities();
    }

    /**
     * Retrieves the password from the login associated with the user.
     *
     * @return The password associated with the login.
     */
    @Override
    public String getPassword() {
        return this.getPasswordHash();
    }

    /**
     * Retrieves whether the account is expired.
     *
     * @return A flag indicating whether the account is expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Retrieves whether the account is locked.
     *
     * @return A flag indicating whether the account is locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Retrieves whether the account's credentials are expired.
     *
     * @return A flag indicating account's credentials are expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Retrieves whether the account's is enabled.
     *
     * @return A flag indicating whether the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
