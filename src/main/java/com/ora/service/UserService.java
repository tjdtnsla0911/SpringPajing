package com.ora.service;

import com.ora.vo.UserStart;

public interface UserService {
	//회원가입
	public void join(UserStart userStart) throws Exception; 
	
	//로그인
	public UserStart login(UserStart userStart) throws Exception;

}
