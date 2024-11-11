package com.sivalabs.ft.features.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAllProducts() {
        return productRepository.findAllProductsBy();
    }

    public Optional<ProductDto> findProductByCode(String code) {
        return productRepository.findProductByCode(code);
    }

    public Long createProduct(CreateProductCommand cmd) {
        var product = new Product();
        product.setCode(cmd.code());
        product.setName(cmd.name());
        product.setDescription(cmd.description());
        product.setImageUrl(cmd.imageUrl());
        product.setCreatedBy(cmd.createdBy());
        product.setDisabled(false);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public void updateProduct(UpdateProductCommand cmd) {
        var product = productRepository.findByCode(cmd.code()).orElseThrow();
        product.setName(cmd.name());
        product.setDescription(cmd.description());
        product.setImageUrl(cmd.imageUrl());
        product.setUpdatedBy(cmd.updatedBy());
        productRepository.save(product);
    }
}
