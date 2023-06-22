package com.cround.cgpt.service;

import com.cround.cgpt.entity.UserList;

public interface UserService {

	/**  */
	public UserList signup(String username, String password
			, String nickname, String email);
	
	/** 
	 * column: username, nickname, email <br>
	 * 해당 column에서 data로 가입된 회원이 있는지 확인하여
	 * 있으면 true, 없으면 false를 반환 <br> 
	 * made by 황우빈
	 *  */
	public boolean duplicateCheck(String column, String data);
	
}
