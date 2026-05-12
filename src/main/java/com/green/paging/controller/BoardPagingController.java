package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private  MenuMapper         menuMapper;
	
	@Autowired
	private  BoardPagingMapper  boardPagingMapper;
	
	@Autowired
	private  BoardMapper        boardMapper;
	
	
	// /BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public  ModelAndView   list( BoardDto boardDto, int nowpage, 
			String  searchType, String keyword ) {
		
		// 메뉴목록 : menus.jsp 용
		List<MenuDTO>  menuList =  menuMapper.getMenuList();
		
		// 게시물 목록 조회(페이징해서)
		// 해당 메뉴의 자료갯수 : 조회된
		int            totalCount    =  boardPagingMapper.count( boardDto,searchType,keyword );  // menu_id
		System.out.println("totalCount:" + totalCount);
		
		/*
		PagingResponse<BoardDto>  response = null;
		if( totalCount < 1  ) { // 현재 Menu_id 로 조회한 자료가 없다면
			response = new PagingResponse<>(
				Collections.emptyList(), null);
			// Collections.emptyList() : 자료가 없는 빈 리스트를 채운다
		}   
		*/
		
		// 페이징을 위한 초기설정
		SearchDto   searchDto   =  new  SearchDto();
		searchDto.setPageNo( nowpage );  // 현재 페이지 정보
		searchDto.setNumOfRows(10);      // 한페이지에 출력될 자료수
		searchDto.setPageSize(10);       
		  // paging.jsp 에 한줄에 출력될 페이지 번호 수 : 처음 이전 1 2 3 ... 10 다음 마지막

		
		// Pagination 설정
		Pagination   pagination  =  new Pagination(totalCount, searchDto);
		searchDto.setPagination(pagination);
		
		
		int     offset      =  searchDto.getOffset();
		int     numOfRows   =  searchDto.getNumOfRows();
		
		String  menu_id     =  boardDto.getMenu_id();
		String  menu_name   =  menuMapper.getMenuName(menu_id);
		
		// 페이지 조회
		List<BoardDto>  list = boardPagingMapper.getBoardPagingList(
			menu_id, searchType, keyword, offset, numOfRows	); 

		
		ModelAndView   mv       =  new ModelAndView();
		mv.setViewName("boardpaging/list");	
		mv.addObject("menuList",   menuList);
		
		mv.addObject("nowpage",    nowpage);				
		mv.addObject("menu_id",    menu_id);  // 현재 메뉴정보
		mv.addObject("menu_name",  menu_name);
		
		mv.addObject("bList",      list);
		mv.addObject("searchDto",  searchDto);
		
		mv.addObject("searchType",  searchType);
		mv.addObject("keyword",  keyword);
		
		
		return  mv;		
	}
	
	
	// /BoardPaging/View?idx=210&menu_id=MENU01&nowpage=1
	@RequestMapping("/View")
	public  ModelAndView  view( BoardDto boardDto, int nowpage ) {
		
		// 메뉴목록 조회
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		// 조회한 글 조회수 1 증가
		boardMapper.incHit( boardDto );
		
		// idx 로 게시글 한 개 조회
		BoardDto  board      = boardPagingMapper.getBoard( boardDto );
		
		String    menu_id    = boardDto.getMenu_id();
		String    menu_name  = menuMapper.getMenuName(menu_id); 
		
		ModelAndView  mv   = new ModelAndView();
		mv.setViewName("boardpaging/view");
		mv.addObject("menuList", menuList);
		
		mv.addObject("menu_id", menu_id);
		mv.addObject("menu_name", menu_name);
		mv.addObject("nowpage", nowpage);
		
		mv.addObject("board", board);
		return  mv;
	}
	
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1"
	

}
