package com.hnv99.fooddelivery.menu.dto.response;


import com.hnv99.fooddelivery.menu.domain.MenuOption;

public record MenuOptionCreateResponse(
        Long menuOptionId,
        String name,
        int price
) {

    public static MenuOptionCreateResponse from(MenuOption menuOption) {
        return new MenuOptionCreateResponse(
                menuOption.getId(),
                menuOption.getName(),
                menuOption.getPrice()
        );
    }
}
