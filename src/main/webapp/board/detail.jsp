<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    <div class="card">
		
  <!--<form name="boardForm" method="post" action="/board/boardForm.jsp">-->
	   <div class ="logo">
			<i class="fa-solid fa-clipboard"></i>
		 	<a href="">★상세내용★</a>	
		 	
		 	<c:set value="${board}" var="board" />
		 	
		 	<button class = "button1" style=float:right onclick="location.href='change.jsp?no=${board.seqno}'">수정</button>
		 	<button class = "button1" style=float:right onclick="location.href='delete.jsp?no=${board.seqno}'">삭제</button>
		 	 <hr>
				 <div class = "cccc">	
				 	★제목 : ${board.seqno}<hr>
				 	★작성날짜 : ${board.wdate}<hr>
				    ★작성자 : ${board.name}<hr>
				    ★조회수 : ${board.count}<hr>
				    ★내용 :${board.content}<hr>
				 	<hr>
				     <form method = "post" action="replayproc.jsp">
				     	<input type ="hidden" name="board_seqno" value="${board.seqno}">
					    <textarea name="comment" placeholder="댓글작성" rows="2" cols="50"></textarea>
					    <input type = "submit" value = "등록">
					  </form>	
				</div>	
			 
					<!-- 첨부파일 -->
					<div>
						<c:set value="${board.attachfile}" var="attachfile" />
						<c:if test="${attachfile != null}">
						<c:forEach items="${attachfile}" var="file">
							(파일 이름 : ${file.fileName}) 
							(파일 사이즈 :${file.fileSize})
						</c:forEach>
						</c:if>
					</div>
				
				<div class = reply> 
			    <c:set value="${board.reply}" var="reply" />
			    <c:if test="${reply != null}">
			    	<c:forEach items="${reply}" var="r" >
			    		${r.comment} 
						${r.name}
						${r.wdate}
						<hr>
			    	</c:forEach>
			    </c:if>
		    	</div>  
		
		</div>
    
   
	
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