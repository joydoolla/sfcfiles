package com.sfc.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfc.model.Product;
import com.sfc.service.ProductService;

@RestController
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	   @RequestMapping(value = "/getAllProducts" , method = RequestMethod.GET)
		@ResponseBody
		public List<Product> getAllProducts()
		{
		logger.info(" Start Get All Products ");
		List<Product> productList=productService.getAllProducts();
		logger.info(" End of Get All Products ");
		return productList;	
		}
		
		@RequestMapping(value = "/saveProduct" , method = RequestMethod.POST)
		public Product addProduct(@RequestBody Product product) {
		logger.info(" Start of Save Product  ");
		return productService.saveProduct(product);
		}

}
