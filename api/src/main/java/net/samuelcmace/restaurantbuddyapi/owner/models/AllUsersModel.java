package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * MVC model encapsulating a set of users to be returned to the client in JSON format.
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AllUsersModel {

    /**
     * The set of users in question.
     */
    private List<UserModel> users;

}
