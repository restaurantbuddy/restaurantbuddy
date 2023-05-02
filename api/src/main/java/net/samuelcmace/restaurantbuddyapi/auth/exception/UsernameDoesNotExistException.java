package net.samuelcmace.restaurantbuddyapi.auth.exception;

import javax.naming.AuthenticationException;

/**
 * Custom exception thrown if a user tries to utilize a username that does not exist.
 */
public class UsernameDoesNotExistException extends AuthenticationException {

    /**
     * Initializes a new instance of UsernameDoesNotExistException.
     *
     * @param errorMessage The error message in question.
     */
    public UsernameDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }

}
