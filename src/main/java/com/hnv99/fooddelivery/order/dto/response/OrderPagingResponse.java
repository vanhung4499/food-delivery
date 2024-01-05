package com.hnv99.fooddelivery.order.dto.response;

import java.util.List;

public record OrderPagingResponse(
        List<OrderResponse> orderResponses,
        int size,
        Long nextCursor,
        boolean isLast
) {
}
