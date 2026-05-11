package com.green.paging.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.PagingResponse;
import com.green.paging.dto.SearchDto;
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
		
		PagingResponse<BoardDto> response = null;
		if( totalCount < 1 ) {  // 현재 Menu_id 로 조회한 자료가 없다면
			response = new PagingResponse<>(
					Collections.emptyList(), null);
			// Collections.emptyList() : 자료가 없는 빈 리스트를 채운다
		}
		
		// 페이징을 위한 초기 설정
		SearchDto  searchDto  = new SearchDto();
		searchDto.setPageNo(nowpage);  //  현재 페이지 정보
		searchDto.setNumOfRows(10);    //  한 페이지에 출력될 자료수
		searchDto.setPageSize(10);     //  paging.jsp 한줄에 출력될 페이지 수   
		
		// Pagination 설정
		Pagination  pagination  = new Pagination(totalCount, searchDto);
		searchDto.setPagination(pagination);
		
		// 검색조건 추가
		// 추가된 검색조건
		String  title     = boardDto.getTitle();
		String  writer    = boardDto.getWriter();
		String  content   = boardDto.getContent();
		
		int     offset    = searchDto.getOffset();
		int     numOfRows = searchDto.getNumOfRows();
		
		String  menu_id   = boardDto.getMenu_id();
		
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
				menu_id, title, writer, content, offset, numOfRows );
		response  = new PagingResponse<>(list, pagination);
		
		System.out.println(response);
		
		String  menu_name = menuMapper.getMenuName(menu_id); 
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);
		
		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id); // 현재 메뉴 정보
		mv.addObject("menu_name", menu_name);
		
		mv.addObject("bList", list);
		mv.addObject("searchDto", searchDto);
		
		return  mv;
	}
	
	
	
	
	
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1"
	

}
