package com.hnv99.fooddelivery.menu.dto.request;

import com.hnv99.fooddelivery.menu.domain.MenuStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.menu.domain.Menu.MAX_NAME_LENGTH;
import static com.hnv99.fooddelivery.menu.domain.Menu.MIN_PRICE;

public record MenuUpdateRequest() {
    public record Info(
            @Size(max = MAX_NAME_LENGTH, message = "Menu name can be up to {max} characters.")
            @NotBlank(message = "Menu name cannot be empty.")
            String name,

            @Min(value = MIN_PRICE, message = "price must be at least {min}.")
            int price
    ) {}

    public record Status(
            MenuStatus status
    ) {}
}
