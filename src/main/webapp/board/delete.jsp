<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="/dbconn.jsp"%>

<%
String seqno = request.getParameter("no");

//댓글먼저 삭제해야 된다.
String sql = "DELETE FROM reply WHERE board_seqno= ?";
PreparedStatement stmt = conn.prepareStatement(sql); 		
stmt.setString(1,seqno);	
int rs = stmt.executeUpdate();

//게시물 삭제
sql = "DELETE FROM board  WHERE seqno= ?";
stmt = conn.prepareStatement(sql); 		
stmt.setString(1,seqno);	
rs = stmt.executeUpdate();

//location 안하고 값 바로보내기!
response.sendRedirect("/boardList.jsp");

//자원반납	
stmt.close();
conn.close();

%>