package net.samuelcmace.restaurantbuddyapi.storage.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * JPA mapper entity associated with the CUSTOMER table in the database.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer implements UserDetails {

    /**
     * Field identifying the name of the table in the relational database.
     */
    public static final String TABLE_NAME = "CUSTOMER";

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the user associated with the given customer (non-nullable from this direction).
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false, unique = true)
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(TABLE_NAME));
    }

    @Override
    public String getPassword() {
        return user.getLogin().getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getLogin().getUsername();
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
