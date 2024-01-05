package com.hnv99.fooddelivery.order.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.shop.domain.Shop;
import com.hnv99.fooddelivery.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private static final int MIN_ORDER_PRICE = 0;

    public static final int MAX_ADDRESS_LENGTH = 50;

    public static final int MAX_REQUIREMENT_LENGTH = 30;

    @Column(name = "address", nullable = false, length = MAX_ADDRESS_LENGTH)
    private String address;

    @Column(name = "requirement", length = MAX_REQUIREMENT_LENGTH)
    private String requirement;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Order(
            String address,
            String requirement,
            Shop shop,
            User user
    ) {
        validateAddress(address);
        validateRequirement(requirement);
        validateShop(shop);
        validateMember(user);
        this.address = address;
        this.requirement = requirement;
        this.shop = shop;
        this.user = user;
        this.orderTime = LocalDateTime.now();
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems.contains(orderItem)) {
            return;
        }
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public void calculateOrderPrice() {
        for (OrderItem orderItem : orderItems) {
            price += orderItem.calculateOrderItemPrice();
        }
        price += shop.getDeliveryTip();
    }

    private void validateAddress(String address) {
        if (address == null || address.length() > MAX_ADDRESS_LENGTH) {
            throw new BusinessException(ErrorCode.USER_ADDRESS_BAD_REQUEST);
        }
    }

    private void validateRequirement(String requirement) {
        if (requirement == null || requirement.length() > MAX_REQUIREMENT_LENGTH) {
            throw new BusinessException(ErrorCode.ORDER_REQUIREMENT_BAD_REQUEST);
        }
    }

    private void validateMember(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_BAD_REQUEST);
        }
    }

    private void validateShop(Shop shop) {
        if (shop == null) {
            throw new BusinessException(ErrorCode.SHOP_BAD_REQUEST);
        }
    }
}
