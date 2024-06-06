package com.sid.ecommerce.service;

import com.sid.ecommerce.dto.ProductPurchaseRequest;
import com.sid.ecommerce.dto.ProductPurchaseResponse;
import com.sid.ecommerce.dto.ProductRequest;
import com.sid.ecommerce.dto.ProductResponse;
import com.sid.ecommerce.exceptions.ProductPurchaseException;
import com.sid.ecommerce.mapper.ProductMapper;
import com.sid.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
       var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
       var storeProducts = repository.findAllByIdInOrderById(productIds);
       if(productIds.size() != storeProducts.size()){
           throw new ProductPurchaseException("One or more products does not exist");
       }
       var storesRequest = request
               .stream()
               .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
               .toList();
       var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
       for(int i=0;i<storeProducts.size();i++){
           var product = storeProducts.get(i);
           var productRequest =storesRequest.get(i);
           if(productRequest.quantity() > product.getAvailableQuantity()){
               throw new ProductPurchaseException("Insufficient stock quantity for product with id = "+product.getId());
           }
           var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
           product.setAvailableQuantity(newAvailableQuantity);
           repository.save(product);
           purchaseProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));

       }
       return purchaseProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("product this id %id not found ",productId)
                ));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
