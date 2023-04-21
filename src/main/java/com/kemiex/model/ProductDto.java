package com.kemiex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kemiex.entity.ProductState;
import com.kemiex.entity.ProductType;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private ProductType type;

    private BigDecimal priceAmount;

    private String priceCurrency;

    private Date createdDate;

    private int itemsInStock;
}
