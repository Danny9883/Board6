package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.BoardApplication;

@Controller
public class HomeController {

    private final BoardApplication boardApplication;

    HomeController(BoardApplication boardApplication) {
        this.boardApplication = boardApplication;
    }
	
	// http://localhost:8080
	@RequestMapping("/")
	public  String  home( ) {
		// System.out.println("Borad 4 첫페이지 실행");
		return  "home";   // jsp 파일을 찾는다
		                  // /WEB-INF/views/home.jsp
	}
	
	// http://localhost:8080/test
	@RequestMapping("/test")
	@ResponseBody              // 서버가 data(html) 을 내려보낸다
	public  String  test() {
		return  "<h2>Test 입니다</h2><br>"
				+ "<a href=\"/\">돌아가기</a>";
	}
	
	
}






