package com.sid.ecommerce.client;

import com.sid.ecommerce.config.OAuth2ClientRestTemplate;
import com.sid.ecommerce.dto.PurchaseRequest;
import com.sid.ecommerce.dto.PurchaseResponse;
import com.sid.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    //private final RestTemplate restTemplate;
    private final OAuth2ClientRestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> requests){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(requests,httpHeaders);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<List<PurchaseResponse>>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity =restTemplate.exchange(
                productUrl+"/purchase",
                HttpMethod.POST,
                request,
                responseType

        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("error occured while processing the products purchase "+responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
