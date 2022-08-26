<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/board/board.css">
<title>게시판</title>
<script src="https://kit.fontawesome.com/236f0b5985.js"
	crossorigin="anonymous"></script>
</head>

<%@ include file="/common.jsp"%>
<%@ include file="/header.jsp"%>
<%@ include file="/menu.jsp"%>



<div class="row">
	<div class="leftcolumn">
		<div class="card">

			<form name="boardForm" method="post" action="boardRegForm.bo">
				<div class="logo">
					<i class="fa-solid fa-clipboard"></i> 
					<a>게시판</a>
				</div>
			</form>
			
			<div class="search">
			<form name="search" method="post" action="boardList.bo">
				<input type="text" name="key" placeholder="제목을 입력하세요"> 
				<input type="submit" value="검색">
				<!-- 페이지당 레코드수 -->
				<select name="rowPerPage" onchange="goAction()">
					<c:forEach var="i" begin="5" end="40" step="5">
					<option value="${i}"
					<c:if test="${i == pageMaker.cri.rowPerPage}"> selected</c:if>
					>${i}개씩</option>
					</c:forEach>
				</select>
			</form>
			</div>

			<script>
				function goAction(){
					document.forms['search'].submit();
				}
			</script>




			<c:if test="${loginUser != null}">
				<input type="submit" value="등록">
			</c:if>

			<table class="c">
				<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>작성일자</th>
						<th>조회수</th>
						<th>작성자</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${board}" var="board">
						<tr>
							<th>${board.getNo()}</th>
							<th><a href="boardDetail.bo?seqno=${ board.getSeqno()}">${board.getTitle()}</a></th>
							<th>${board.getWdate()}</th>
							<th>${board.getCount()}</th>
							<th>${board.getName()}</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
	
			<p>총레코드개수 : ${pageMaker.total}</p>
	
			<p></p>
			
			<div class="pagination">
				<c:if test="${pageMaker.prev }">
				 <a href="boardList.bo?currentPage=${pageMaker.startPage-1}&rowPerPage=${pageMaker.cri.rowPerPage}">&laquo;</a>
			  	</c:if>
			  
			  
			  <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
			  	<a href="/boardList.bo?currentPage=${num}&rowPerPage=${pageMaker.cri.rowPerPage}"
			  	 class="${pageMaker.cri.currentPage == num ? "active" : " " }">${num}</a>
			  </c:forEach>
			  
			<!--    <a class="active" href="#">2</a>-->
			 
			  <c:if test="${pageMaker.next }">
			  <a href="boardList.bo?currentPage=${pageMaker.endPage+1}&rowPerPage=${pageMaker.cri.rowPerPage}">&raquo;</a>
			  </c:if>
			</div>
		</div>
	</div>
	<div class="rightcolumn">
		<div class="card">
			<h2>Save the endangered otter</h2>
			<img src="/img/기도.jpg">
		</div>
		<div class="card">
			<h3>Popular Post</h3>
			<div class="fakeimg">
				<p>Image</p>
			</div>
			<div class="fakeimg">
				<p>Image</p>
			</div>
			<div class="fakeimg">
				<p>Image</p>
			</div>
		</div>
		<div class="card">
			<h3>Follow Me</h3>
			<p>Some text..</p>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp"%>
<%@ include file="/member/login_modal.jsp"%>

</body>
</html>