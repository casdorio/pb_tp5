package com.carlos.infnet.vatrate.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemTransaction {
    
    @NotNull
    @Positive
    private Long quantity;

    private Long productId;

    private Country country;

    @NotNull
    @Positive
    private BigDecimal price;

    public void setCountry(String country) {
        if (country != null) {
            try {
                this.country = Country.valueOf(country.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Country is invalid: " + country);
            }
        } else {
            this.country = null;
        }
    }
}
