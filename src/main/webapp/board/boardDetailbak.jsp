<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title> board </title>

<%@ include file="/header.jsp" %>

<%@ include file="/common.jsp" %>

<%@ include file="/menu.jsp" %>

<%@ include file="/dbconn.jsp" %>


</head>

<% 
String seqno = request.getParameter("no");
//조회수 카운트
String sql = "update board set count = count + 1 where seqno = " + seqno;
PreparedStatement stmt = conn.prepareStatement(sql);
//insert, update, delete 문장은 executeupdate() 이용.
stmt.executeUpdate();
//해당 게시물 세부내용 조회
      sql = "select title, b.content bcontent,"; 
      sql += " TO_CHAR(b.wdate, 'yyyy\"년\"mm\"월\"dd\"일(\"DY\")\" HH:MI:SS PM', 'nls_date_language=american') board_wdate, count,";
      sql += " (select name from member m where m.id = b.id) name";
      sql += " from board b";
      sql += " where b.seqno=?";
   stmt = conn.prepareStatement(sql);
   stmt.setString(1, seqno);
   ResultSet rs = stmt.executeQuery();
%>

<link rel="stylesheet" href="/css/board.css">
<script src="https://kit.fontawesome.com/737ede07db.js" crossorigin="anonymous"></script>


<div class="row">
  <div class="leftcolumn">
    <div class="card" >
      <button type="button" onclick="location.href='/board/board_change.jsp?no=<%= seqno %>'">수정</button>
      <button type="button" onclick="location.href='/board/boardDelete.jsp?no=<%= seqno %>'">삭제</button>
      <hr>
   
   <% if(rs.next()) { %> 
   <table class="detail">
      <thead>
         <tr>
            <th colspan='3'><%=rs.getString("title") %></th>
           </tr>
      </thead>
      <tbody>
         <tr>
            <td><%=rs.getString("board_wdate") %></td>
            <td><%=rs.getString("name") %></td>
            <td><%=rs.getString("count") %></td>
         </tr>
         <tr>
            <td colspan='3'><%=rs.getString("bcontent") %></td>
         </tr>
      </tbody>
   </table>
   <hr>
   
   <form method="post" action="/replayProc.jsp">   
      <input type="hidden" name="board_seqno" value="<%=seqno %>">
      <textarea name="comment" style="width:100%; height:60px;" rows="5" cols="50" placeholder="댓글을 입력하세요"></textarea>
      <input style="float:right" type="submit" value="등록">
   </form>
   
   <% 
   
   sql = "select content,(select name from member m where m.id=r.id) name,";
   sql += " TO_CHAR(r.wdate, 'yyyy/mm/dd HH24:MI:SS') wdate";
   sql += " from reply r";
   sql += " where board_seqno = ? order by wdate desc";
   
   stmt = conn.prepareStatement(sql);
   stmt.setString(1, seqno);
   rs = stmt.executeQuery();
   %>
   
   <table class="detail">
   <thead>
      <tr>
         <th>작성자</th>
         <th colspan='2'>댓글내용</th>
         <th>작성일자</th>
      </tr>
   </thead>
   
   <% while(rs.next()) {%>
      <tbody>
         <tr>
            <td><%=rs.getString("name") %></td>
            <td colspan='2'><%=rs.getString("content") %></td>         
            <td><%=rs.getString("wdate") %></td>
         </tr>
      </tbody>
   <% } %>
   </table>
   <% } else { out.println("게시물이 없습니다");}%> 
   </div>
  </div>
  
  <div class="rightcolumn">
    <div class="card">
      <h2>About Me</h2>
      <div class="fakeimg" style="height:100px;">Image</div>
      <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
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

<%@ include file="/footer.jsp" %>
<!-- 화면 끝 -->

<%@ include file="/member/login_modal.jsp" %>

</body>
</html>