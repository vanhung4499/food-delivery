package com.hnv99.fooddelivery.order.dao;

import com.hnv99.fooddelivery.order.domain.OrderRepositoryCustom;
import static com.hnv99.fooddelivery.order.domain.QOrder.*;
import static com.hnv99.fooddelivery.order.domain.QOrderHistory.*;
import static com.hnv99.fooddelivery.order.domain.QOrderItem.*;

import com.hnv99.fooddelivery.order.domain.Order;
import com.hnv99.fooddelivery.order.domain.OrderStatus;
import com.hnv99.fooddelivery.order.dto.request.OrderSearchCondition;
import com.hnv99.fooddelivery.shop.domain.Category;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Order> search(
        Long memberId,
        OrderSearchCondition orderSearchCondition,
        int size,
        Long cursor
    ) {
        return jpaQueryFactory
            .select(order)
            .from(order)
            .where(
                equalCategory(orderSearchCondition.categories()),
                equalOrderStatus(orderSearchCondition.orderStatuses()),
                betweenTime(orderSearchCondition.startTime(), orderSearchCondition.endTime()),
                greaterOrEqualThanCursor(cursor)
            )
            .leftJoin(orderHistory).on(orderHistory.order.eq(order))
            .leftJoin(order.orderItems, orderItem)
            .fetchJoin()
            .limit(size)
            .fetch();
    }

    private Predicate greaterOrEqualThanCursor(Long cursor) {
        return cursor != null ? order.id.goe(cursor) : null;
    }

    private BooleanExpression betweenTime(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return null;
        }

        return order.orderTime.between(start, end);
    }

    private BooleanBuilder equalCategory(List<Category> categories) {
        if (categories == null) {
            return null;
        }

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (Category category : categories) {
            booleanBuilder.or(order.shop.category.eq(category));
        }

        return booleanBuilder;
    }

    private BooleanBuilder equalOrderStatus(List<OrderStatus> orderStatuses) {
        if (orderStatuses == null) {
            return null;
        }

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (OrderStatus orderStatus : orderStatuses) {
            booleanBuilder.or(orderHistory.orderStatus.eq(orderStatus));
        }

        return booleanBuilder;
    }
}
