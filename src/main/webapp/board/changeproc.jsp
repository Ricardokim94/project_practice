<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/dbconn.jsp" %>

<%
String seqno = request.getParameter("seqno");
String title = request.getParameter("title");
String content = request.getParameter("content");
String open = request.getParameter("open");

String sql = "UPDATE board SET title=?, content=?, open=? WHERE seqno = ? ";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, title);
stmt.setString(2, content);
stmt.setString(3, open);
stmt.setString(4, seqno);
int rs = stmt.executeUpdate();
System.out.println("레코드 삽입 갯수 : " +  rs);

//서버에서 브라우저로 전송할때 response 객체 사용
response.sendRedirect("boardDetail.jsp?no="+seqno);

//자원반납
stmt.close();
conn.close();


%>