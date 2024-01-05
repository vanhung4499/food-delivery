package com.hnv99.fooddelivery.order.dto.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public record SelectedMenuOptionRequest(
        @NotNull(message = "menuOptionGroupId must not be null")
        Long menuOptionGroupId,

        @NotNull(message = "selectedMenuOptions must not be null")
        List<@NotNull Long> selectedMenuOptions
) {
}
