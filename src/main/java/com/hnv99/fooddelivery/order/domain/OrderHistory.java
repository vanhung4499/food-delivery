package com.hnv99.fooddelivery.order.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "order_histories")
public class OrderHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    private OrderHistory(
            Order order,
            OrderStatus orderStatus
    ) {
        validateOrder(order);
        this.order = order;
        this.orderStatus = orderStatus;
    }

    public static OrderHistory createOrderHistory(Order order) {
        return new OrderHistory(
                order,
                OrderStatus.ORDERED
        );
    }

    public static OrderHistory createDeliveredOrderHistory(Order order) {
        return new OrderHistory(
                order,
                OrderStatus.DELIVERED
        );
    }

    public static OrderHistory createStartedCookingOrderHistory(Order order) {
        return new OrderHistory(
                order,
                OrderStatus.COOKING
        );
    }

    public static OrderHistory createCookingCompletedOrderHistory(Order order) {
        return new OrderHistory(
                order,
                OrderStatus.COOK_COMPLETE
        );
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new BusinessException(ErrorCode.ORDER_BAD_REQUEST);
        }
    }
}
