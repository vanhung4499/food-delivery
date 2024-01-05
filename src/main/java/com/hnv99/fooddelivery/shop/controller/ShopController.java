package com.hnv99.fooddelivery.shop.controller;


import com.hnv99.fooddelivery.shop.domain.ShopSorting;
import com.hnv99.fooddelivery.shop.dto.request.ShopCreateRequest;
import com.hnv99.fooddelivery.shop.dto.request.ShopSearchCondition;
import com.hnv99.fooddelivery.shop.dto.request.ShopUpdateRequest;
import com.hnv99.fooddelivery.shop.dto.response.ShopPagingResponse;
import com.hnv99.fooddelivery.shop.dto.response.ShopResponse;
import com.hnv99.fooddelivery.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopResponse> createShop(@Valid @RequestBody ShopCreateRequest request) {
        ShopResponse response = shopService.createShop(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ShopResponse> getShop(@PathVariable Long shopId) {
        ShopResponse response = shopService.getShop(shopId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ShopPagingResponse> getShops(
            @ModelAttribute ShopSearchCondition shopSearchCondition,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Long cursor,
            @RequestParam(required = false) String sort
    ) {
        ShopPagingResponse responses = shopService.getShops(
                shopSearchCondition,
                size,
                cursor,
                ShopSorting.from(sort)
        );

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<Void> updateShop(
            @PathVariable Long shopId,
            @Valid @RequestBody ShopUpdateRequest.Info request
    ) {
        shopService.updateShop(shopId, request);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{shopId}")
    public ResponseEntity<Void> changeShopStatus(
            @PathVariable Long shopId,
            @Valid @RequestBody ShopUpdateRequest.Status request
    ) {
        shopService.changeShopStatus(shopId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long shopId) {
        shopService.deleteShop(shopId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{shopId}/cook-beginning/{orderId}")
    public ResponseEntity<Void> startCooking(
            @PathVariable Long shopId,
            @PathVariable Long orderId
    ) {
        shopService.startCooking(shopId, orderId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{shopId}/cook-completion/{orderId}")
    public ResponseEntity<Void> finishCooking(
            @PathVariable Long shopId,
            @PathVariable Long orderId
    ) {
        shopService.finishCooking(shopId, orderId);

        return ResponseEntity.ok().build();
    }
}

