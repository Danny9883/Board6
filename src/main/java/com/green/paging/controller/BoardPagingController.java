package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private  MenuMapper         menuMapper;
	
	@Autowired
	private  BoardPagingMapper  boardPagingMapper;
	
	
	// /BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public  ModelAndView  list( BoardDto boardDto, int nowpage ) {

		// 메뉴 목록 : menus.jsp 용
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		
		// 게시물 목록 조회(페이징 해서)
		// 해당 메뉴의 자료 갯수 :  
		int     totalCount     = boardPagingMapper.count(boardDto);   // menu_id 
		System.out.println("totalCount : " + totalCount);
		
		String  menu_id   = boardDto.getMenu_id();
		String  menu_name = menuMapper.getMenuName(menu_id); 
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		
		mv.addObject("menu_id", menu_id); // 현재 메뉴 정보
		mv.addObject("menu_name", menu_name);
		return  mv;
	}
	
	
	
	
	
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1"
	

}
