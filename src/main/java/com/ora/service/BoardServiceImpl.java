package com.ora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ora.dao.BoardDAO;
import com.ora.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;

	// 게시글작성
	@Override
	public void write(BoardVO boardVo) throws Exception {
		System.out.println("boardServiceImpl의 write에왔습니다");
		System.out.println("boardServiceImpl의 write의 boardvo = " + boardVo);
		dao.write(boardVo);
	}

	// 게시글 리스트
	@Override
	public List<BoardVO> list() throws Exception {
		System.out.println("boardServiceImpl의 list에왔습니다");
		return dao.list();
	}

//조회
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}
	@Override
	public int deleteList(int bno) throws Exception {
		System.out.println("boardSer비스의 델리트에옴 bno="+bno);
		return dao.deleteList(bno);
	}

}
