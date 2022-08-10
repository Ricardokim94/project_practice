<%@page import="java.nio.channels.SelectableChannel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>

<%@ include file= "/dbconn.jsp" %>

<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");

/* out.println("아이디 :" + id);
out.println("비밀번호 :" + pw);  */

//2.sql문 작성

String sql = "Select * from member where id = ?"; //sql변수를 만듦 = 아이디만 일단 있는지 보는거임
PreparedStatement stmt = conn.prepareStatement(sql);  //데이터 타입을 prepareStament로 받음

stmt.setString(1, id);
ResultSet rs = stmt.executeQuery(); //sql문장이 실행된다 + 반환해 준다 (ResultSet 타입으로)


if(rs.next()) {
	if(rs.getString("pw").equals(pw)){ //equals가 같으면 true 틀리면 false
		
		//'로그인이 되었으면' 세션변수 설정한다 (==>창이 닫기 전까지 유지)
		session.setAttribute("id",rs.getString("id"));  //세션변수,데이터
		session.setAttribute("name",rs.getString("name"));  
		session.setAttribute("login_time",session.getCreationTime()); //시간을 주는는 것  
		
		out.println("<script>");			
		out.println("alert('로그인 되었습니다')");			
		out.println("location.href='/'");	
		out.println("</script>");			
	}else{
		out.println("<script>");			
		out.println("alert('비밀번호가 틀렸습니다')");			
		out.println("location.href='/index.jsp?stat=1'");  //뒤로가기 기능			
		out.println("</script>");			
	}
	
	}else{
		out.println("<script>");			
		out.println("alert('아이디 정보가 없습니다')");			
		out.println("location.href='/index.jsp?stat=1'");			
		out.println("</script>");			
		
	}

//rs.next(); //결과 셋에서 한번실행할 때 마다 한단계 다음으로 넘어가줌

/* out.println("아이디 :" + rs.getString("id"));  //table 필드 이름
out.println("비밀번호 :" + rs.getString("pw")); //table 필드 이름
out.println("이름 :" + rs.getString("name")); //table 필드 이름
out.println("등록일자 :" + rs.getString("wdate")); //table 필드 이름 */

//자원반납
stmt.close();
conn.close();

%>






