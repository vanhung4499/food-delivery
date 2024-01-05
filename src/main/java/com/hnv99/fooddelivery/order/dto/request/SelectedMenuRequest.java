package com.hnv99.fooddelivery.order.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.hnv99.fooddelivery.order.domain.OrderItem.*;

public record SelectedMenuRequest(
        @NotNull(message = "menuId must not be null")
        Long menuId,

        @Min(value = MIN_ORDER_QUANTITY, message = "quantity must be greater than or equal to {value}")
        Integer quantity,

        @NotNull(message = "selectedMenuOptions must not be null")
        List<@NotNull SelectedMenuOptionRequest> selectedMenuOptions
) {
}
