package com.hnv99.fooddelivery.order.dto.response;

import com.hnv99.fooddelivery.order.domain.OrderItem;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record SelectedMenuResponse(
        Long menuId,
        int quantity,
        List<Long> selectedOptionIds
) {
    public static List<SelectedMenuResponse> from(List<OrderItem> orderItems) {

        return orderItems.stream()
                .map(SelectedMenuResponse::convertFrom)
                .toList();
    }

    private static SelectedMenuResponse convertFrom(OrderItem orderItem) {

        return SelectedMenuResponse.builder()
                .menuId(orderItem.getMenu().getId())
                .quantity(orderItem.getQuantity())
                .selectedOptionIds(orderItem.getSelectedOptions()
                        .stream()
                        .map(selectedOption -> selectedOption.getMenuOption().getId())
                        .toList())
                .build();
    }
}
