package net.samuelcmace.restaurantbuddyapi.owner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {

    /**
     *
     */
    private Long menuId;

    /**
     * The name of the given menu (for example: breakfast, lunch, dinner, specialty, etc.)
     */
    private String menuName;

}
