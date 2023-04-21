package com.kemiex.repository;

import com.kemiex.entity.Product;
import com.kemiex.entity.ProductType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomProductRepository {
    List<Product>  findAvailableProducts(boolean overrideOrder, Set<ProductType> productTypes);

    Optional<Product> findAvailableProduct(Long id);
}
