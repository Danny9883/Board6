package com.green.paging.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingResponse<T> {
	// 페이징 된 실제 자료의 모음 : numOfRows개 -> 10개 
	private  List<T>     list        = new ArrayList<>();
	
	// 페이지와 관련된 변수들의 모임
	private  Pagination  pagination;

	public PagingResponse(List<T> list, Pagination pagination) {
		this.list = list;
		this.pagination = pagination;
	}
	
	
}
