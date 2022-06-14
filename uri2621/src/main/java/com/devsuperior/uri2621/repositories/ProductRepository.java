package com.devsuperior.uri2621.repositories;

import java.util.List;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // NATIVE SQL
    @Query(nativeQuery = true, value = "SELECT products.name "
            + "FROM products "
            + "INNER JOIN providers ON products.id_providers = providers.id "
            + "WHERE providers.name LIKE CONCAT(:beginName, '%') "
            + "AND products.amount BETWEEN :min and :max")
    List<ProductMinProjection> searchListProduct(Integer min, Integer max, String beginName);

    // JPQL
    @Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) "
            + "FROM Product obj "
            + "WHERE obj.provider.name LIKE CONCAT(:beginName, '%') "
            + "AND obj.amount BETWEEN :min and :max")
    List<ProductMinDTO> searchListProductJPQL(Integer min, Integer max, String beginName);
}
