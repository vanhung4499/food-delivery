package com.hnv99.fooddelivery.shop.service;


import com.hnv99.fooddelivery.global.error.exception.EntityNotFoundException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.order.event.CookingFinishedEvent;
import com.hnv99.fooddelivery.order.event.StartedCookingEvent;
import com.hnv99.fooddelivery.shop.domain.*;
import com.hnv99.fooddelivery.shop.dto.request.ShopCreateRequest;
import com.hnv99.fooddelivery.shop.dto.request.ShopSearchCondition;
import com.hnv99.fooddelivery.shop.dto.request.ShopUpdateRequest;
import com.hnv99.fooddelivery.shop.dto.response.ShopPagingResponse;
import com.hnv99.fooddelivery.shop.dto.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

    private final ShopRepository shopRepository;

    private final ApplicationEventPublisher publisher;

    @Transactional
    public ShopResponse createShop(ShopCreateRequest request) {
        Shop shop = request.toEntity();
        Shop savedShop = shopRepository.save(shop);

        return ShopResponse.from(savedShop);
    }

    public ShopResponse getShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.SHOP_NOT_FOUND)
                );

        return ShopResponse.from(shop);
    }

    public ShopPagingResponse getShops(
            ShopSearchCondition shopSearchCondition,
            int size,
            Long cursor,
            ShopSorting sort
    ) {
        List<Shop> shops = shopRepository.search(
                shopSearchCondition,
                size,
                cursor,
                sort
        );

        return new ShopPagingResponse(
                ShopResponse.from(shops),
                size,
                getNextCursor(shops),
                sort,
                isLast(shops, size)
        );
    }

    private Long getNextCursor(List<Shop> shops) {
        if (shops.isEmpty()) {
            return null;
        }

        int lastIndex = getLastIndex(shops);

        return shops.get(lastIndex).getId();
    }

    private int getLastIndex(List<Shop> shops) {
        return shops.size() - 1;
    }

    private boolean isLast(List<Shop> shops, int size) {
        return shops.size() <= size;
    }

    @Transactional
    public void updateShop(
            Long shopId,
            ShopUpdateRequest.Info request
    ) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.SHOP_NOT_FOUND)
                );

        shop.updateInfo(
                request.name(),
                Category.valueOf(request.category()),
                request.address(),
                request.phone(),
                request.content(),
                request.deliveryTip(),
                request.openingTime(),
                request.closingTime()
        );
    }

    @Transactional
    public void changeShopStatus(
            Long shopId,
            ShopUpdateRequest.Status request
    ) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.SHOP_NOT_FOUND)
                );

        shop.changeStatus(ShopStatus.valueOf(request.status()));
    }

    @Transactional
    public void deleteShop(Long shopId) {
        if (!shopRepository.existsById(shopId)) {
            throw new EntityNotFoundException(ErrorCode.SHOP_NOT_FOUND);
        }

        shopRepository.deleteById(shopId);
    }

    @Transactional
    public void startCooking(
            Long shopId,
            Long orderId
    ) {
        publisher.publishEvent(new StartedCookingEvent(shopId, orderId));
    }

    @Transactional
    public void finishCooking(
            Long shopId,
            Long orderId
    ) {
        publisher.publishEvent(new CookingFinishedEvent(shopId, orderId));
    }
}
