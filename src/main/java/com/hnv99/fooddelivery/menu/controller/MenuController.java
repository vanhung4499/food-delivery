package com.hnv99.fooddelivery.menu.controller;


import com.hnv99.fooddelivery.menu.dto.request.*;
import com.hnv99.fooddelivery.menu.dto.response.MenuCreateResponse;
import com.hnv99.fooddelivery.menu.dto.response.MenuOptionCreateResponse;
import com.hnv99.fooddelivery.menu.dto.response.MenuOptionGroupCreateResponse;
import com.hnv99.fooddelivery.menu.dto.response.MenuResponse;
import com.hnv99.fooddelivery.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shops")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{shopId}/menus")
    public ResponseEntity<MenuCreateResponse> createMenu(
            @PathVariable Long shopId,
            @Valid @RequestBody MenuCreateRequest request
    ) {
        MenuCreateResponse response = menuService.createMenu(shopId, request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{shopId}/menus/{menuId}/option-groups")
    public ResponseEntity<MenuOptionGroupCreateResponse> createMenuOptionGroup(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuOptionGroupCreateRequest request
    ) {
        MenuOptionGroupCreateResponse response = menuService.createMenuOptionGroup(
                shopId,
                menuId,
                request
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{shopId}/menus/{menuId}/option-groups/{menuOptionGroupId}/options")
    public ResponseEntity<MenuOptionCreateResponse> createMenuOption(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @PathVariable Long menuOptionGroupId,
            @Valid @RequestBody MenuOptionCreateRequest request
    ) {
        MenuOptionCreateResponse response = menuService.createMenuOption(
                shopId,
                menuId,
                menuOptionGroupId,
                request
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shopId}/menus/{menuId}")
    public ResponseEntity<MenuResponse> getMenu(
            @PathVariable Long shopId,
            @PathVariable Long menuId
    ) {
        MenuResponse menuResponse = menuService.getMenu(shopId, menuId);

        return ResponseEntity.ok(menuResponse);
    }

    @PutMapping("/{shopId}/menus/{menuId}")
    public ResponseEntity<Void> updateMenu(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuUpdateRequest.Info request
    ) {
        menuService.updateMenu(
                shopId,
                menuId,
                request
        );

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{shopId}/menus/{menuId}")
    public ResponseEntity<Void> changeMenuStatus(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuUpdateRequest.Status request
    ) {
        menuService.changeMenuStatus(
                shopId,
                menuId,
                request
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{shopId}/menus/{menuId}/option-groups/{menuOptionGroupId}")
    public ResponseEntity<Void> updateMenuOptionGroup(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @PathVariable Long menuOptionGroupId,
            @Valid @RequestBody MenuOptionGroupUpdateRequest request
    ) {
        menuService.updateMenuOptionGroup(
                shopId,
                menuId,
                menuOptionGroupId,
                request
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{shopId}/menus/{menuId}/option-groups/{menuOptionGroupId}/options/{menuOptionId}")
    public ResponseEntity<Void> updateMenuOption(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @PathVariable Long menuOptionGroupId,
            @PathVariable Long menuOptionId,
            @Valid @RequestBody MenuOptionUpdateRequest request
    ) {
        menuService.updateMenuOption(
                shopId,
                menuId,
                menuOptionGroupId,
                menuOptionId,
                request
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{shopId}/menus/{menuId}")
    public ResponseEntity<Void> deleteMenu(
            @PathVariable Long shopId,
            @PathVariable Long menuId
    ) {
        menuService.deleteMenu(shopId, menuId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{shopId}/menus/{menuId}/option-groups/{menuOptionGroupId}")
    public ResponseEntity<Void> deleteMenuOptionGroup(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @PathVariable Long menuOptionGroupId
    ) {
        menuService.deleteMenuOptionGroup(
                shopId,
                menuId,
                menuOptionGroupId
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{shopId}/menus/{menuId}/option-groups/{menuOptionGroupId}/options/{menuOptionId}")
    public ResponseEntity<Void> deleteMenuOption(
            @PathVariable Long shopId,
            @PathVariable Long menuId,
            @PathVariable Long menuOptionGroupId,
            @PathVariable Long menuOptionId
    ) {
        menuService.deleteMenuOption(
                shopId,
                menuId,
                menuOptionGroupId,
                menuOptionId
        );

        return ResponseEntity.ok().build();
    }
}
