package com.sid.ecommerce.service;

import com.sid.ecommerce.client.CustomerClient;
import com.sid.ecommerce.client.PaymentClient;
import com.sid.ecommerce.client.ProductClient;
import com.sid.ecommerce.dto.*;
import com.sid.ecommerce.exception.BusinessException;
import com.sid.ecommerce.kafka.OrderProducer;
import com.sid.ecommerce.mapper.OrderMapper;
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
        //check the customer -->OpenFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order:: customer not found"));

        log.info("customer retrieve  ...{}",customer);

        //purchase the products -->product-ms(RestTemplate)
        var purchasesProduct = productClient.purchaseProduct(request.products());
        var order = orderRepository.save(orderMapper.toOrder(request));
        //persist orderline
        //loop to calculate price total order
        BigDecimal priceTotal = new BigDecimal(0);
        for(PurchaseResponse purchase : purchasesProduct){
            priceTotal = priceTotal.add(purchase.price().multiply(BigDecimal.valueOf(purchase.quantity())));
        }

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //done start payment process

        paymentClient.requestOrderPayment(new PaymentRequest(
                priceTotal,
                request.method(),
                order.getId(),
                order.getReference(),
                customer
        ));
        // send the order confirmation--> notif ms (kafka)
        orderProducer.sendConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        priceTotal,
                        request.method(),
                        customer,
                        purchasesProduct
                )
        );
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
}
