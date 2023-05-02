package net.samuelcmace.restaurantbuddyapi.auth.exception;

import javax.naming.AuthenticationException;

/**
 * Custom exception thrown if the same username already exists in the database.
 */
public class UsernameAlreadyExistsException extends AuthenticationException {

    /**
     * Initializes a new instance of UsernameAlreadyExistsException.
     *
     * @param errorMessage The error message passed to the exception.
     */
    public UsernameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
