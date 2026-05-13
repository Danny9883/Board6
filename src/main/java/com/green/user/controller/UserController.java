package com.green.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.user.dto.UserDto;
import com.green.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Users")
public class UserController {

	@Autowired
	private UserMapper userMapper;

    // /Users/WriteForm
    @RequestMapping("/WriteForm")
    public  ModelAndView  writeForm() {
    	
    	ModelAndView  mv  = new ModelAndView();
    	mv.setViewName("users/write");
    	mv.addObject("msg", "욱's");
    	
    	return  mv;
    }
    
    // /Users/Write
    @RequestMapping("/Write")
    public  ModelAndView  write( UserDto userDto ) {
    	System.out.println( "UserConntroller write() ->  " + userDto );
    	
    	// 넘어온 data 로 DB 에 저장
    	userMapper.insertUser( userDto );
    	
    	ModelAndView  mv  = new ModelAndView();
    	mv.setViewName("redirect:/Users/List");
    	return  mv;
    }
    
    

	// /Users/List
	@RequestMapping("/List")
	public  ModelAndView  list() {
		
		// DB 에서 사용자 목록을 조회
		List<UserDto> userList = userMapper.getUserList();
		
		ModelAndView  mv = new ModelAndView();
		mv.setViewName("users/list");
		mv.addObject("userList", userList);
		
		return  mv;
	}
	
	
	
	// /Users/Delete?userid=SKY
	@RequestMapping("/Delete")
	public  ModelAndView  delete( UserDto userDto ) {
		
		// 넘겨받은 자료를 출력
		System.out.println( "UserCont delete() -> " + userDto );
		
		// DB 의 자료를 삭제
		userMapper.deleteUser( userDto );
		
		// 목록으로 이동
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return  mv;
	}
	
	
	// /Users/UpdateForm?userid=SKY
	@RequestMapping("/UpdateForm")
	public  ModelAndView  updateForm( UserDto userDto ) {
		// 넘어온 userDto 정보
		System.out.println("넘어온 정보 -> " + userDto);
		
		// 수정을 위해 DB 에서 조회한 정보
		UserDto  user = userMapper.getUser( userDto ); 
		System.out.println("조회된 정보 -> " + user);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("users/update");
		mv.addObject("user", user);
		
		return  mv;
	}
	
	@RequestMapping("/UpdateForm2")
	public  ModelAndView  updateForm2( UserDto userDto ) {
		// 넘어온 userDto 정보
		System.out.println("넘어온 정보 -> " + userDto);
		
		// 수정을 위해 DB 에서 조회한 정보
		UserDto  user = userMapper.getUser( userDto ); 
		System.out.println("조회된 정보 -> " + user);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("users/update2");
		mv.addObject("user", user);
		
		return  mv;
	}
	
	// /Users/Update
	// userid=SEA&oldpwd=1234&passwd=12345&username=%EB%B0%94%EB%8B%A4&email=sea%40green.com
	// Controller 에서 Map 으로 인자를 받을땐 반드시 @RequestParam 을 사용해야함 
	@RequestMapping("/Update")
	public  ModelAndView  update( @RequestParam Map<String, Object> map) {
		System.out.println("map : " + map);
		// map : {userid=AAA, oldpwd=1234, passwd=1234, username=AAA, email=AAA@green.com}
		userMapper.updateUser2(map);
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		
		return  mv;
	}
	
	@RequestMapping("/Update2")
	public  ModelAndView  update2( @RequestParam Map<String, Object> map) {
		System.out.println("map : " + map);
		// map : {userid=AAA, oldpwd=1234, passwd=1234, username=AAA, email=AAA@green.com}
		userMapper.updateUser2(map);
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/");
		
		return  mv;
	}
	
	/*
	@RequestMapping("/Update")
	public  ModelAndView  update( UserDto userDto, String oldpwd ) {
		
		userMapper.updateUser(userDto, oldpwd);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return  mv;
	}
	*/ 
	
	
	// 아이디 중복 확인 - 결과문자열을 리턴 : 
	// <b class="green">사용가능한 아이디 입니다</b>
	// <b class="red">사용할 수 없는 아이디입니다</b>
	// /Users/IdDupCheck2?userid=sky
	@GetMapping("/IdDupCheck2")
	@ResponseBody     // return 되는 글자는 jsp 가 아니다
	public  UserDto  idDupCheck2( UserDto userDto ) {
		
		UserDto  user    = userMapper.getIdDupCheck(userDto); //  조회한 userid
		if(user == null)
			user = new UserDto();
		return   user;
		
	}
	
	
	// /Users/DupCheckWindow?first=true
	@GetMapping("/DupCheckWindow")
	public  ModelAndView  dupCheckWindow( boolean first, HttpSession session ) {
			
					
		ModelAndView  mv  = new ModelAndView();
		// ?first=true  활용방법
		/*
		if( first )
			mv.addObject("first", first);
		*/
		session.setAttribute("first", "true");
		mv.setViewName("users/idcheck");
		return  mv;
	}
	
	
	// 중복 확인
	// /Users/DupCheck?userid=aaa
	@RequestMapping("/DupCheck")
	public  ModelAndView  dupCheck( UserDto userDto, HttpSession session ) {
		
		session.setAttribute("first", "");
		UserDto       user   = userMapper.getUser( userDto );
		String        msg    = "<b class=\"red\">사용 할 수 없는 아이디 입니다.</b>";
		if( user == null )
			msg = "<b class=\"green\">사용가능한 아이디 입니다.</b>";
		
		ModelAndView  mv    = new ModelAndView();
		mv.setViewName("users/idcheck");
		mv.addObject("msg", msg);
		
		return  mv;
	}
	
	
	//----------------------------------------------------------
	// 로그인 폼  /Users/LoginForm
	@RequestMapping("/LoginForm")
	public  String  loginForm() {
		return  "users/login";
	}
	
	// 로그인    /Users/LoginForm , userid, passwd
	@RequestMapping("/Login")
	public  String  login( UserDto userDto, 
			HttpServletRequest request, Model model ) {
		System.out.println(userDto);
		
		UserDto      user     = userMapper.getLogin(userDto);

		HttpSession  session  =  request.getSession();
		session.setAttribute("login", user);
		
		String  loc  = "";
		if(session.getAttribute("loc") == null)
			loc = "redirect:/";
		else
			loc = "redirect:" + session.getAttribute("loc").toString();

		return  loc;
	}
	
	// 로그아웃  /Users/Logout
	@RequestMapping("/Logout")
	public  String  logout( HttpServletRequest request ) {
		HttpSession  session  = request.getSession();
		session.invalidate();
		return  "redirect:/";
	}
	
	
	

	
}
