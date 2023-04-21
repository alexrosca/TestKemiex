package com.kemiex.entity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProductType {
    ITALIAN_PASTA("Italian pasta"),
    GALUSTE_BANATENE("Galuste banatene"),
    SPATZLE("Spatzle");

    private static final Map<String, ProductType> ENUM_TEXT = Stream.of(ProductType.values()).collect(Collectors.toMap(ProductType::getName, Function.identity()));

    private final String name;

    ProductType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static ProductType get (String name) {
        return ENUM_TEXT.get(name);
    }
}
