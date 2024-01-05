package com.hnv99.fooddelivery.delivery.dto.response;

import com.hnv99.fooddelivery.delivery.domain.DeliveryHistory;
import com.hnv99.fooddelivery.delivery.domain.DeliveryStatus;
import lombok.Builder;

@Builder
public record DeliveryResponse(
        Long id,
        DeliveryStatus deliveryStatus
) {

    public static DeliveryResponse from(DeliveryHistory deliveryHistory) {
        return DeliveryResponse.builder()
                .id(deliveryHistory.getDelivery().getId())
                .deliveryStatus(deliveryHistory.getDeliveryStatus())
                .build();
    }
}
