package com.hnv99.fooddelivery.menu.dto.response;

import com.hnv99.fooddelivery.menu.domain.MenuOptionGroup;

public record MenuOptionGroupCreateResponse(
        Long menuOptionGroupId,
        String name
) {

    public static MenuOptionGroupCreateResponse from(MenuOptionGroup menuOptionGroupEntity) {
        return new MenuOptionGroupCreateResponse(menuOptionGroupEntity.getId(), menuOptionGroupEntity.getName());
    }
}
