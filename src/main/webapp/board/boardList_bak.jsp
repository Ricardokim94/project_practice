<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/dbconn.jsp"%>

<% //sql에서 정보받아오기
 String sql = "SELECT rownum, seqno, title, wdate, count, name";
 		sql += " FROM (";
		sql += " SELECT seqno,title,"; 
		sql += " TO_CHAR(b.wdate, 'yyyy\"년\"mm\"월\"dd\"일\" HH:MI:SS PM', 'nls_date_language=american') wdate,";
		sql += " count,name";
		sql += " FROM board b, member m";
		sql += " WHERE b.id = m.id order by wdate desc)";
		sql += " WHERE rownum between 1 and 10000";
		
	PreparedStatement stmt = conn.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
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
		
   <form name="boardForm" method="post" action="/board/boardForm.jsp">
	   	 <div class ="logo">
				<i class="fa-solid fa-clipboard"></i>
			 	<a href="">게시판</a>	
			 	<input type="submit" value="등록">
		</div>
   </form>
    
   
   <form method = "post" action= "/board/boardsearch.jsp">
   	
   	<input type="text" name="key">
   	<input type="submit" value = "검색">
   </form>
    
    
    <table class ="c">
	       <thead>
	        <tr>
	          <th>순번</th>
	          <th>제목</th>
	          <th>작성일자</th>
	          <th>조회수</th>
	          <th>작성자</th>
	        </tr>
	      </thead>
	      
	      <tbody>
			<% while(rs.next()) { %>
			      	
			      	<tr>
			          <th><%=rs.getString("rownum") %></th>
			          <th><a href ="/board/boardDetail.jsp?no=<%=rs.getInt("seqno") %>"><%=rs.getString("title") %></a></th>
			          <th><%=rs.getString("wdate") %></th>
			          <th><%=rs.getString("count") %></th>
			          <th><%=rs.getString("name") %></th>
			        </tr>
			        
			<% } %>   
	      </tbody>
 	</table>

	<div class ="as">
	<a href="#">◀</a>
	<a href="#">1</a>
	<a href="#">2</a>
	<a href="#">3</a>
	<a href="#">4</a>
	<a href="#">5</a>
	<a href="#">▶</a>
  
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