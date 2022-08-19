<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


    <!DOCTYPE html>
<html>
<head>
	<link rel ="stylesheet" href="/board/board.css">
	<title>게시판</title>
	<script src="https://kit.fontawesome.com/236f0b5985.js" crossorigin="anonymous"></script>
</head>

<%@ include file="/common.jsp" %>

<%@ include file="/header.jsp" %>

<%@ include file="/menu.jsp" %>


<div class="row">
  <div class="leftcolumn">
	      <h2>★☆게시판 등록☆★</h2>
	      
	      
	      <c:set value="${board}" var="board" />
	      <form name="boardForm" method="post" action="changeproc.jsp">
		    <div class="card">
				 <input type ="hidden" name="seqno" value="${board.seqno}"></input>
			     <input type="text" name="title" value="${board.title}" required>
			     <textarea rows="50" style="width: 100%" placeholder="내용" name="content">${board.content}</textarea>
				 <input type="radio" name="open" value='Y' 
				 	<c:if test="${board.open == 'Y'}"> checked </c:if>> 공개
				 <input type="radio" name="open" value='N'
				 	<c:if test="${board.open == 'N'}"> checked </c:if>> 비공개
				 	
	  	  		 
	  	  		 <!-- 첨부파일 -->
	  	  		 <c:set value= "${board.attachfile }" var="attachfile" />
	  	  		 <c:choose>
	  	  		 	<c:when test="${empty attachfile}">
	  	  		 		<input type="file" name="filename">
	  	  		 	</c:when>
	  	  		 <c:when test="${!empty attachfile }">
	  	  		 	<c:forEach items="${attachfile }" var="file" >
	  	  		 		<c:set value="${file.filetype}" var="filetype" />
	  	  		 		<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
	  	  		 	
	  	  		 		<c:if test="${type eq 'image' }">
	  	  		 			<c:set value="${file.thumbnail.fileName }" />
	  	  		 			<img src="upload/thumbmail/${thumb_file}">
	  	  		 		</c:if>	
	  	  		 		${file.fileName} (사이즈:${file.fileSize})
	  	  		 	</c:forEach>
	  	  		 </c:when>
	  	  		 </c:choose>	
	  	  		 <input type="submit" value="수정">
	  	  		 <input type="submit" value="삭제">
	  	  		
	  	  	</div>
	  	  </form>
	
				    
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

<div class="footer">
  <h2>Footer</h2>
</div>