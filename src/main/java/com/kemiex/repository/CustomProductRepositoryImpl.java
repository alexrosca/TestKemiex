package com.kemiex.repository;

import com.kemiex.entity.Product;
import com.kemiex.entity.ProductState;
import com.kemiex.entity.ProductType;
import io.hypersistence.utils.hibernate.type.array.EnumArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomProductRepositoryImpl implements CustomProductRepository{

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Product> findAvailableProducts(boolean overrideOrder, Set<ProductType> productTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Product ");
        sb.append("WHERE state ='");
        sb.append(ProductState.NORMAL).append("'");
        if(!productTypes.isEmpty()){
            sb.append(" AND type IN (:types)");
        }
        if(!overrideOrder) {
            sb.append("ORDER BY created_date DESC");
        }

        org.hibernate.query.Query query = em.createQuery(sb.toString(), Product.class).unwrap(org.hibernate.query.Query.class);
        if(!productTypes.isEmpty()) {
            query.setParameter("types", productTypes, EnumArrayType.INSTANCE);
        }
        return query.getResultList();
    }

    @Override
    public Optional<Product> findAvailableProduct(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Product ");
        sb.append("WHERE id =:id AND state ='");
        sb.append(ProductState.NORMAL).append("'");

        Query query = em.createQuery(sb.toString(), Product.class);
        query.setParameter("id", id);
        List<Product> products =  query.getResultList();
        if(products.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(products.get(0));
    }
}
