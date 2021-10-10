package com.sfc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfc.model.Users;
import com.sfc.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/getAllUsers" , method = RequestMethod.GET)
	@ResponseBody
	public List<Users> getAllProducts()
	{
	logger.info(" Start Get All Products ");
	List<Users> usersList=userService.getAllUsers();
	logger.info(" End of Get All Products ");
	return usersList;	
	}
	
	@RequestMapping(value = "/getUserExists" , method = RequestMethod.GET)
	@ResponseBody
	public boolean getUserExists(@RequestParam String parameter)
	{
	  boolean status = false;
	  logger.info(" Start user Exists ");
	  status=userService.userExists(parameter);
	  logger.info(" End user Exists ");
	 return status;	
	}
	
	
	@PatchMapping("activateUser/{userId}")
    Users activateUser(@RequestBody Users user, @PathVariable int userId) {
		Users updateuser =null;
		logger.info(" Updating User Method Starts ");
		try {
			updateuser = userService.getOne(userId);
			updateuser.setStatus(user.getStatus());
			updateuser =userService.update(updateuser);
	    }catch(Exception ex) {
	    	updateuser =null;
	    	logger.info(ex.getMessage());
	    }
		logger.info(" Updating User Method Ends ");
	return updateuser;
    }

}
