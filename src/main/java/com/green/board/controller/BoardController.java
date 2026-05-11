package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	@Autowired
	private  BoardMapper  boardMapper;
	
	// /Board/List?menu_id=MENU01
	@RequestMapping("/List")
	public  ModelAndView  list( MenuDTO menuDto ) {
		
		// 메뉴 전체 목록 조회 - menus.jsp
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		log.info("menuList : " + menuList);
		
		// 게시물 목록 조회 - list.jsp
		List<BoardDto> boardList  = boardMapper.getBoardList(menuDto);
		log.info("boardList: " + boardList);
		
		// 넘어온 menu_id
		String   menu_id   = menuDto.getMenu_id();
		
		MenuDTO  menu      = menuMapper.getMenu(menuDto);

		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("menuList", menuList);
		mv.addObject("bList", boardList);
		mv.addObject("menu_id", menu_id );
		mv.addObject("menu", menu );
		
		return  mv;
	}
	
	// /Board/View?idx=1&menu_id=MENU01
	@RequestMapping("/View")
	public  ModelAndView  view( BoardDto boardDto ) {
		
		// 메뉴 목록 조회 
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		
		// idx 글의 조회수를 1 증가
		boardMapper.incHit( boardDto );
		
		String   menu_id   = boardDto.getMenu_id();
		String   menu_name = menuMapper.getMenuName(menu_id);
		
		
		// idx 로 조회한 게시글
		BoardDto  board   = boardMapper.getBoard( boardDto );
		// System.out.println("board : " + board);
		// BoardDto [idx=1, menu_id=MENU01, title=JAVA Hello, content=자바 게시판에 오신것을 환영합니다, writer=java, regdate=2026-05-04 15:16:47, hit=0]
		
		// content 안에 있는 엔터 \n 를 <br> 로 변경 -> content
		if (board.getContent() != null)
			board.setContent( board.getContent().replace("\n", "<br>") );
		
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		
		return  mv;
	}
	
	
	// /Board/WriteForm
	@RequestMapping("/WriteForm")
	public  ModelAndView  writeForm( BoardDto boardDto ) {
		// 메뉴 목록 조회 
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		
		
		String   menu_id   = boardDto.getMenu_id();
		String   menu_name = menuMapper.getMenuName(menu_id);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id );
		mv.addObject("menu_name", menu_name );
		return  mv;
	}
	
	// /Board/Write?menu_id=MENU01&title=a&content=a&writer=a
	@RequestMapping("/Write")
	public  ModelAndView  write( BoardDto boardDto ) {
		// System.out.println("/Write ? : " + boardDto);
		// /Write ? : BoardDto(idx=0, menu_id=MENU01, title=1, content=2, writer=3, regdate=null, hit=0)
		
		// db 저장
		boardMapper.insertBoard( boardDto );
		
		String menu_id = boardDto.getMenu_id();
		
		// 페이지 이동
		ModelAndView  mv  = new ModelAndView(); 
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id );
		return  mv;
	}	
	
	// 게시물 삭제
	// /Board/Delete?idx=1&menu_id=MENU01
	@RequestMapping("/Delete")
	public  ModelAndView  delete( BoardDto boardDto ) {
		
		// 게시글 삭제 (idx 에 해당하는)
		boardMapper.deleteBoard( boardDto );
		
		String menu_id = boardDto.getMenu_id();
		
		// menu_id 해당 목록으로 돌아간다
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id );
		return  mv;
	}
	
	
	// /Board/UpdateForm?idx=${board.idx}&menu_id=${board.menu_id}
	@RequestMapping("/UpdateForm")
	public  ModelAndView  updateForm( BoardDto boardDto ) {
		
		// 메뉴 목록 조회 
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		
		BoardDto  board   = boardMapper.getBoard( boardDto );
		
		String   menu_id   = boardDto.getMenu_id();
		String   menu_name = menuMapper.getMenuName(menu_id);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/update");
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		return  mv;
	}
	
	// /Board/Update
	@RequestMapping("/Update")
	public  ModelAndView  update( BoardDto boardDto ) {
		
		// 게시글 수정
		boardMapper.updateBoard( boardDto );
		
		// 수정후 목록으로 이동
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/View?idx=" + boardDto.getIdx()+"&menu_id="+boardDto.getMenu_id() );
		return  mv;
	}
	
	

	
	
	
}
