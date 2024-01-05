package com.hnv99.fooddelivery.menu.dto.request;

import com.hnv99.fooddelivery.menu.domain.MenuOptionGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.menu.domain.Menu.MAX_NAME_LENGTH;

public record MenuOptionGroupCreateRequest(
        @Size(max = MAX_NAME_LENGTH, message = "Menu option group name can be up to {max} characters.")
        @NotBlank(message = "Menu option group name cannot be empty.")
        String name
) {
        public MenuOptionGroup toEntity() {
                return new MenuOptionGroup(name);
        }

}
