package com.devsuperior.uri2602.repositories;

import java.util.List;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // SQL NATIVE
    @Query(nativeQuery = true, value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)")
    List<CustomerMinProjection> searchSql(String state);

    // JPQL
    @Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) FROM Customer obj WHERE UPPER(obj.state) = UPPER(:state)")
    List<CustomerMinDTO> searchJpql(String state);
}
