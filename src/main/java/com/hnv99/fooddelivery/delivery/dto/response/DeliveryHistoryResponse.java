package com.hnv99.fooddelivery.delivery.dto.response;

import com.hnv99.fooddelivery.delivery.domain.DeliveryHistory;
import com.hnv99.fooddelivery.delivery.domain.DeliveryStatus;
import com.hnv99.fooddelivery.rider.domain.Rider;

import java.time.LocalDateTime;
import java.util.List;

public record DeliveryHistoryResponse(
        Long riderId,
        HistoryInfo historyInfo
) {

    public static DeliveryHistoryResponse of(
            Rider rider,
            DeliveryHistory deliveryHistory
    ) {
        HistoryInfo historyInfo = new HistoryInfo(deliveryHistory.getDeliveryStatus(), deliveryHistory.getCreatedAt());

        return new DeliveryHistoryResponse(rider.getId(), historyInfo);
    }

    public record Multiple(
            Long riderId,
            List<HistoryInfo> historyInfos
    ) {

        public static DeliveryHistoryResponse.Multiple of(
                Rider rider,
                List<DeliveryHistory> deliveryHistories
        ) {
            List<HistoryInfo> historyInfos = deliveryHistories.stream()
                    .map(deliveryHistory -> new HistoryInfo(
                            deliveryHistory.getDeliveryStatus(),
                            deliveryHistory.getCreatedAt()
                    ))
                    .toList();

            return new DeliveryHistoryResponse.Multiple(
                    rider.getId(),
                    historyInfos
            );
        }
    }

    private record HistoryInfo(
            DeliveryStatus deliveryStatus,
            LocalDateTime createdAt
    ) {
    }

    public static HistoryInfo createHistoryInfo(
            DeliveryStatus deliveryStatus,
            LocalDateTime createdAt
    ) {
        return new HistoryInfo(deliveryStatus, createdAt);
    }

    public DeliveryStatus deliveryStatus() {
        return this.historyInfo.deliveryStatus();
    }
}
