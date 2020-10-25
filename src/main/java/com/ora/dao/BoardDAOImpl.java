package com.ora.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ora.vo.BoardVO;
@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	//게시글작성
	@Override
	public void write(BoardVO boardVo) throws Exception {
		System.out.println("boardDAO의 write에 왔습니다");
		System.out.println("boardDAO의 write의 boaVo = "+boardVo);
		sqlSession.insert("boardMapper.insert",boardVo);
		
	}
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("list에 왔습니다.");
		return sqlSession.selectList("boardMapper.list");
	}
@Override
public BoardVO read(int bno) throws Exception {
	// TODO Auto-generated method stub
	return sqlSession.selectOne("boardMapper.read",bno);
}

@Override
public int deleteList(int bno) throws Exception {
	System.out.println("deleteList에옴 여긴 Dao 그리고 bno = "+bno);
	
	return sqlSession.delete("boardMapper.deleteList",bno);
	
}
}
