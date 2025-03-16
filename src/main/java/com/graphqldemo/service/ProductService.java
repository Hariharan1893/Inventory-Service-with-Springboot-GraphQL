package com.graphqldemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphqldemo.entity.Product;
import com.graphqldemo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	//get all the products
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> getProductWithPagination(int limit, int offset){
		return productRepository.findProductsWithPagination(limit, offset);
	}
	

	// get the products by id
	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product Found for id: " + id));
	}

	// to create new product
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	// update the existing product
	public Product updateProduct(Integer id, Product updatedProduct) {
		if (productRepository.existsById(id)) {
			updatedProduct.setId(id);
			return productRepository.save(updatedProduct);
		}
		return null;
	}

	// delete the product
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
