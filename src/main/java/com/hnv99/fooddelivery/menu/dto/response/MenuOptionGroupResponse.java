package com.hnv99.fooddelivery.menu.dto.response;

import com.hnv99.fooddelivery.menu.domain.MenuOptionGroup;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
@Builder
public record MenuOptionGroupResponse(
        Long menuOptionGroupId,
        String name,
        List<MenuOptionResponse> menuOptionResponses
) {
    public static MenuOptionGroupResponse from(MenuOptionGroup menuOptionGroup) {
        List<MenuOptionResponse> menuOptionResponses = menuOptionGroup.getMenuOptions()
                .stream()
                .map(MenuOptionResponse::from)
                .collect(Collectors.toList());

        return MenuOptionGroupResponse.builder()
                .menuOptionGroupId(menuOptionGroup.getId())
                .name(menuOptionGroup.getName())
                .menuOptionResponses(menuOptionResponses)
                .build();
    }
}
