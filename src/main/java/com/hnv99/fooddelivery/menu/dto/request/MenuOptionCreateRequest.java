package com.hnv99.fooddelivery.menu.dto.request;

import com.hnv99.fooddelivery.menu.domain.MenuOption;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.menu.domain.Menu.MAX_NAME_LENGTH;
import static com.hnv99.fooddelivery.menu.domain.Menu.MIN_PRICE;

public record MenuOptionCreateRequest(
        @Size(max = MAX_NAME_LENGTH, message = "Menu option group name can be up to {max} characters.")
        @NotBlank(message = "Menu option group name cannot be empty.")
        String name,

        @Min(value = MIN_PRICE, message = "price must be at least {min}.")
        int price
) {
    public MenuOption toEntity() {
        return new MenuOption(name, price);
    }
}
