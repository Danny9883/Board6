<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon" />
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
	

</style>
</head>
<body>
	<main>
		<h2>사용자 수정</h2>
		<form action="/Users/Update2" method="post">
			<table>
				<tr>
					<td><span class="red">*</span> 사용자 아이디</td>
					<td><input type="text"  name="userid"  value="${user.userid}" readonly/></td>
				</tr>				
				<tr>
					<td><span class="red">*</span>이전 비밀번호 확인</td>
					<td><input type="password"  name="oldpwd" id="passwdold" /></td>
				</tr>
				<tr>
					<td><span class="red">*</span>새 비밀번호</td>
					<td><input type="password"  name="passwd"  id="passwd" /></td>
				</tr>
				<tr>
					<td><span class="red">*</span>새 비밀번호 확인</td>
					<td><input type="password"  id="passwd2" /></td>
				</tr>
				<tr>
					<td><span class="red">*</span> 이름</td>
					<td><input type="text"  name="username" value="${user.username}" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text"  name="email" value="${user.email}" /></td>
				</tr>
				<tr>
					<td>포인트</td>
					<td>${ user.upoint } </td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>${ user.regdate } </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" />
						<input type="button" value="이전" 
						 onclick="location.href='/'"/><br>
						 <p style="color: black;">정보는 재 로그인 후 반영됩니다.</p>
					</td>
				</tr>

			</table>		
		</form>
	
	</main>
	
	<!-- JavaScript 코딩 : client validation -->
	<script>
		const  formEl       = document.querySelector('form');
		const  useridEl     = document.querySelector('[name="userid"]');
		const  passwdoldEl  = document.querySelector('#passwdold');
		const  passwdEl     = document.querySelector('#passwd');
		const  passwd2El    = document.querySelector('#passwd2');
		const  usernameEl   = document.querySelector('[name="username"]');
		
		formEl.addEventListener('submit', function( e ) {
			
			//  아이디값 체크
			if ( useridEl.value.trim() == '' ) {
				alert('아이디를 입력하세요.');
				useridEl.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			// 이전비밀번호 입력체크
			if ( passwdoldEl.value.trim() == '' ) {
				alert('이전 비밀번호를 입력하세요.');
				passwdoldEl.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			
			// 이전비밀번호 == 조회된 비밀번호 user.passwd 체크 
			if ( passwdoldEl.value.trim() != '${user.passwd}' ) {
				alert('이전 비밀번호가 일치하지 않습니다.');
				passwdoldEl.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			//  비밀번호값 체크
			if ( passwdEl.value.trim() == '' ) {
				alert('암호를 입력하세요.');
				passwdEl.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			//  비밀번호확인 체크
			if ( passwd2El.value.trim() == '' ) {
				alert('비밀번호 확인을 입력하세요.');
				passwd2El.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			//  비밀번호 == 비밀번호확인
			if ( passwdEl.value != passwd2El.value ) {
				alert('비밀번호가 일치하지 않습니다.');
				passwd2El.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
			//  이름값 체크
			if ( usernameEl.value.trim() == '' ) {
				alert('이름을 입력하세요.');
				usernameEl.focus();				
				e.preventDefault();   // 이벤트 취소
				e.stopPropagation();  // 이벤트 버블링 방지
				return;
			}
			
		});
	
	
	</script>
	
</body>
</html>