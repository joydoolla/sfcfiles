package com.sfc.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter @Setter @ToString
public class JWTTokenInfo {
	
	String jwtToken;

}
