package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;
import com.green.paging.dto.SearchDto;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDto boardDto, String searchType, String keyword);

	List<BoardDto> getBoardPagingList(String menu_id, String title, String writer, String content, int offset,
			int numOfRows);

	List<BoardDto> getBoardPagingList(String menu_id, String searchType, String keyword, int offset, int numOfRows);

	BoardDto getBoard(BoardDto boardDto);

	void insertBoard(BoardDto boardDto);

	void incHit(BoardDto boardDto);

	void deleteBoard(BoardDto boardDto);

	void updateBoard(BoardDto boardDto);


}
