<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="/dbconn.jsp"%>

<%
	String seqno = request.getParameter("no");
		
	String sql = "select title, content, open from board where seqno = ?";

	PreparedStatement stmt = conn.prepareStatement(sql);		
	stmt.setString(1,seqno);	
	ResultSet rs = stmt.executeQuery(); 
	
	rs.next();
	
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
	      <h2>★☆게시판 등록☆★</h2>
	      
	      <form name="boardForm" method="post" action="changeproc.jsp">
		    <div class="card">
				 <input type ="hidden" name="seqno" value="<%= seqno %>"></input>
			     <input type="text" name="title" value="<%=rs.getString("title") %>"required>
				 <input type="radio" name="open" value='Y' 
				 	<% if(rs.getString("open").equals("Y") ){out.print("checked");} %>> 공개
				 <input type="radio" name="open" value='N'
				 	<% if(rs.getString("open").equals("N") ){out.print("checked");} %>> 비공개
			     <textarea rows="50" style="width: 100%" placeholder="내용" name="content"><%=rs.getString("content") %></textarea>
	  	  		 <input type="submit" value="수정">
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