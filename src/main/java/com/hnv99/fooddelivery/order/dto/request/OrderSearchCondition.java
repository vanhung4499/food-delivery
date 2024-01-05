package com.hnv99.fooddelivery.order.dto.request;

import com.hnv99.fooddelivery.order.domain.OrderStatus;
import com.hnv99.fooddelivery.shop.domain.Category;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record OrderSearchCondition(

        List<Category> categories,

        List<OrderStatus> orderStatuses,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime startTime,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime endTime
) {
}
