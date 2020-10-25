package com.ora.service;

import java.util.List;

import com.ora.vo.BoardVO;

public interface BoardService {
	public void write(BoardVO boardVo) throws Exception;
	List<BoardVO> list() throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public int deleteList(int bno) throws Exception;

}
