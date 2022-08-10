<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file= "/dbconn.jsp" %>

<%
 String id = request.getParameter("id"); //변수이름 id를 가져온다는 거임 

//2.sql문 작성
/* String sql = "Select * from member where id = ?"; */ //sql변수를 만듦 = 아이디만 일단 있는지 보는거임
Statement stmt = conn.createStatement();  //데이터 타입을 prepareStament로 받음
String sql = String.format("select * from member where id = '%s'", id);
ResultSet rs = stmt.executeQuery(sql); 
//sql문장이 실행된다 + 반환해 준다 (ResultSet 타입으로)

//레코드가 있으면 중복 : out.print('1');
//레코드가 있으면 중복 아니면 : out.print('0');
/* rs.next(); */ //이거해야지 첫번째 가르키게 된다.


if(rs.next()){
	out.print('1');
}else{
	out.print('0');
}

%>















