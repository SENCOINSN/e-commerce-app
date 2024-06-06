package com.sid.ecommerce.dto;

import com.sid.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
      Integer id,
      BigDecimal amount,
      PaymentMethod paymentMethod,
      Integer orderId,
      String orderReference,
      Customer customer
) {
}
