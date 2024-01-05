package com.hnv99.fooddelivery.shop.dto.response;

import com.hnv99.fooddelivery.shop.domain.ShopSorting;

import java.util.List;

public record ShopPagingResponse (
        List<ShopResponse> shopResponses,
        int size,
        Long nextCursor,
        ShopSorting sorting,
        boolean isLast
) {
}
