package com.kemiex.model;

import lombok.Data;

import java.util.Set;

@Data
public class GetAvailableProductsRequest {
    private boolean overrideOrder;

    private Set<String> types;
}
