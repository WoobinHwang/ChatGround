package com.cround.cgpt;

import lombok.Getter;

@Getter
public enum UserRole {

	ADMIN("ADMIN")
	, USER("USER");
	
	UserRole(String authority) {
		// TODO Auto-generated constructor stub
		this.authority = authority;
	}
	
	private String authority;
}
