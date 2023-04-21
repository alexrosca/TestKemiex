package com.kemiex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddProductRequest {
    private Long id;

    private String name;

    private String description;

    private String type;

    private BigDecimal priceAmount;

    private String priceCurrency;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdDate;

    private int itemsInStock;
}
