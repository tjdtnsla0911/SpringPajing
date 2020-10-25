package com.ora.dao;

import com.ora.vo.UserStart;

public interface UserDao {
//회원가입
	public void userStart(UserStart userStart) throws Exception;
	
	public UserStart loginUser(UserStart userStart) throws Exception;
}
