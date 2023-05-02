package net.samuelcmace.restaurantbuddyapi.storage.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        if (this.user.getCustomer() != null)
            authorityList.add(new SimpleGrantedAuthority(Customer.TABLE_NAME));
        if (this.user.getEmployee() != null)
            authorityList.add(new SimpleGrantedAuthority(Employee.TABLE_NAME));
        if (this.user.getOwner() != null)
            authorityList.add(new SimpleGrantedAuthority(Owner.TABLE_NAME));

        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.getPasswordHash();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
