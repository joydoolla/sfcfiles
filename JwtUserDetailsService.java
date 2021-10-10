package com.sfc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sfc.dao.UserDao;
import com.sfc.model.Users;
import com.sfc.utility.Constants;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 Users user = userDao.findByUserName(Constants.Active, userName, userName);
			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + userName);
			}
			return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

	public Users save(Users user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setStatus(Constants.InActive);
		return userDao.saveUser(user);
	}

}