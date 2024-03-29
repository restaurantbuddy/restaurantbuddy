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
 * JPA mapper representing the EMPLOYEE table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee implements UserDetails {

    /**
     * Field identifying the name of the table in the relational database.
     */
    public static final String TABLE_NAME = "EMPLOYEE";

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the salary of the employee.
     */
    @Column(name = "EMPLOYEE_SALARY", nullable = false)
    private Double salary;

    /**
     * Field representing the user associated with the employee (non-nullable from this direction).
     */
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false, unique = true)
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
        return this.getUser().getLogin().getPasswordHash();
    }

    /**
     * Retrieves the username from the login associated with the user.
     *
     * @return The username associated with the login.
     */
    @Override
    public String getUsername() {
        return this.getUser().getLogin().getUsername();
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
