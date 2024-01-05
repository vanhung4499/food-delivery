package com.hnv99.fooddelivery.order.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CookingFinishedEvent {
    private final Long shopId;

    private final Long orderId;
}
