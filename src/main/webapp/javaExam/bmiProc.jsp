<%@page import="javax.print.DocFlavor.STRING"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	 <%@ page import="java.lang.Math"%>
     <%@ page import="java.sql.*" %>
  <%
  String w =request.getParameter("weight");
  String h= request.getParameter("height");	//무조건 문자열임
	 int int_h =Integer.parseInt(h);
 	 int int_w =Integer.parseInt(w);
	
 	 double bmi=0.0;
  //int_h = int_h*int_h/100;
  /* bmi =  int_w/int_h; */
	 bmi = int_w / Math.pow(int_h*0.01,2); 
	String result;	
  
	if(bmi<18.5){
		result = "초멸치";
	}  
	else if(bmi<24.9){
		result = "멸치";
	}  
	else if(bmi>29.9){
		result = "정상";
	}  
	else if(bmi>34.9){
		result = "근돼";
	}  
	else {
		result = "초근돼";
	}  
  /* System.out.println(String.format("%.2f", bmi));  */
	%>
	당신의 bmi :<%= bmi %>  <br>  당신은<%= result %>
	
	
	
		
	