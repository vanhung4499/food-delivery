package com.hnv99.fooddelivery.order.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeliveryFinishedEvent {

    private final Long orderId;
}
