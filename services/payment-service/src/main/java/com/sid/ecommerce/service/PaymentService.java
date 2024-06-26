package com.sid.ecommerce.service;

import com.sid.ecommerce.dto.PaymentNotificationRequest;
import com.sid.ecommerce.dto.PaymentRequest;
import com.sid.ecommerce.mapper.PaymentMapper;
import com.sid.ecommerce.notification.NotificationProducer;
import com.sid.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public Integer createPayment(PaymentRequest request){
        log.info("process create payment request... {}",request);
        var payment = paymentRepository.save(paymentMapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                       request.orderReference(),
                       request.amount(),
                       request.paymentMethod(),
                       request.customer().firstName(),
                       request.customer().lastName(),
                       request.customer().email()
                )
        );
        return payment.getId();
    }
}
