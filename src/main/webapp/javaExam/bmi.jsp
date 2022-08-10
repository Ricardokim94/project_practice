<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>

<h1>당신의 몸무게는</h1>

<div id = modal>
	<form method="post" action="bmiProc.jsp">
		<input type="text"  placeholder="height"  required name="height"><br>
		<input type="text"  placeholder="weight"  required name="weight"><br>
		<input type="submit" value="result">
	</form>
</div>

<img src = "/project_kim/javaExam/img/bmi.jpg">

