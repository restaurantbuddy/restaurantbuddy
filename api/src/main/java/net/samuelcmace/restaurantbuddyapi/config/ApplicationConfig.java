package net.samuelcmace.restaurantbuddyapi.config;

import lombok.RequiredArgsConstructor;
import net.samuelcmace.restaurantbuddyapi.storage.database.repositories.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Application-wide configuration containing beans dealing with the security configuration of the project.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Interface for the LOGIN table used to query details corresponding to any given username.
     */
    private final LoginRepository loginRepository;

    /**
     * Method to create a new implementation of the UserDetailsService class.
     *
     * @return A new implementation of the UserDetailsService class tailored to the database specifications.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> loginRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("The requested username was not found!"));
    }

    /**
     * Method to create a new implementation of the UserDetailsService class.
     * <br>
     * In this case, the project is using the DaoAuthenticationProvider, which allows the application to authenticate
     * a given user via username and password.
     *
     * @return A newly-instantiated instance of the DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Method to create a new implementation of the AuthenticationManager tailored to the project's configuration.
     *
     * @param config An object containing the authentication information specific to the project.
     * @return A new implementation of AuthenticationManager to be used by the project in all subsequent requests.
     * @throws Exception Thrown if the method has an issue in generating a new instance of AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Create a new password encoder based on the Blowfish hashing algorithm.
     *
     * @return A newly-instantiated Blowfish password encoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
