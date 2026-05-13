<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib  prefix="c" uri="jakarta.tags.core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 수정</title>
<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet" />

<style>
	
	table { width: 100%; }
	
	td {
		padding: 5px 10px;
		text-align: center;
		&:nth-of-type(1) {
			background-color: black;
			color: white;
			border: 1px solid white;
		}
	}
	
	
	#table1 {
		margin-bottom: 150px;
		td {
			&:nth-of-type(1) {
				width: 150px;
				background: black;
				color: white;
				border-left: 1px solid black;
			}
			&:nth-of-type(2) {
				width: 150px;
				background: white;
				color: black;
			}
			&:nth-of-type(3) {
				width: 150px;
				background: black;
				color: white;
				border-bottom: 1px solid white;
			}
			&:nth-of-type(4) {
				width: 150px;
				background: white;
				color: black;
			}
		}
	}
	

  #table1	tr:last-child >td {
		background: white;
		border: 1px solid black;
		a, input[type="submit"] { margin: 0px 15px; }
	}
	
	#table1 tr:first-of-type td {
		border-top: 1px solid black;
	}
	
  #table1	tr:nth-of-type(2) td:nth-of-type(3) {
  	border-bottom: 1px solid black;
  }
  
  #table1	tr:nth-of-type(3) td:nth-of-type(2) {
  	text-align: left;
  }
  
  #table1	tr:nth-of-type(4){
  	height: 300px;
  	td {border-bottom: 1px solid black;}
  }
  
 	input[name="title"] {
		width: 100%;
	}
	
	textarea {
		width: 100%;
		height: 300px; 		
	}
	
	input[name="title"], 
	textarea {
		padding: 5px;
	}

	
	
	.menu tr:first-of-type {
	background-color: black;
	color: white;
	td {
		border : 1px solid white;
		text-align: center;
	}
	

	

</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</head>
<body>
	<main>
	<%@include file="/WEB-INF/include/menuspaging.jsp" %>
		<h2 class="h2"><b id="mname"></b> 게시글 수정</h2>
		<form action="/BoardPaging/Update" method="post">
			<input type="hidden" name="idx" value="${ board.idx }"  />
			<input type="hidden" name="menu_id" value="${ board.menu_id }"  />
			<input type="hidden" name="nowpage" value="${ nowpage }"  />
			<table id="table1">
				<tr>
					<td>글 번호</td>
					<td>${ board.idx }</td>
					<td>조회수</td>
					<td>${ board.hit }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${ board.writer }</td>
					<td>작성일</td>
					<td>${ board.regdate }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" value="${ board.title }" name="title" required /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3" ><textarea name="content" >${ board.content }</textarea></td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="submit" value="수정" class="btn btn-warning" />
						<a href="/BoardPaging/View?idx=${board.idx}&menu_id=${board.menu_id}&nowpage=${nowpage}" class="btn btn-info">이전</a>
						<a href="/BoardPaging/List?menu_id=${board.menu_id}&nowpage=${nowpage}" class="btn btn-info">글 목록</a>
						<a href="/" class="btn btn-success">Home</a>
					</td>
				</tr>
			</table>		
		</form>
	
	</main>
	
	
	
	
	<script>
		const  mnameEl    = document.querySelector('#mname');
		let    menunameEl = document.querySelector('.menu .active');
		mnameEl.innerHTML = menunameEl.innerHTML;
	
		// javascript : client validation
	</script>

</body>
</html>