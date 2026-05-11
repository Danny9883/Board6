package com.green.paging.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDto boardDto);

}
