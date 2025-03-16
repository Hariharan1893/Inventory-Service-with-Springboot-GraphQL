package com.graphqldemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.graphqldemo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(String category);
	
	@Query(value = "SELECT * FROM product LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Product> findProductsWithPagination(@Param("limit") int limit, @Param("offset") int offset);

}
