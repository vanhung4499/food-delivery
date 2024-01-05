package com.hnv99.fooddelivery.menu.dto.request;

import com.hnv99.fooddelivery.menu.domain.Menu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.menu.domain.Menu.*;

public record MenuCreateRequest(
        @Size(max = MAX_NAME_LENGTH, message = "Menu name can be up to {max} characters.")
        @NotBlank(message = "Menu name cannot be empty.")
        String name,

        @Min(value = MIN_PRICE, message = "price must be at least {min}.")
        int price,

        @NotNull(message = "popularity cannot be null.")
        boolean popularity
) {
    public Menu toEntity() {
        return Menu.builder()
                .name(this.name)
                .price(this.price)
                .popularity(this.popularity)
                .build();
    }
}
