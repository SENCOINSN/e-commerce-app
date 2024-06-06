package com.sid.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product is required")
        Integer productId,
        @NotNull(message = "quantity is required")
        double quantity
) {
}
