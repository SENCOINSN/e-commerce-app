package com.sid.ecommerce.mapper;

import com.sid.ecommerce.dto.PaymentRequest;
import com.sid.ecommerce.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .id(request.id())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }
}
