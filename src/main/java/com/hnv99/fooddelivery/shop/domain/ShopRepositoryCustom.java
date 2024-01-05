package com.hnv99.fooddelivery.shop.domain;

import com.hnv99.fooddelivery.shop.dto.request.ShopSearchCondition;

import java.util.List;

public interface ShopRepositoryCustom {

    List<Shop> search(
            ShopSearchCondition shopSearchCondition,
            int size,
            Long cursor,
            ShopSorting sorting
    );
}
