package com.sid.ecommerce.kafka.order;

import com.sid.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod method,
        CustomerResponse customerResponse,
        List<Product> products
) {
}
