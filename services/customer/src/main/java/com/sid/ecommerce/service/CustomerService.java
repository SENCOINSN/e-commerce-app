package com.sid.ecommerce.service;

import com.sid.ecommerce.documents.Customer;
import com.sid.ecommerce.dto.CustomerRequest;
import com.sid.ecommerce.dto.CustomerResponse;
import com.sid.ecommerce.exceptions.CustomerNotFoundException;
import com.sid.ecommerce.mapper.CustomerMapper;
import com.sid.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request){
     var customer = customerRepository.save(customerMapper.toCustomer(request));
     return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest){
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(()->new CustomerNotFoundException(
                        String.format("customer with id %s not found ",customerRequest.id())
                ));
        mergerCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer,CustomerRequest customerRequest){
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setFirstName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setFirstName(customerRequest.email());
        }
        if(customerRequest.address() !=null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String id) {
        return customerRepository.findById(id)
                .isPresent();
    }

    public CustomerResponse find(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(
                        String.format("this customer with id %s not found ",id)
                ));
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
