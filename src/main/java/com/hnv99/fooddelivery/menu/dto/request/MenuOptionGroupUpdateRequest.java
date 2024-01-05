package com.hnv99.fooddelivery.menu.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.menu.domain.Menu.MAX_NAME_LENGTH;

public record MenuOptionGroupUpdateRequest(
        @Size(max = MAX_NAME_LENGTH, message = "Menu option group name can be up to {max} characters.")
        @NotBlank(message = "Menu option group name cannot be empty.")
        String name
) {
}
