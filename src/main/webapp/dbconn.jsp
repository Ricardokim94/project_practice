<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.sql.*" %>
<%    
Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:xe";
Connection conn = DriverManager.getConnection(url,"joy","cmrikim"); 
System.out.println("오라클 연결완료");  
%>