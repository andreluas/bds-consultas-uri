package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // SQL NATIVE
    @Query(nativeQuery = true, value = "SELECT categories.name, SUM(products.amount) "
            + "FROM products "
            + "INNER JOIN "
            + "categories ON categories.id = products.id_categories "
            + "GROUP BY 1 ")
    List<CategorySumProjection> listCategorySum();

    // JPQL
    @Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) "
            + "FROM Product obj "
            + "GROUP BY obj.category.name ")
    List<CategorySumDTO> listCategorySumJPQL();
}
