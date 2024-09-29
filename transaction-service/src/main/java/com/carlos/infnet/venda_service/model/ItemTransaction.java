package com.carlos.infnet.venda_service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemTransaction {
    private Long productId;
    private Long quantity;
    private String country;
    private BigDecimal price;
}
