package com.carlos.infnet.venda_service.model;

import java.util.List;
import java.math.BigDecimal;

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
}
