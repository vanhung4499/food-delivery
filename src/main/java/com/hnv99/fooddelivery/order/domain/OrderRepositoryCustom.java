package com.hnv99.fooddelivery.order.domain;

import com.hnv99.fooddelivery.order.dto.request.OrderSearchCondition;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> search(
            Long memberId,
            OrderSearchCondition orderSearchCondition,
            int size,
            Long cursor
    );
}
