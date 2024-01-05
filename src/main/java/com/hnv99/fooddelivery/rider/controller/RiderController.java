package com.hnv99.fooddelivery.rider.controller;

import com.hnv99.fooddelivery.rider.dto.request.RiderCreateRequest;
import com.hnv99.fooddelivery.rider.dto.response.RiderResponse;
import com.hnv99.fooddelivery.rider.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/riders")
public class RiderController {

    private final RiderService riderService;

    @PostMapping
    public ResponseEntity<RiderResponse> createRider(@Valid @RequestBody RiderCreateRequest request) {
        RiderResponse response = riderService.createRider(request);

        return ResponseEntity.ok(response);
    }
}
