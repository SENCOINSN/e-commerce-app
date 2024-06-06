package com.sid.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product is mandatory")
        Integer productId,
        @Positive(message = "quantity should be positive")
        double quantity
) {
}
