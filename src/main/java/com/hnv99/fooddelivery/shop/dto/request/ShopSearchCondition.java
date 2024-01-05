package com.hnv99.fooddelivery.shop.dto.request;

import com.hnv99.fooddelivery.shop.domain.Category;

public record ShopSearchCondition(

        String name,

        Category category,

        String address,

        Integer deliveryTip,

        String menuName
) {
}

