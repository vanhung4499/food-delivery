package com.hnv99.fooddelivery.order.event;

import com.hnv99.fooddelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final OrderService orderService;

    @EventListener(classes = DeliveryFinishedEvent.class)
    public void finishOrder(DeliveryFinishedEvent event) {
        orderService.finishOrder(event.getOrderId());
    }

    @EventListener(classes = StartedCookingEvent.class)
    public void startCooking(StartedCookingEvent event) {
        orderService.startCooking(
                event.getShopId(),
                event.getOrderId()
        );
    }

    @EventListener(classes = CookingFinishedEvent.class)
    public void finishCooking(CookingFinishedEvent event) {
        orderService.finishCooking(
                event.getShopId(),
                event.getOrderId()
        );
    }
}
