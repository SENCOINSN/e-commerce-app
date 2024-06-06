package com.sid.ecommerce.dto;

import com.sid.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod method,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
