package net.samuelcmace.restaurantbuddyapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class dealing with the security of the application.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * The instance of JwtAuthenticationFilter used by the class.
     */
    private final JwtAuthenticationFilter jwtAuthFilter;


    /**
     * The instance of AuthenticationProvider used by the class.
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * Method called to set up the security filter chain for the application.
     *
     * @param http An HttpSecurity configuration object to be setup.
     * @return A SecurityFilterChain object built based on the HttpSecurity configuration.
     * @throws Exception Thrown if there is an issue in setting up the security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/authenticate/**").permitAll()
                        .requestMatchers("/auth/register/employee/**").hasAuthority("OWNER")
                        .requestMatchers("/auth/register/owner/**").hasAuthority("OWNER")
                        .requestMatchers("/customer/**").hasAuthority("CUSTOMER")
                        .requestMatchers("/employee/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/owner/**").hasAuthority("OWNER")
                        .anyRequest().denyAll())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
