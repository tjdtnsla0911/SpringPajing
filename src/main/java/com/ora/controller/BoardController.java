package com.ora.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ora.service.BoardService;
import com.ora.vo.BoardVO;
//@RequestMapping("/board")
import com.ora.vo.Pajing;

@Controller
public class BoardController {

	@Inject
	BoardService service;

	@GetMapping("/board/writeView")
	// @RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public String writeView() throws Exception {
		System.out.println("컨트롤러의 writeView에왔습니다");
		return "/board/writeView";
	}

	// 게시판 글 작성
	@PostMapping("/board/write")
	// @RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception {
		System.out.println("겟타이틀 = " + boardVO.getTitle());
		System.out.println("받은 boardVo는 = ? " + boardVO);

		System.out.println("/board/write에왔습니다.");
		service.write(boardVO);

		return "redirect:/";
	}

	///////////////////////////////////////
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Model model2) throws Exception {
		
		Pajing pajing = new Pajing();
		System.out.println("list에옴");
		List<BoardVO> list = service.list();
		System.out.println("list의 총 사이즈 = " + list.size());
		double real = (double) list.size() / 3;
		System.out.println("list총사이즈 나누기3 = " + list.size() / 3);
		int realPajing = (int) Math.ceil(real);
		System.out.println("리얼페이징 = " + realPajing);
		pajing.setStartPage(1);
		pajing.setAllpage(realPajing);// 페이징 나눈거
		System.out.println("뱉어낸 페이징 = " + pajing);
		model2.addAttribute("pajing", pajing);// 가져갈페이징
		model.addAttribute("list", list); // 가져갈값
		return "board/list";

	}
	/////////////////////////

	@GetMapping("/board/readView")
	public String readview(BoardVO boardVo, Model model) throws Exception {
		System.out.println("/board/readView에옴");
		BoardVO board = service.read(boardVo.getBno());
		System.out.println("받은 board = " + board);
		model.addAttribute("read", board);
		return "board/readView";

	}

	@DeleteMapping("/board/delete/{bno}")
	public @ResponseBody List<BoardVO> ddd(@PathVariable int bno) throws Exception {
		System.out.println("delete먜핑에옴");
		System.out.println("받은 bno = " + bno);
		service.deleteList(bno);// 여기서지우고
		List<BoardVO> list = service.list();
		System.out.println("무사히끝난후 list = " + list);
		return list;
	}
	@GetMapping("/board/pajing/{i}")
	public @ResponseBody List<BoardVO> ccc(@PathVariable int i) throws Exception {
		List<BoardVO> pajing3List = service.list();
		List<BoardVO> pajing3List2 = new ArrayList<BoardVO>(); //정한 개수만 받아서 가져갈놈
		System.out.println("받은 i = " + i);
		int page = 0;
		if (i != 1) { // 첫페이지 클릭을 생각해서 i가 1보다 클때만 작용하도록함
			page = page + i * 3; // 3개씩 출력할려고! 좀특이하게 할꺼임
			System.out.println("출력한 page = " + page);
			int page1 = page - 3;
			int page2 = page - 2;
			int page3 = page - 1;
			System.out.println("가져온 pajing3List =" + pajing3List);
			System.out.println("개수 = " + pajing3List.size());
			for (int s = 0; s < pajing3List.size(); s++) {
				System.out.println("page -4 = " + page);
				System.out.println("여기서 page1 = " + page1);
				System.out.println("s =" + s);
				if (s == page1 || s == page2 || s == page3) {
					System.out.println("맞다에들어옴 s = " + s);
					pajing3List2.add(pajing3List.get(s));

				} else {
					System.out.println("숙청당하는 s =" + s);
					System.out.println("else에왔습니다");
				}
			}
			System.out.println("페이징리스트 사이즈 - " + pajing3List2.size());
			return pajing3List2;
		}else {
			System.out.println("else에왔습");
			System.out.println("pajing3List.size() = "+pajing3List.size());
			if(pajing3List.size()<3) {
				for(int ss =0;ss<pajing3List.size();ss++) {
					pajing3List2.add(pajing3List.get(ss));
				}
				System.out.println("pajing3List2.size() if = "+pajing3List2.size());

				return pajing3List2;
				
			}else {
				for(int ss =0;ss<3;ss++) {
					
					pajing3List2.add(pajing3List.get(ss));
			}
				System.out.println("pajing3List2.size() else = "+pajing3List2.size());
		
			
	
			return pajing3List2;
		}
			
			
		}
	}
	@GetMapping("/board/serch/{title}")
	public @ResponseBody List<BoardVO> serchList(String title) throws Exception{
		System.out.println("받은 title =" +title);
		List<BoardVO> serchlist  = service.list();
		return serchlist;
	}
	
	@GetMapping("/board/pajingRendering")
	public @ResponseBody int ren() throws Exception {
	
		System.out.println("새로랜더링하러옴");
		List<BoardVO> pajing3List = service.list();
		System.out.println("페이징 사이즈 = "+pajing3List.size());
		double ddd = (double)pajing3List.size()/3;
		System.out.println("ddd = "+ddd);
		int realPajing = (int) Math.ceil(ddd);
		System.out.println("리얼 페이징 = "+realPajing);
		return realPajing;
	}


}
