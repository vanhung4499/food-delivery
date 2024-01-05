package com.hnv99.fooddelivery.delivery.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.rider.domain.Rider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "deliveries")
public class Delivery extends BaseEntity {

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    public Delivery(Long orderId) {
        this.orderId = orderId;
    }

    public void attach(Rider rider) {
        validateRider(rider);
        this.rider = rider;
    }

    private void validateRider(Rider rider) {
        if (rider == null) {
            throw new BusinessException(ErrorCode.DELIVERY_RIDER_BAD_REQUEST);
        }
    }
}
