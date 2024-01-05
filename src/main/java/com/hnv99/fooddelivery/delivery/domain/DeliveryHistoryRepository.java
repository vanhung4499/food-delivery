package com.hnv99.fooddelivery.delivery.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {

    @Query(value = "SELECT d FROM DeliveryHistory as d WHERE d.delivery.id = :deliveryId")
    List<DeliveryHistory> findByDeliveryId(@Param("deliveryId") Long deliveryId);
}
