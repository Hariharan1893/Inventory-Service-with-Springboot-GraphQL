package com.graphqldemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.graphqldemo.entity.Product;
import com.graphqldemo.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@QueryMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@QueryMapping
	public Product getProductById(@Argument Integer id) {
		return productService.getProductById(id);
	}
	
	@QueryMapping
	public List<Product> getProductsWithPagination(@Argument int limit, @Argument int offset) {
	    return productService.getProductWithPagination(limit, offset);
	}


	@MutationMapping
	public Product createProduct(@Argument String name, @Argument String category, @Argument Integer price,
			@Argument Integer stock) {
		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		return productService.createProduct(product);
	}

	// Update an existing product
	@MutationMapping
	public Product updateProduct(@Argument Integer id, @Argument String name, @Argument String category,
			@Argument Integer price, @Argument Integer stock) {
		Product updatedProduct = new Product();
		updatedProduct.setName(name);
		updatedProduct.setCategory(category);
		updatedProduct.setPrice(price);
		updatedProduct.setStock(stock);
		return productService.updateProduct(id, updatedProduct);
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Integer id) {
		productService.deleteProduct(id);
		return true;
	}
}
