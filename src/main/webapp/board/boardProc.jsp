<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="/dbconn.jsp"%>

<%
String title = request.getParameter("title");
String content = request.getParameter("content");
String open = request.getParameter("open");
String sess_id = (String)session.getAttribute("id"); //창을 닫기 전까지 유지 //회원만 할수있게


String sql = "INSERT INTO board (seqno,title,content,open,id) values (board_seq.nextval,?,?,?,?)";

PreparedStatement stmt = conn.prepareStatement(sql); 		
stmt.setString(1,title);	
stmt.setString(2,content);
stmt.setString(3,open);
stmt.setString(4,sess_id);

int rs = stmt.executeUpdate();

	
	out.println("<script>");
	out.println("alert('게시글이 등록되었습니다')");
	out.println("location.href='/boardList.jsp'");
	out.println("</script>");
	
stmt.close();
conn.close();

%>
		 		