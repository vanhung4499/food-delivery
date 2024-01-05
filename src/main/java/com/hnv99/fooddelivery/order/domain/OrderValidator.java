package com.hnv99.fooddelivery.order.domain;

import com.hnv99.fooddelivery.global.error.exception.EntityNotFoundException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderValidator {

    private final OrderRepository orderRepository;

    public void validateOrderId(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new EntityNotFoundException(ErrorCode.ORDER_NOT_FOUND);
        }
    }
}
