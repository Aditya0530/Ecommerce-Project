package com.adi.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adi.main.dto.ErrorDto;
import com.adi.main.exceptions.ProductException;
import com.adi.main.model.Product;
import com.adi.main.serviceimpl.ProductServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductServiceImpl pi;
	
	@PostMapping("/testException")
	public ResponseEntity<String> testExceptionHandling() {
	    throw new ProductException("This is a test exception!");
	}
	@PostMapping("/postProduct")
	public ResponseEntity<Product> saveProduct (@RequestPart("product")String p,
			@RequestPart("productImage") MultipartFile file) {
		try {
	        Product pr = pi.saveProduct(p, file);
	        return new ResponseEntity<>(pr, HttpStatus.CREATED);
	    } catch (ProductException ex) {
	        // Temporarily log the error
	        //LOG.error("Exception caught: {}", ex.getMessage());
	        throw ex; 
	    }
	}
	}

