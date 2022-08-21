package com.example.ecommerce.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	private String NotFoundMsg = "Product dosent exist with id - ";
	
	//get all product 
	@GetMapping("/GetProducts")
	public List<Product> getAllProduct()
	{
		return productRepository.findAll();	
	}
	
	//add product rest api
	@PostMapping("/CreateProduct")
	public Product createProduct(@RequestBody Product product) 
	{
		
		return productRepository.save(product);
	}
	
	@PostMapping("/SaveImage")
	public Boolean Saving(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
	    try {
	    	
	    	Product product=productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(NotFoundMsg + id));
	    	
	    	product.setPic(file.getBytes());
	    	productRepository.save(product);
	    	
	    	//byte[] bytedata = file.getBytes();
		} 
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return true;
	}
	
	//get product by seller rest api
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductByProductId(@PathVariable Long id) 
	{
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(NotFoundMsg + id));
		
		return ResponseEntity.ok(product);
	}
	
	//update product rest api
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails)
	{
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(NotFoundMsg + id));
		
		product.setProductName(productDetails.getProductName());
		product.setProductType(productDetails.getProductType());
		product.setProductCategory(productDetails.getProductCategory());
		product.setProductPrice(productDetails.getProductPrice());
		product.setProductSaleBy(productDetails.getProductSaleBy());
		
		Product updateProduct=productRepository.save(product);
		return ResponseEntity.ok(updateProduct);
	}
	
	//delete product rest api
	@DeleteMapping("/products/{id}")
	public ResponseEntity <Map<String, Boolean>>deleteProduct(@PathVariable Long id)
	{
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(NotFoundMsg + id));
		
		productRepository.delete(product);
		Map<String, Boolean>response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}