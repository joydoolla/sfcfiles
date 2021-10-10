package com.sfc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sfc.model.Users;
import com.sfc.repository.UserRepository;

@Component
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}
	
	public Users findByUserName(String status, String userName, String mobileNumber) {
		return userRepository.findUserDeails(status,userName,mobileNumber);
	}
	
	public Users getOne(int userId) {
		return userRepository.findByUserId(userId);
	}
	
	public List<Users> getAllUsers() {
		return (List<Users>) userRepository.findAll();
	}
	
	public Users update(Users user) {
		return userRepository.save(user);
	}
	
	public boolean userExists(String  param) {
		return userRepository.userExists(param);
	}
}
