package com.sid.ecommerce.mapper;

import com.sid.ecommerce.dto.ProductPurchaseRequest;
import com.sid.ecommerce.dto.ProductPurchaseResponse;
import com.sid.ecommerce.dto.ProductRequest;
import com.sid.ecommerce.dto.ProductResponse;
import com.sid.ecommerce.model.Category;
import com.sid.ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(Category.builder().id(productRequest.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }


    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
