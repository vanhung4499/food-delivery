package com.hnv99.fooddelivery.order.dto.response;


import com.hnv99.fooddelivery.order.domain.Order;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        Long userId,
        Long shopId,
        String address,
        String requirement,
        List<SelectedMenuResponse> selectedMenus,
        int price
) {

    public static OrderResponse from(Order order) {
        List<SelectedMenuResponse> selectedMenus = SelectedMenuResponse.from(order.getOrderItems());

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .shopId(order.getShop().getId())
                .address(order.getAddress())
                .requirement(order.getRequirement())
                .selectedMenus(selectedMenus)
                .price(order.getPrice())
                .build();
    }
}