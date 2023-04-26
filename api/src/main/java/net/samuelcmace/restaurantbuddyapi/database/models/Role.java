package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing the ROLE table in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {

    /**
     * Field representing the primary key of the table.
     */
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the name of the role.
     */
    @Column(name = "ROLE_NAME")
    private String name;

    /**
     * Field representing the purpose for which the role exists in the database (optional).
     */
    @Column(name = "ROLE_DESCRIPTION")
    private String description;

    /**
     * JPA entity mapper representing the logins associated with the given role in question.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "LOGIN_ROLE",
            joinColumns = @JoinColumn(
                    name = "ROLE_ID",
                    referencedColumnName = "ROLE_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "LOGIN_ID",
                    referencedColumnName = "LOGIN_ID"
            )
    )
    private List<Login> logins;

    /**
     * Method to add a login to any given role.
     *
     * @param login The login to be assigned to the given role.
     */
    public void addLogin(Login login) {
        if (this.logins == null) this.logins = new ArrayList<>();
        logins.add(login);
    }

}
