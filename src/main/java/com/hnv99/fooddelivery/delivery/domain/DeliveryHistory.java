package com.hnv99.fooddelivery.delivery.domain;


import com.hnv99.fooddelivery.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery_histories")
public class DeliveryHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private DeliveryHistory(
            Delivery delivery,
            DeliveryStatus deliveryStatus
    ) {
        this.delivery = delivery;
        this.deliveryStatus = deliveryStatus;
    }

    public static DeliveryHistory createBeforeDeliveryHistory(Delivery delivery) {
        return new DeliveryHistory(delivery, DeliveryStatus.BEFORE_DELIVERY);
    }

    public static DeliveryHistory createStartDeliveryHistory(Delivery delivery) {
        return new DeliveryHistory(delivery, DeliveryStatus.DELIVERING);
    }

    public static DeliveryHistory createAllocatedDeliveryHistory(Delivery delivery) {
        return new DeliveryHistory(delivery, DeliveryStatus.ALLOCATED);
    }

    public static DeliveryHistory createArrivedDeliveryHistory(Delivery delivery) {
        return new DeliveryHistory(delivery, DeliveryStatus.ARRIVED);
    }
}
