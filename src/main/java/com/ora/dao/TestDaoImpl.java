package com.ora.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	// mapper파일의 namespace
	private static String namespace = "com.ora.mapper.TestMapper";
	
	
	@Override
	public List<HashMap> list() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".testSelect");
	}

}
