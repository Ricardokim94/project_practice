<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common.jsp" %>
	<link rel ="stylesheet" href="/board/board.css">
	<title>회원가입</title>
	
	<script src="https://kit.fontawesome.com/236f0b5985.js" crossorigin="anonymous"></script>
</head>
	
	<%@ include file="/header.jsp" %>
	
	<%@ include file="../menu.jsp" %>
	<script src="/js/formCheck.js"></script>

<div class="row">
  <div class="leftcolumn">
    <div class="card">
      <h2 style=>회원가입</h2>
      <hr>
	  <form name="memRegForm" method="post" action="memberReg.do" onsubmit="return formCheck()">
		  	<div class ="who">
		  		<input type="text" placeholder=" ID" required name="id" onchange="idCheck()">
				<input type="hidden" id="isIdCheck">
				<p id="idCheckMsg" style = "color : red "></p>
								
				
				<input type="password" placeholder="PW"  name="pw" required ><br>
				<input type="text" placeholder=" Name" name="name">
				<span id ="msg" style= "background : pink"></span>
			</div>	
				<hr>
			
				<div class ="gender">
				<h4>성별</h4>
				<input type="radio" name="gender" value="F"><b>여자</b> <!--동그란버튼  -->
				<input type="radio" name="gender" value="M"><b>남자 </b>
				</div>
				<hr>
				
				<div class ="hobby">
				<fieldset style="width:50%;">
				<legend><ins><strong>취미</strong></ins></legend>
				<input type="checkbox" name="hobby" value="fitness"> 운동<!--여러개 선택-->
				<input type="checkbox" name="hobby" value="music"> 음악
				<input type="checkbox" name="hobby" value="art"> 미술
				</fieldset>
			</div>	
				<hr>
			
				<div class="em">
				<input type ="text" placeholder="e-mail" name="eid">@
				<input type ="text" placeholder="domain" name="domain">	
		  		<select name="selDomain" onchange ="inputDomain()">
		  			<option value="">직접입력</option>
		  			<option value="naver.com">naver.com</option>
			  		<option value="daum.net">daum.net</option>
			  		<option value="gmail.com">gmail.com</option>
			  	</select>
			  	</div><hr><br>
		  	
		  	<textarea rows="5" cols="50" placeholder ="자기소개(최대500자)" name="intro"></textarea>
		  	
		  	<input type="submit" value="회원등록">
		  	<input type="reset" value="취소">
	  	
	  </form>
    </div>
    
   </div>
  <div class="rightcolumn">
    <div class="card">
      <h2>Save the endangered otter</h2>
      <!-- <div class="fakeimg" style="height:200px;">Image</div> -->
      <img src ="/img/기도.jpg">
    </div>
    <div class="card">
      <h3>Popular Post</h3>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
    </div>
    <div class="card">
      <h3>Follow Me</h3>
      <p>Some text..</p>
    </div>
  </div>
</div>

<%@ include file="/footer.jsp" %>
<%@ include file="/member/login_modal.jsp" %>


</body>
</html>
