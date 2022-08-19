<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 로그인창 만들기  -->
<div id ="modal">
	<div class ="modal-content">
		<form method="post" action="/login">
			<input type="text"  placeholder="ID" required name="id"><br>  <!--required : 반드시 입력해야됨  -->
			<input type="password" placeholder="PW" required name="pw"><br>
			<input type="submit" value="login">
		</form>
		
		<p>&nbsp;<a href ="memRegForm.do"> [sign up] |</a>  
				 <a href ="#"> [find pw] |</a> 
				 <a href="javascript:clo()" >[Close]</a>		
		</p>	
	</div>
</div>
<!--로그인창 스크립트  // 메뉴창에서 호출--> 
<script>
	function open1(){
		document.getElementById("modal").style.display ="block";  //열기
		}
	function clo(){
		document.getElementById("modal").style.display ="none";  //닫기
		}
</script>