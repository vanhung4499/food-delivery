package com.hnv99.fooddelivery.menu.dto.response;

import com.hnv99.fooddelivery.menu.domain.MenuOption;
import lombok.Builder;

@Builder
public record MenuOptionResponse(
        Long menuOptionId,
        String name,
        int price
) {

    public static MenuOptionResponse from(MenuOption menuOption) {
        return MenuOptionResponse.builder()
                .menuOptionId(menuOption.getId())
                .name(menuOption.getName())
                .price(menuOption.getPrice())
                .build();
    }
}