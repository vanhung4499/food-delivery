package com.hnv99.fooddelivery.menu.dto.response;

import com.hnv99.fooddelivery.menu.domain.Menu;
import com.hnv99.fooddelivery.menu.domain.MenuStatus;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record MenuResponse(
        Long menuId,
        String name,
        int price,
        boolean popularity,
        MenuStatus status,
        List<MenuOptionGroupResponse> menuOptionGroupResponses
) {
    public static MenuResponse from(Menu menu) {
        List<MenuOptionGroupResponse> menuOptionGroupResponses = menu.getMenuOptionGroups()
                .stream()
                .map(MenuOptionGroupResponse::from)
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .popularity(menu.isPopularity())
                .status(menu.getStatus())
                .menuOptionGroupResponses(menuOptionGroupResponses)
                .build();
    }
}
