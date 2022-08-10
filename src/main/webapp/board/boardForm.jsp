<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
<html>
<head>
	<link rel ="stylesheet" href="/board/board.css">
	<title>게시판</title>
	<script src="https://kit.fontawesome.com/236f0b5985.js" crossorigin="anonymous"></script>
</head>

<%@ include file="/common.jsp" %>

<%@ include file="/header.jsp" %>

<%@ include file="/menu.jsp" %>


<div class="row">
  <div class="leftcolumn">
	      <h2>★☆게시판 등록☆★</h2>
	      
	      <form name="boardForm" enctype="multipart/form-data" method="post" action="boardReg.bo" onsubmit = "return check(this)">
		    <div class="card">
			     <input type="text" placeholder="제목" name="title">
				 <input type="radio" name="open" value='Y'> 공개
				 <input type="radio" name="open" value='N'> 비공개
			     <textarea rows="50" style="width: 100%" placeholder="내용" name="content"></textarea>
			     <input type="file" name="filename">
			     
			     
	  	  		 <input type="submit" value="작성완료">
	  	  	</div>
	  	  </form>
	
				<script>
					function check(f){
						if(f.open.value ==""){
							alert("공개여부를 체크하시오")
							return false;
						}
						return true;
					}
				</script>
    
    
   </div>
  <div class="rightcolumn">
    <div class="card">
      <h2>Save the endangered otter</h2>
      <!-- <div class="fakeimg" style="height:200px;">Image</div> -->
      <img src ="/img/기도.jpg">
    </div>
    <div class="card">
      <h3>Popular Post</h3>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
    </div>
    <div class="card">
      <h3>Follow Me</h3>
      <p>Some text..</p>
    </div>
  </div>
</div>

<div class="footer">
  <h2>Footer</h2>
</div>