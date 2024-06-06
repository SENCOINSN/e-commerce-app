package com.sid.ecommerce.repository;

import com.sid.ecommerce.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
