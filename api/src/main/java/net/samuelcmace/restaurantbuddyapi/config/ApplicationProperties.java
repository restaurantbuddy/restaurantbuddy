package net.samuelcmace.restaurantbuddyapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("custom")
public class ApplicationProperties {

    public String jwtSecretKey;

    @Data
    public static class MinioConfig {

        public String url;

        public String username;

        public String password;

    }

}
