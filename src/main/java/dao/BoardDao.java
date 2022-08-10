package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.OracleConn;
import dto.Board;
import dto.Reply;

public class BoardDao {  //데이터를 입출력하는 객체다 : Dao
			//DB컨넥션
	private final Connection conn = OracleConn.getInstance().getConn();
	
	public List<Board> boardList() {
		 
		List<Board>board = new ArrayList<Board>();
		
		String sql = "SELECT rownum, seqno, title, wdate, count, name";
	 		   sql += " FROM (";
	 		   sql += " SELECT seqno,title,"; 
			   sql += " TO_CHAR(b.wdate, 'yyyy\"년\"mm\"월\"dd\"일\" HH:MI:SS PM', 'nls_date_language=american') wdate,";
			   sql += " count,name";
			   sql += " FROM board b, member m";
			   sql += " WHERE b.id = m.id order by wdate desc)";
			   sql += " WHERE rownum between 1 and 10000";
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(sql//ResultSet.TYPE_SCROLL_INSENSITIVE,
											//ResultSet.CONCUR_UPDATABLE
					);
			ResultSet rs = stmt.executeQuery();
			
			//rs.last();
			
			//rs.beforeFirst(); //커서를 맨위 헤더로 이동해야함!
			//보드 담아야됨
			
			//int i=0;//배열에 넣어서(43열)증가 시켜야돼서 변수하나 선언
			while(rs.next()) {
				Board b =new Board(); //객체를 하나 만들어야됨!
				
				b.setNo(rs.getString("rownum")); //알리야스 준거로 해야됨!(13열) 테이블에 있는거 하면 안됨
				b.setTitle(rs.getString("title"));
				b.setWdate(rs.getString("wdate"));
				b.setName(rs.getString("name"));
				b.setCount(rs.getString("count"));
				b.setSeqno(rs.getString("seqno"));
				//board[i++] = b;
				board.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return board;
	}

	public Board boardDetail(String seqno) {
		
		Board board = new Board();//보드를 try밖에다 해줘야함 안에다 하면 return 할때 모름

		try {
			//조회수 카운트
			String sql = "update board set count = count+1 where seqno = " + seqno;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			//해당 게시물 세부내용 조회
			   sql = "select title , b.content content, b.id,";
			   sql+= " 			TO_CHAR(b.wdate, 'yyyy\\\"년\\\"mm\\\"월\\\"dd\\\"일(\\\"DY\\\")\\\" HH:MI:SS PM') wdate,";
			   sql+= " 			count,(select name from member m where m.id = b.id) name";
			   sql+= " from board b";
			   sql+= " where b.seqno=?";
			   sql+= " union all";
			   sql+= " select '', content, r.id,";
			   sql+= " 			TO_CHAR(r.wdate, 'yyyy\\\"년\\\"mm\\\"월\\\"dd\\\"일(\\\"DY\\\")\\\" HH:MI:SS PM'),";
			   sql+= " 			0, (select name from member m where m.id = r.id)";
			   sql+= " from reply r";
			   sql+= " where r.board_seqno = ?";
				
			   stmt = conn.prepareStatement(sql);
			   stmt.setString(1, seqno);
			   stmt.setString(2, seqno);
			   ResultSet rs = stmt.executeQuery(); //rs이제 결과를 보드로 담아야됨
			
			   		rs.next(); 
			   
			    	board.setTitle(rs.getString("title"));
			   		board.setContent(rs.getString("content"));
			   		board.setId(rs.getString("id"));
			   		board.setWdate(rs.getString("wdate"));
			   		board.setCount(rs.getString("count"));
			   		board.setName(rs.getString("name"));
			   	
			   		
			   		//rs.last();
			   		//Reply[] re = new Reply[rs.getRow()-1]; //마지막행번호를 re에 담음
			   		
			   		List<Reply> re = new ArrayList<Reply>();
			   		
			   		
			   		
//			   		
			   		//rs.beforeFirst(); //첫번째로가라다시
//			   		rs.next();
			   		
			   			while(rs.next()) {
			   			
				   			Reply reply = new Reply();
				   			reply.setId(rs.getString("id"));
				   			reply.setComment(rs.getString("content"));
				   			reply.setWdate(rs.getString("wdate"));
				   			
				   			re.add(reply);
				   			//re[i] = reply;
			   			}
//			   		
//			   		for(int i=0; i<re.length; i++) {
//			   			rs.next();
//			   			re[i].setId(rs.getString("id"));
//			   			re[i].setComment(rs.getString("content"));
//			   			re[i].setWdate(rs.getString("wdate"));
//			   		}
			   		
			   			board.setReply(re);		//마지막이 re니까 이걸 보드에 담으면 됨
			    
			   			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		
		return board;
	}

}














