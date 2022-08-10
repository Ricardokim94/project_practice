<%@page import="javax.print.attribute.HashPrintRequestAttributeSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="/dbconn.jsp"%>

<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String gender = request.getParameter("gender");

//취미 - 베열로 받아야 한다!!(1차원 배열)
/* out.print ("id :" + id);	
out.print ("pw :" + pw);	
out.print ("name :" + name);
out.print ("gender :" + gender);
out.print ("선택한 취미 :" ); */


String hobby[] = request.getParameterValues("hobby");
	String hobby_str = new String();
	for(int i=0; i < hobby.length; i++){
		hobby_str += hobby[i];
		if(i != hobby.length-1){
			hobby_str += ",";
	}
}
	
/* out.print(hobby_str); */
	
	
String email = request.getParameter("eid") + "@" + request.getParameter("domain");
String intro = request.getParameter("intro");
//sql문장 작성

Statement stmt = conn.createStatement();

/* String sql = "INSERT INTO member(id, pw, name, gender, hobby, email, intro)"
			+"VALUES ('" + id + "','" + pw + "','" + name + "','" + gender + "','" + hobby_str + "','" + email + "','" + intro+ "')";
 */
 
 String sql = String.format("INSERT INTO member" +
 				"(id, pw, name, gender, hobby, email, intro)" +
		 		" values('%s','%s','%s','%s','%s','%s','%s')",
		 		id, pw, name, gender, hobby_str, email, intro);
 
 
 System.out.println(sql);

int rs = stmt.executeUpdate(sql);
	if(rs > 0){	
		out.println("<script>");
		out.println("alert(' "+ name +" 님 저희와 함께 해주셨군요')");
		
		out.println("location.href='/index.jsp?stat=1'");
		out.println("</script>");
		
	}


%>












