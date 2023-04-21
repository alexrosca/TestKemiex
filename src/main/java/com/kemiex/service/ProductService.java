package com.kemiex.service;

import com.kemiex.entity.Product;
import com.kemiex.entity.ProductState;
import com.kemiex.entity.ProductType;
import com.kemiex.model.AddProductRequest;
import com.kemiex.model.EditProductRequest;
import com.kemiex.model.GetAvailableProductsRequest;
import com.kemiex.repository.ProductRepository;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager em;

    public void addProduct(AddProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setState(ProductState.NORMAL);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setProductType(ProductType.get(productRequest.getType()));
        product.setPrice(Money.of(productRequest.getPriceAmount(), productRequest.getPriceCurrency()));
        product.setCreatedDate(productRequest.getCreatedDate());
        product.setItemsInStock(productRequest.getItemsInStock());
        productRepository.save(product);
    }

    public void editProduct(EditProductRequest productRequest) {
        Optional<Product> productOpt = productRepository.findById(productRequest.getId());
        if (!productOpt.isPresent()) {
            return;
        }
        Product product = productOpt.get();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(Money.of(productRequest.getPriceAmount(), productRequest.getPriceCurrency()));
        product.setItemsInStock(productRequest.getItemsInStock());
        productRepository.save(product);
    }

    public void retireProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findAvailableProduct(productId);
        if (!productOpt.isPresent()) {
            return;
        }
        Product product = productOpt.get();

        product.setState(ProductState.RETIRED);
        productRepository.save(product);

    }

    public List<Product> getAvailableProducts(GetAvailableProductsRequest getAvailableProductsRequest) {

        Set<String> types = getAvailableProductsRequest.getTypes();
        Set<ProductType> productTypes = Collections.emptySet();
        if(types != null) {
            types.stream().map(type -> ProductType.get(type)).collect(Collectors.toSet());
        }

        return productRepository.findAvailableProducts(getAvailableProductsRequest.isOverrideOrder(), productTypes);
    }

    public void buyProduct(Long productId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Optional<Product> productOpt = productRepository.findAvailableProduct(productId);
        if (!productOpt.isPresent()) {
            return;
        }
        Product product = productOpt.get();
        int itemsInStock = product.getItemsInStock();
        if(itemsInStock > 0){
            itemsInStock--;
            product.setItemsInStock(itemsInStock);
            productRepository.save(product);
        }
    }
}
