package net.samuelcmace.restaurantbuddyapi.shared.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseModel {

    /**
     * String object containing any relevant debugging messages to be sent to the client.
     */
    private String successMessage;

    /**
     * String object containing any relevant error messages to be sent to the client.
     */
    private String errorMessage;

}
