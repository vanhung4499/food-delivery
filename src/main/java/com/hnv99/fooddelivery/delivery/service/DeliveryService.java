package com.hnv99.fooddelivery.delivery.service;

import com.hnv99.fooddelivery.delivery.domain.*;
import com.hnv99.fooddelivery.rider.domain.Rider;
import com.hnv99.fooddelivery.rider.domain.RiderRepository;
import com.hnv99.fooddelivery.delivery.dto.response.DeliveryHistoryResponse;
import com.hnv99.fooddelivery.delivery.dto.response.DeliveryResponse;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.EntityNotFoundException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.order.domain.OrderValidator;
import com.hnv99.fooddelivery.order.event.DeliveryFinishedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final DeliveryHistoryRepository deliveryHistoryRepository;

    private final RiderRepository riderRepository;

    private final ApplicationEventPublisher publisher;

    private final OrderValidator orderValidator;

    @Transactional
    public DeliveryResponse createDelivery(Long orderId) {
        orderValidator.validateOrderId(orderId);

        Delivery delivery = new Delivery(orderId);
        Delivery savedDelivery = deliveryRepository.save(delivery);

        DeliveryHistory deliveryHistory = DeliveryHistory.createBeforeDeliveryHistory(savedDelivery);
        DeliveryHistory savedDeliveryHistory = deliveryHistoryRepository.save(deliveryHistory);

        return DeliveryResponse.from(savedDeliveryHistory);
    }

    @Transactional
    public DeliveryHistoryResponse allocateRider(
            Long deliveryId,
            Long riderId
    ) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        delivery.attach(rider);

        DeliveryHistory deliveryHistory = DeliveryHistory.createAllocatedDeliveryHistory(delivery);
        DeliveryHistory savedDeliveryHistory = deliveryHistoryRepository.save(deliveryHistory);

        return DeliveryHistoryResponse.of(rider, savedDeliveryHistory);
    }

    @Transactional
    public DeliveryHistoryResponse startDelivery(
            Long deliveryId,
            Long riderId
    ) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        validateRider(riderId, delivery);

        DeliveryHistory deliveryHistory = DeliveryHistory.createStartDeliveryHistory(delivery);
        DeliveryHistory savedDeliveryHistory = deliveryHistoryRepository.save(deliveryHistory);

        return DeliveryHistoryResponse.of(rider, savedDeliveryHistory);
    }

    @Transactional
    public DeliveryHistoryResponse finishDelivery(
            Long deliveryId,
            Long riderId
    ) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        validateRider(riderId, delivery);

        DeliveryHistory deliveryHistory = DeliveryHistory.createArrivedDeliveryHistory(delivery);
        DeliveryHistory savedDeliveryHistory = deliveryHistoryRepository.save(deliveryHistory);
        publisher.publishEvent(new DeliveryFinishedEvent(delivery.getOrderId()));


        return DeliveryHistoryResponse.of(rider, savedDeliveryHistory);
    }

    public DeliveryHistoryResponse.Multiple getDeliveryHistories(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELIVERY_NOT_FOUND)
                );

        Rider rider = delivery.getRider();

        List<DeliveryHistory> deliveryHistories = deliveryHistoryRepository
                .findByDeliveryId(deliveryId);

        return DeliveryHistoryResponse.Multiple.of(rider, deliveryHistories);
    }

    private void validateRider(
            Long riderId,
            Delivery delivery
    ) {
        Rider rider = delivery.getRider();
        if (!riderId.equals(rider.getId())) {
            throw new BusinessException(ErrorCode.DELIVERY_RIDER_BAD_REQUEST);
        }
    }
}