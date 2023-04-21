package com.kemiex.entity;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.util.Date;

@Entity
@TypeDef(typeClass = MonetaryAmountType.class, defaultForType = MonetaryAmount.class)
@Data
public class Product {
    @Id
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ProductState state;

    @Columns(columns = {
            @Column(name = "price_amount"),
            @Column(name = "price_currency")
    })
    private MonetaryAmount price;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "items_in_stock", nullable = false)
    private int itemsInStock;
}
