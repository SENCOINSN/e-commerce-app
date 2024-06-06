package com.sid.ecommerce.mapper;

import com.sid.ecommerce.dto.OrderLineRequest;
import com.sid.ecommerce.dto.OrderLineResponse;
import com.sid.ecommerce.model.Order;
import com.sid.ecommerce.model.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request){
        return OrderLine.builder()
                .id(request.id())
                .order(Order.builder().id(request.orderId()).build())
                .quantity(request.quantity())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
