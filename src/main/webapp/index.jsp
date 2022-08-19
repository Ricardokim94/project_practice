<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="common.LoginImpl" %>

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
			var msg = document.getElementsByName("msg");
			//alert(msg[0].value);
			var alert_msg;
			var modal_pop;
			if(msg[0].value == "loginOk") {
				alert_msg = "로그인이 되었습니다"; 
				modal_pop = false; // 모달팝업을 안하겠다.
			}
			if(msg[0].value == "loginFail") {
				alert_msg = "로그인정보가 없습니다";
				modal_pop = true;
			}
			if(msg[0].value == "memberOk") {
				alert_msg = "회원등록이 되었습니다.";
				modal_pop = true;
			}	 	 
			
			if(msg[0].value != "null") alert(alert_msg);
			if(modal_pop) document.getElementById("modal").style.display ="block";	
		}
		
	</script>
</head>


<body onload="init()"> 
<input type ="hidden" name="msg" value="<%=request.getAttribute("msg") %>">

<%@ include file ="header.jsp" %>
<%@ include file= "menu.jsp" %>

<c:set value="${loginUser }" var = "loginuser" />
JSP코드인 현재접속 : <%= LoginImpl.total_user %>
<p> 현재 접속자수 : ${loginuser.getTotal_user() } 명 </p>

<div class="row">
  <div class="leftcolumn">
    <div class="card">
      <i class="fa-solid fa-hashtag"></i><h2>otter's world</h2>
            
      <h5>수달</h5>
      <div class="fakeimg" style="height:200px;">Image</div>
      <p>Some text..</p>
      <p>한국에는 13종의 수달이 살고있습니다.</p>
    </div>
    
    <div class="card">
      <i class="fa-solid fa-hashtag"></i><h2>otter's pictures</h2>
      <h5>2022.06.02</h5>
      <div class="fakeimg" style="height:200px;">Image</div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
  <div class="rightcolumn">
    <div class="card">
      <h2>Save the endangered otter</h2>
      <!-- <div class="fakeimg" style="height:200px;">Image</div> -->
      <img src ="img/기도.jpg">
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

<%@ include file="footer.jsp" %>


<%@ include file="/member/login_modal.jsp" %>	

</body>
</html>