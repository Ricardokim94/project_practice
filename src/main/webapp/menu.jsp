<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set value="${loginUser}" var="loginuser" />
<div class="topnav">
 	<i class="fa-solid fa-otter"></i>
 	<a href="/">Home</a>
  	<a href="http://localhost:8080/ex.html">Join us</a>
  	<a href="/board/boardList.bo">board</a>

	<c:if test="${loginuser != null }">
	 	<a id="login" href=/member/mypage.jsp>마이페이지</a>
	 	<a id="login" href ="logout.do">Logout</a>
	 	<a id="login" style ="font-size : 20px;">${loginuser.name} 님 반갑습니다</a>
	</c:if> 	
	
	<c:if test="${loginuser eq null }">
	 	<a id="login" href ="javascript:open1()">Login</a>
	</c:if>
</div>