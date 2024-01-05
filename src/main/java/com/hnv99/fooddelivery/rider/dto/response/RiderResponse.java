package com.hnv99.fooddelivery.rider.dto.response;

import com.hnv99.fooddelivery.rider.domain.Rider;
import lombok.Builder;

@Builder
public record RiderResponse(
        Long riderId,
        String name,
        String phone
) {

    public static RiderResponse from(Rider rider) {
        return RiderResponse.builder()
                .riderId(rider.getId())
                .name(rider.getName())
                .phone(rider.getPhone())
                .build();
    }
}
