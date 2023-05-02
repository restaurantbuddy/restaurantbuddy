package net.samuelcmace.restaurantbuddyapi;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Main class of the application.
 */
@SpringBootApplication
public class RestaurantbuddyapiApplication {

    /**
     * Method called to launch the Spring Boot application.
     *
     * @param args The arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(RestaurantbuddyapiApplication.class, args);
    }

}
