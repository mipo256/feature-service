package com.sivalabs.ft.features.domain;

import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

interface ProductRepository extends ListCrudRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
