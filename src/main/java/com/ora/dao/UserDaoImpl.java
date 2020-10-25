package com.ora.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ora.vo.UserStart;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SqlSession sqlSessionl;
	
	//로그인
	@Override
	public UserStart loginUser(UserStart userStart) throws Exception {
		System.out.println("로그인하러옴 받은 userStart = "+userStart);
		return sqlSessionl.selectOne("userMapper.login",userStart);
	}
	
	//회원가입
	@Override
	public void userStart(UserStart userStart) throws Exception {
		System.out.println("daoImpl의 받은 userStart = "+userStart);
		
		sqlSessionl.insert("userMapper.insert",userStart);
	}

}
