package com.hnv99.fooddelivery.order.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static com.hnv99.fooddelivery.order.domain.Order.*;

public record OrderCreateRequest(
        @NotNull(message = "userId must not be null")
        Long userId,

        @NotNull(message = "shopId must not be null")
        Long shopId,

        @Size(max = MAX_ADDRESS_LENGTH, message = "address must be less than or equal to {max} characters")
        @NotNull(message = "address must not be null")
        String address,

        @Size(max = MAX_REQUIREMENT_LENGTH, message = "requirement must be less than or equal to {max} characters")
        String requirement,

        @NotNull(message = "selectedMenus must not be null")
        List<@Valid SelectedMenuRequest> selectedMenus
) {
}
