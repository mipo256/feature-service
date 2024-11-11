package com.sivalabs.ft.features.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);

    List<ProductDto> findAllProductsBy();

    Optional<ProductDto> findProductByCode(String code);
}
