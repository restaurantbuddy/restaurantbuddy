package net.samuelcmace.restaurantbuddyapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "LOGIN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field representing the username of the login.
     */
    @Column(name = "LOGIN_USERNAME", nullable = false)
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
    @JoinColumn(name = "LOGIN_ID", referencedColumnName = "LOGIN_ID", nullable = false)
    private User user;

}
