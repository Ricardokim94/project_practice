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
					<c:if test="${sess_name != null}">
						<input type="submit" value="등록">
					</c:if>
				</div>
			</form>

			<form method="post" action="/board/boardsearch.jsp">
				<input type="text" name="key"> 
				<input type="submit" value="검색">
			</form>

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

			<div class="as">
				<a href="#">◀</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a><a href="#">4</a> <a href="#">5</a> <a href="#">▶</a>
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