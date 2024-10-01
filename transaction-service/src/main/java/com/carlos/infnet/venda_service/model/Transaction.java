package com.carlos.infnet.venda_service.model;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "transaction")
public class Transaction {
    
    @Id
    private String id;
    private Long userId;
    private List<ItemTransaction> items;
    private BigDecimal totalTaxcost;
    private BigDecimal totalCost;
    private BigDecimal totalCostEndTax;
    private String statusNotification;
    private int notificationsProcessed;

    @Builder.Default 
    private List<StatusChange> statusNotificationHistory = new ArrayList<>();

    public void addStatusChange(String status) {
        this.statusNotificationHistory.add(new StatusChange(status));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusChange {
        private String status;
        private String dateTime;

        public StatusChange(String status) {
            this.status = status;
            this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
