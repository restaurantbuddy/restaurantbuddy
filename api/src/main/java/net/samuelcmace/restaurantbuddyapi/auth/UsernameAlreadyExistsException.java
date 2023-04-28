package net.samuelcmace.restaurantbuddyapi.auth;

import javax.naming.AuthenticationException;

public class UsernameAlreadyExistsException extends AuthenticationException {

    public UsernameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
