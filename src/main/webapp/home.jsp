<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common.jsp" %>
	<title>수달이홈페이지</title>
	
	<link rel ="stylesheet" href="/board/board.css">
	<script src="/js/formCheck.js"></script>
	<script src="https://kit.fontawesome.com/236f0b5985.js" crossorigin="anonymous"></script>
	<script>
		function init(){
			var stat = document.getElementsByName("stat"); //stat를 실행시킬 함수를 찾아서 stat의 변수에 저장!
			if(stat[0].value == 1){
				/* alert(stat[0].value); */
				document.getElementById("modal").style.display ="block";	
			}
		}
	</script>
</head>


<body onload="init()">
home.jsp 입니다
</body>
</html>