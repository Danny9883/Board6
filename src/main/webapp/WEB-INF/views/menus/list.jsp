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

<style>
	table {width: 100%;}
	td {
		padding:5px;
		text-align: center;
	}
	
	tr:first-child {
		background-color: black;
		color: white;
		td {
			border : 1px solid white;
		}
		
  }
	tr:nth-of-type(2)>td {
		text-align: right;
		padding-right: 30px;
  }
  
  main {
  	margin-bottom: 150px;
  }

</style>

</head>
<body>
	<main>
	  <h2>메뉴 목록 ${msg} </h2>
		<table>
			<tr>
				<td>Menu_id</td>
				<td>Menu_name</td>
				<td>Menu_seq</td>
				<c:if test="${ sessionScope.login.userid eq 'ADMIN' }">
				<td>삭제</td>
				<td>수정</td>
				</c:if>
				<c:if test="${ sessionScope.login.userid != 'ADMIN' }">
				<td>이동</td>
				<td>글쓰기</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="5">
					<c:if test="${ sessionScope.login.userid eq 'ADMIN' }">
					[<a href="/Menus/WriteForm">메뉴등록</a>]&nbsp;&nbsp;&nbsp;
					[<a href="/Menus/WriteForm2">메뉴등록2</a>]&nbsp;&nbsp;&nbsp;
					</c:if>
					[<a href="/">Home</a>]
				</td>
			</tr>
			
			<c:forEach var="menu" items="${ menuList }">
			<tr>
				<td>${ menu.menu_id } </td>
				<td>${ menu.menu_name } </td>
				<td>${ menu.menu_seq } </td>
				<c:if test="${ sessionScope.login.userid eq 'ADMIN' }">
				<td> <a href="/Menus/Delete?menu_id=${ menu.menu_id }">삭제</a> </td>
				<td> <a href="/Menus/UpdateForm?menu_id=${ menu.menu_id }">수정</a> </td>
				</c:if>
				<c:if test="${ sessionScope.login.userid != 'ADMIN' }">
				<td> <a href="/Board/List?menu_id=${ menu.menu_id }">이동</a> </td>
				<td> <a href="/Board/WriteForm?menu_id=${ menu.menu_id }">글 쓰기</a> </td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>