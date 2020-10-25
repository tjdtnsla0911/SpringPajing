package com.ora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ora.dao.UserDao;
import com.ora.vo.UserStart;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Override
	public void join(UserStart userStart) throws Exception {
		System.out.println("join에 왔습니다.");
		dao.userStart(userStart);
	}

	// 로그인
	@Override
	public UserStart login(UserStart userStart) throws Exception {
		// TODO Auto-generated method stub
		return dao.loginUser(userStart);
	}

}
