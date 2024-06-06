package com.sid.ecommerce.dto;

import com.sid.ecommerce.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should be precised")
        PaymentMethod method,
        @NotNull(message = "customer Id not be null")
        @NotEmpty(message = "customer should be present")
        @NotBlank(message = "customer not be blank")
        String customerId,
        List<PurchaseRequest> products
) {
}
