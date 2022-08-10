<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@ include file= "../index.jsp" %>  

    <%!
    	int num =10;
    %>
    
    
    <%
    	if(num > 0){
    %>
    	<p>참!</p>
    <%
    	} else{
    %>
		<p> 거짓 </p>    	
    <%
    	}
    %>	
    <!--  html주석 -->
    <%--jsp주석 --%>
     
    <%--  <p>num 값은 : <%=num %></p>   --%>
     