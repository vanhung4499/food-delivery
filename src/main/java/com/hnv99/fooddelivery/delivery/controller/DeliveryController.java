package com.hnv99.fooddelivery.delivery.controller;


import com.hnv99.fooddelivery.delivery.dto.response.DeliveryHistoryResponse;
import com.hnv99.fooddelivery.delivery.dto.response.DeliveryResponse;
import com.hnv99.fooddelivery.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/{orderId}")
    public ResponseEntity<DeliveryResponse> createDelivery(@PathVariable Long orderId) {
        DeliveryResponse response = deliveryService.createDelivery(orderId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deliveryId}/allocation/{riderId}")
    public ResponseEntity<DeliveryHistoryResponse> allocateRider(
            @PathVariable Long deliveryId,
            @PathVariable Long riderId
    ) {
        DeliveryHistoryResponse response = deliveryService.allocateRider(deliveryId, riderId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deliveryId}/pick-up/{riderId}")
    public ResponseEntity<DeliveryHistoryResponse> startDelivery(
            @PathVariable Long deliveryId,
            @PathVariable Long riderId
    ) {
        DeliveryHistoryResponse response = deliveryService.startDelivery(deliveryId, riderId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deliveryId}/arrival/{riderId}")
    public ResponseEntity<DeliveryHistoryResponse> finishDelivery(
            @PathVariable Long deliveryId,
            @PathVariable Long riderId
    ) {
        DeliveryHistoryResponse response = deliveryService.finishDelivery(deliveryId, riderId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryHistoryResponse.Multiple> getDeliveryHistories(@PathVariable Long deliveryId) {
        DeliveryHistoryResponse.Multiple responses = deliveryService.getDeliveryHistories(deliveryId);

        return ResponseEntity.ok(responses);
    }
}
