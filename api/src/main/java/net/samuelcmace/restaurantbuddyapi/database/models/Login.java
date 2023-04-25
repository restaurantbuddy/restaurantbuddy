package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing the LOGIN table in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOGIN")
public class Login {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "LOGIN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the username of the login.
     */
    @Column(name = "LOGIN_USERNAME")
    private String username;

    /**
     * Field representing the password hash as it is stored in the database.
     */
    @Column(name = "LOGIN_PASSWORD_HASH")
    private String passwordHash;

    /**
     * JPA mapper representing the roles associated with the login in question.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "LOGIN_ROLE",
            joinColumns = @JoinColumn(
                    name = "LOGIN_ID",
                    referencedColumnName = "LOGIN_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",
                    referencedColumnName = "ROLE_ID"
            )
    )
    private List<Role> roles;

    /**
     * Field that represents the user associated with the login.
     */
    @OneToOne
    @JoinColumn(name = "LOGIN_ID", referencedColumnName = "LOGIN_ID")
    private User user;

    /**
     * Method to add a given role to the login.
     *
     * @param role The role to be added to the login.
     */
    public void addRole(Role role) {
        if (this.roles == null) this.roles = new ArrayList<>();
        this.roles.add(role);
    }

}
