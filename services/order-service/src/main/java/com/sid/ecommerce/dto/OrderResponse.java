package com.sid.ecommerce.dto;

import com.sid.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod method,
        String CustomerId
        ) {
}
