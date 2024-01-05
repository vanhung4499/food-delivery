package com.hnv99.fooddelivery.order.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StartedCookingEvent {

    private final Long shopId;

    private final Long orderId;
}
