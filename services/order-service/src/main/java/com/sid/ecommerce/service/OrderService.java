package com.sid.ecommerce.service;

import com.sid.ecommerce.client.CustomerClient;
import com.sid.ecommerce.client.PaymentClient;
import com.sid.ecommerce.client.ProductClient;
import com.sid.ecommerce.dto.*;
import com.sid.ecommerce.exception.BusinessException;
import com.sid.ecommerce.kafka.OrderProducer;
import com.sid.ecommerce.mapper.OrderMapper;
import com.sid.ecommerce.model.Order;
import com.sid.ecommerce.model.PaymentMethod;
import com.sid.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order:: customer not found"));
        log.info("customer retrieve  ...{}",customer);
        var purchasesProduct = productClient.purchaseProduct(request.products());
        var order = orderRepository.save(orderMapper.toOrder(request));

        BigDecimal priceTotal = calculatePrice(purchasesProduct);

        saveOrderLine(request.products(),order.getId());

        sendPaymentProcess(order.getId(),order.getReference(),
                priceTotal,customer,request.method());

        sendOrderConfirmation(request.reference(),priceTotal,
                request.method(),customer,purchasesProduct);

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("not order found with id %d ", id)
                ));
    }

    private BigDecimal calculatePrice(List<PurchaseResponse> purchaseResponses){
      BigDecimal total = new BigDecimal(0);
        for(PurchaseResponse purchase : purchaseResponses){
            total = total.add(purchase.price().multiply(BigDecimal.valueOf(purchase.quantity())));
        }
        return total;
    }

    private void saveOrderLine(List<PurchaseRequest> requests,Integer orderId){
        for (PurchaseRequest purchaseRequest : requests) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            orderId,
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }
    }

    private void sendPaymentProcess(Integer orderId,String reference, BigDecimal totalPrice, CustomerResponse customer, PaymentMethod method){
        paymentClient.requestOrderPayment(
                new PaymentRequest(
                        totalPrice,
                        method,
                        orderId,
                        reference,
                        customer
                )
        );
    }

    private void sendOrderConfirmation(String reference,BigDecimal priceTotal,PaymentMethod method,CustomerResponse customer,
                                       List<PurchaseResponse> purchasesProduct){
        orderProducer.sendConfirmation(
                new OrderConfirmation(
                        reference,
                        priceTotal,
                        method,
                        customer,
                        purchasesProduct
                )
        );
    }
}
