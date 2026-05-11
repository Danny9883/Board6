<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib  prefix="c" uri="jakarta.tags.core" %>      

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon" />
<link href="/css/common.css" rel="stylesheet" />

</head>
<body>
  <main>
    <h2>Home</h2>
    <c:if test="${ sessionScope.login.userid eq 'ADMIN' }">
    <a href="/test">Test</a>
    <div><a href="/Menus/WriteForm">새 메뉴추가</a></div>
    <div><a href="/Menus/WriteForm2">새 메뉴추가2</a></div>
    </c:if>
    <div><a href="/Menus/List">메뉴 목록</a></div>
		<div>&nbsp;</div>    
		
		<c:if test="${ sessionScope.login.userid != 'ADMIN' and sessionScope.login.userid != null}">
		<div><a href="/Users/UpdateForm2?userid=${ sessionScope.login.userid }">개인정보 수정</a></div>
		</c:if>
		<c:if test="${ sessionScope.login.userid eq 'ADMIN' }">
    <div><a href="/Users/List">사용자 목록</a></div>  
    <div><a href="/Users/WriteForm">사용자 추가</a></div>  
		<div>&nbsp;</div>
		    
    <div><a href="/Users/IdDupCheck2?userid=aaa"  target="_blank">아이디 중복 테스트</a></div>
		</c:if>
		<div>&nbsp;</div>
		
    <div><a href="/Board/List?menu_id=MENU01">게시글 목록</a></div>  
    <div><a href="/Board/WriteForm?menu_id=MENU01">게시글 추가</a></div>  
		<div>&nbsp;</div>
		
		<c:if test="${ sessionScope.login.userid != null }">
		<hr>
		<div><br>
			${ sessionScope.login.username } 님 환영합니다.<br>
			보유 포인트 : [ ${ sessionScope.login.upoint } ] <br><br>
			당신의 가입일은 ${ sessionScope.login.regdate } 입니다.<br>
		</div><br>
		</c:if>
		<hr>
		
		<div>&nbsp;</div>
		<c:if test="${ sessionScope.login.userid != null }">
		<a href="/Users/Logout">로그아웃</a><br>
		</c:if>
		<c:if test="${ sessionScope.login.userid eq null }">
		<a href="/Users/LoginForm">로그인</a>
		</c:if>
      
  </main>
  
</body>
</html>