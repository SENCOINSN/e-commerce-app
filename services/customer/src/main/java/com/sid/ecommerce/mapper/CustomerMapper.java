package com.sid.ecommerce.mapper;

import com.sid.ecommerce.documents.Customer;
import com.sid.ecommerce.dto.CustomerRequest;
import com.sid.ecommerce.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request==null){
            return null;
        }
        return Customer.builder()
                        .id(request.id())
                        .email(request.email())
                        .lastName(request.lastName())
                        .firstName(request.firstName())
                        .address(request.address())
                        .build();

    }

    public CustomerResponse fromCustomer(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
