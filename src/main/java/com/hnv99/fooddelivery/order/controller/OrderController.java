package com.hnv99.fooddelivery.order.controller;


import com.hnv99.fooddelivery.order.dto.request.OrderCreateRequest;
import com.hnv99.fooddelivery.order.dto.request.OrderSearchCondition;
import com.hnv99.fooddelivery.order.dto.response.OrderPagingResponse;
import com.hnv99.fooddelivery.order.dto.response.OrderResponse;
import com.hnv99.fooddelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        OrderResponse response = orderService.createOrder(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        OrderResponse response = orderService.getOrder(orderId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<OrderPagingResponse> getOrders(
            @RequestHeader Long userId,
            @ModelAttribute OrderSearchCondition orderSearchCondition,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Long cursor
    ) {
        OrderPagingResponse responses = orderService.getOrders(
                userId,
                orderSearchCondition,
                size,
                cursor
        );

        return ResponseEntity.ok(responses);
    }
}