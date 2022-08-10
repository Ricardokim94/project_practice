<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "dto.*" %>
<%@ page import= "java.util.*" %>

<%
Board b = (Board)request.getAttribute("board"); //가져오려면 request.getA~해야된다.
%>

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
		 	<button class = "button1" style=float:right onclick="location.href='change.jsp?no=<%= b.getSeqno()%>'">수정</button>
		 	<button class = "button1" style=float:right onclick="location.href='delete.jsp?no=<%= b.getSeqno()%>'">삭제</button>
		 	 <hr>
				 <div class = "cccc">	
				 	★제목 : <%=b.getTitle() %><hr>
				 	★작성날짜 : <%=b.getWdate() %><hr>
				    ★작성자 : <%=b.getName() %><hr>
				    ★조회수 : <%=b.getCount() %><hr>
				    ★내용 :<%=b.getContent() %><hr>
				 	<hr>
				     <form method = "post" action="replayproc.jsp">
				     	<input type ="hidden" name="board_seqno" value="<%= b.getSeqno() %>">
					    <textarea name="comment" placeholder="댓글작성" rows="2" cols="50"></textarea>
					    <input type = "submit" value = "등록">
					  </form>	
				</div>	
			 
			
				<div class = reply> 
			      <% int num =1;
			      				
			      	List<Reply> re = b.getReply();
			      if(!re.isEmpty()){
			      	for(int i=0; i<re.size(); i++) {
				      	Reply r = re.get(i);
				      	out.print(num++); 
				      	out.print(r.getComment());
				      	out.print(r.getId());
				      	out.print(r.getWdate());
				      	out.print("<hr>");
				     	 }
			      }
				     	 %>
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