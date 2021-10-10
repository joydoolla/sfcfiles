package com.sfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfc.dao.UserDao;
import com.sfc.model.Users;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public Users getOne(int userId) {
		return userDao.getOne(userId);
	}
	
	public List<Users> getAllUsers() {
		return userDao.getAllUsers();
	}

	public Users update(Users user) {
		return userDao.update(user);
	}
	
	public boolean userExists(String param) {
		return userDao.userExists(param);
	}
}
