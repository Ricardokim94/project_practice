<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% //jsp코드
	String name = (String)session.getAttribute("sess_name"); //앞에(String)쓰면 강제로 변환된다
	boolean isLogin = false;
	if(name != null){
		isLogin = true;
	}
%>

<div class="topnav">
 	<i class="fa-solid fa-otter"></i>
 	<a href="/">Home</a>
  	<a href="http://localhost:8080/ex.html">Join us</a>
  	<a href="/board/boardList.bo">board</a>

<%	if(isLogin){ %> 
 	<a id="login" href=/member/mypage.jsp>마이페이지</a>
 	<a id="login" href ="logout.do">Logout</a>
 	<a id="login" style ="font-size : 20px;"><%= name %> 님 반갑습니다</a>
<%} else{ %>
 	<a id="login" href ="javascript:open1()">Login</a>
 	
<% } %>	
</div>