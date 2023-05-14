package net.samuelcmace.restaurantbuddyapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to enable Cross-Resource-Origin-Sharing (or CORS) on all API requests.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Method to override the default CORS settings for Spring Boot. Since the goal of the API is to remain open for
     * clients to use, all maps and methods will be granted access to the server's defined API routes.
     *
     * @param registry The registry to be modified.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

}
