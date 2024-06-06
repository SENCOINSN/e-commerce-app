package com.sid.ecommerce.client;

import com.sid.ecommerce.config.OAuthFeignConfig;
import com.sid.ecommerce.dto.PurchaseRequest;
import com.sid.ecommerce.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}",
        configuration = OAuthFeignConfig.class
)
public interface ProductClient1 {
    @PostMapping("/purchase")
    List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> requests);
}
