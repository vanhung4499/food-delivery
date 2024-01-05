package com.hnv99.fooddelivery.shop.domain;

import static com.hnv99.fooddelivery.shop.domain.QShop.*;

import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum ShopSorting {

    DELIVERY_TIP_ASC("deliveryTipAsc", shop.deliveryTip.asc());

    private final String name;
    private final OrderSpecifier<Integer> orderSpecifier;
    private static final Map<String, ShopSorting> SHOP_SORTING_MAP = new HashMap<>();

    static {
        for (ShopSorting sort : values()) {
            SHOP_SORTING_MAP.put(sort.name, sort);
        }
    }

    public static ShopSorting from(String name) {
        if (name == null) {
            return null;
        }

        if (SHOP_SORTING_MAP.containsKey(name)) {
            return SHOP_SORTING_MAP.get(name);
        }

        throw new InvalidValueException(ErrorCode.SHOP_SORT_BAD_REQUEST);
    }
}
