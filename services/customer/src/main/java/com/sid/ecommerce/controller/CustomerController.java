package com.sid.ecommerce.controller;

import com.sid.ecommerce.dto.CustomerRequest;
import com.sid.ecommerce.dto.CustomerResponse;
import com.sid.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody  @Valid CustomerRequest request){
     return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody
                                                   @Valid CustomerRequest customerRequest){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
      return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> exist(@PathVariable String id){
        return ResponseEntity.ok(customerService.existById(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> find(@PathVariable String id){
        return ResponseEntity.ok(customerService.find(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        customerService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
