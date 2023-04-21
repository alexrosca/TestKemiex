package com.kemiex.controller;

import com.kemiex.model.GetAvailableProductsRequest;
import com.kemiex.service.ProductService;
import com.kemiex.entity.Product;
import com.kemiex.model.AddProductRequest;
import com.kemiex.model.EditProductRequest;
import com.kemiex.model.ProductDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody AddProductRequest productRequest){
        productService.addProduct(productRequest);
    }

    @PatchMapping (value = "/edit")
    public void editProduct(@RequestBody EditProductRequest productRequest){
        productService.editProduct(productRequest);
    }

    @PatchMapping(value = "/retire/{id}")
    public String retireProducts(@PathVariable("id") Long productId){
        productService.retireProduct(productId);
        return productId + "";
    }

    @PostMapping(value = "/getAvailable")
    public List<ProductDto> getAvailableProducts(@RequestBody GetAvailableProductsRequest getAvailableProductsRequest){
        List<Product> availableProducts = productService.getAvailableProducts(getAvailableProductsRequest);
        List<ProductDto> productDtos = convertToDto(availableProducts);
        return productDtos;
    }

    @PatchMapping(value = "/buy/{id}")
    public void buyProduct(@PathVariable("id") Long productId){
        productService.buyProduct(productId);
    }

    private List<ProductDto> convertToDto(List<Product> products) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Product, ProductDto> typeMap = modelMapper.createTypeMap(Product.class, ProductDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(src -> src.getPrice().getNumber(),
                    ProductDto::setPriceAmount);
            mapper.map(src -> src.getPrice().getCurrency().getCurrencyCode(),
                    ProductDto::setPriceCurrency);
        });
        return products.stream().map(product ->typeMap.map(product)).collect(Collectors.toList());
    }
}
