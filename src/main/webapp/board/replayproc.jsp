<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="/dbconn.jsp"%>


<%
//id값 가져오기 => 로그인 세션값 가져오기
String sess_id = (String)session.getAttribute("id"); //창을 닫기 전까지 유지
String board_seqno = request.getParameter("board_seqno");
String content = request.getParameter("comment");

String sql = "INSERT INTO reply (seqno,content,id,board_seqno) values (reply_seq.nextval,?,?,?)";

PreparedStatement stmt = conn.prepareStatement(sql); 		
stmt.setString(1,content);	
stmt.setString(2,sess_id);
stmt.setString(3,board_seqno);

int rs = stmt.executeUpdate();


/*	
	out.println("<script>");
	out.println("alert('댓글이 등록되었습니다')");
	out.println("location.href='/board/boardDetail.jsp?no="+board_seqno+"'");
	out.println("</script>");
*/

//서버에서 브라우저로 전송할때  //reqest는 브라우저가 서버한테 
response.sendRedirect("boardDetail.jsp?no="+board_seqno);

//자원반납
stmt.close();
conn.close();

%>










		 		