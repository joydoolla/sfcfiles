package com.sfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfc.dao.ProductDao;
import com.sfc.model.Product;
import com.sfc.model.Users;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserService userService;
	
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	public Product saveProduct(Product product) {
		Users user = userService.getOne(product.getUserId());
		product.setUser(user);
		return productDao.saveProduct(product);
	}

}
