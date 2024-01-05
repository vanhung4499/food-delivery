package com.hnv99.fooddelivery.rider.service;

import com.hnv99.fooddelivery.rider.domain.Rider;
import com.hnv99.fooddelivery.rider.domain.RiderRepository;
import com.hnv99.fooddelivery.rider.dto.request.RiderCreateRequest;
import com.hnv99.fooddelivery.rider.dto.response.RiderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;

    public RiderResponse createRider(RiderCreateRequest request) {
        Rider rider = request.toEntity();
        Rider savedRider = riderRepository.save(rider);

        return RiderResponse.from(savedRider);
    }
}
