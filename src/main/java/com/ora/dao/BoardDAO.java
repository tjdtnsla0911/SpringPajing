package com.ora.dao;

import java.util.List;

import com.ora.vo.BoardVO;

public interface BoardDAO {
	//게시글 작성
	public void write(BoardVO boaVo) throws Exception;
	
	//게시글 목록조회
	List<BoardVO> list() throws Exception;
	
	//게시글 조회
	public BoardVO read(int bno) throws Exception;
	
	//삭제
	public int deleteList(int bno) throws Exception;

}
