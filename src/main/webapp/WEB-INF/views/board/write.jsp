<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib  prefix="c" uri="jakarta.tags.core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet" />

<style>

	table {	width: 100%; }
	
	#table1 { margin-bottom: 150px; }
	
	
	td {
		padding: 5px 10px;
		text-align: center;
		&:nth-of-type(1) {
			background-color: black;
			color: white;
			border: 1px solid white;
		}
	}
	tr:last-child >td{
		background-color: white;
		border: 1px solid black;
	}
		
	input[type="text"], input[type="number"], input[type="password"] {
		width: 100%;
	}
	
	input[type="button"], input[type="submit"] {
		width: 100px;
	}
	
	input[name="userid"] {
		width: 60%;
	}
	
	textarea {
		width: 100%;
		height: 300px; 		
	}
	
	.menu td> .${ menu_id } {background-color: #04AA6D;}
	
	.menu tr:first-of-type {
	background-color: black;
	color: white;
	td {
		border : 1px solid white;
		text-align: center;
	}
	

</style>
</head>
<body>
	<main>
		<%@include file="/WEB-INF/include/menus.jsp" %>
		
		<h2 class="h2">${ menu_name } 새 글 쓰기</h2>
		<form action="/Board/Write" method="post">
		<input type="hidden" name="menu_id" value="${menu_id}" />
		<input type="hidden"  name="writer" value="${ sessionScope.login.userid }" />
			<table id="table1">
				<tr>
					<td><span class="red">*</span>제목</td>
					<td>
						<input type="text"   name="title" required="required" />
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="추가" />
						<input type="button" value="목록" 
						 onclick="location.href='/Board/List?menu_id=${menu_id}'"/>
					</td>
				</tr>
			</table>		
		</form>
	
	</main>
	
	<script>
	
	</script>
</body>
</html>