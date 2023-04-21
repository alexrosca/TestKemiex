package com.kemiex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EditProductRequest {
    private Long id;

    private String name;

    private String description;

    private BigDecimal priceAmount;

    private String priceCurrency;

    private int itemsInStock;
}
