package com.sid.ecommerce.client;


import com.sid.ecommerce.config.OAuthFeignConfig;
import com.sid.ecommerce.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}",
        configuration = OAuthFeignConfig.class
)
public interface CustomerClient {
    @GetMapping("/{id}")
    Optional<CustomerResponse> findCustomerById(
            @PathVariable("id") String id
    );
}
