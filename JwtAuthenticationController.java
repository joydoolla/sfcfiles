package com.sfc.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfc.config.JwtTokenUtil;
import com.sfc.dto.APIResponse;
import com.sfc.model.JWTTokenInfo;
import com.sfc.model.Users;
import com.sfc.service.JwtUserDetailsService;
import com.sfc.utility.Constants;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired 
	JWTTokenInfo jwtTokenDtl;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<APIResponse> createAuthenticationToken(@RequestBody Users authenticationRequest) throws Exception {
		APIResponse response = new APIResponse();
		logger.info(" Login Method Start  ");
		//logger.info(" Password Encription "+bcryptEncoder.encode(authenticationRequest.getPassword()));
		try {	
		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		jwtTokenDtl.setJwtToken(token);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setMessage(Constants.SUCCESS);
		response.setData(jwtTokenDtl);
		
	} catch (Exception e) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setMessage(Constants.FAIL);
		response.setData(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	return new ResponseEntity<>(response, HttpStatus.OK);
}


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Users user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}