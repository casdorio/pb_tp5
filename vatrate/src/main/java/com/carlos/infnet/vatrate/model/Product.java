package com.carlos.infnet.vatrate.model;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private String country;
    private Integer idCategory;

}
