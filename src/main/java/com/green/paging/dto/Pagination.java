package com.green.paging.dto;

import lombok.Getter;
import lombok.ToString;

// OFFSET     30       ROWS FETCH  NEXT     10      ROWS ONLY;
//        limitStart                     numOfRows

// paging.jsp : 페이지 번호를 출력할 파일
//       한줄에 10개의 페이지번호를 출력 : SearchDto pageSize
//     startPage        nowpage(pageNo)         endPage
//         1        2  3  4  [5]  6  ...  9       10      >  >> 
// <<  <  11       12 13 14  15  16  ... 19       20      >  >> 
// <<  <  21       22 23 24  25  26  
//                           totalPageCount : 전체페이지 수


@Getter
@ToString
public class Pagination {
	private   int      totalCount;      // 해당 메뉴의 조회 된 자료수
	private   int      totalPageCount;  // 전체 페이지 수 : totalCount / numOfRows
	
	private   int      startPage;
	private   int      endPage;
	
	private   int      limitStart;
	
	private   boolean  existPrevPage;
	private   boolean  existNextPage;
	
	// 생성자
	public  Pagination( int totalCount, SearchDto searchDto ) {
		if( totalCount > 0 ) {
			this.totalCount = totalCount;
			calculation( searchDto );
		}
		
	}

	private void calculation(SearchDto srchDto) {
		
		// 전체 페이지 수 계산
		int  numOfRows      = srchDto.getNumOfRows();
		this.totalPageCount = (int)Math.ceil((double)this.totalCount / (double)numOfRows);
		
		// 현재 페이지 : pageNo <- nowpage
		int  pageNo         = srchDto.getPageNo();
		if( pageNo > this.totalPageCount ) {
			pageNo  = this.totalPageCount;
			srchDto.setPageNo( pageNo );
		}
		
		// 페이지 번호의 시작 계산
		int  pageSize  = srchDto.getPageSize();  // 한줄에 출력할 페이지 번호 수 
		startPage      = ((pageNo - 1) / pageSize) * pageSize + 1; 
		endPage        = startPage + pageSize - 1 ;
		
		// limitStart : 데이터베이스에서 가져올 시작 위치
		limitStart     = srchDto.getOffset();
		// limitStart     = (pageNo - 1) * numOfRows; 
		
		// 이전 페이지로 이동 버튼 필요
		existPrevPage  = startPage > 1;
		
		// 다음 페이지로 이동 버튼 필요
		existNextPage  = endPage < totalPageCount;
		
	}
	
}
