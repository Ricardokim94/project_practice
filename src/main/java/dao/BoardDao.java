package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.OracleConn;
import dto.AttachFile;
import dto.Board;
import dto.Criteria;
import dto.Reply;
import dto.Thumbnail;
import oracle.jdbc.OracleTypes;

public class BoardDao {  //데이터를 입출력하는 객체다 : Dao
			//DB컨넥션
	private final Connection conn = OracleConn.getInstance().getConn();
	
	public List<Board> boardList(Criteria cri) {
		
		CallableStatement stmt = null;
		List<Board>board = new ArrayList<Board>();
		
		String sql = "call p_getboardlist(?,?,?,?,?)";
		
		try {
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerPage());
			stmt.setString(3, "");
			stmt.setString(4, "");
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(5);
			
			
			while(rs.next()) {
				Board b =new Board(); //객체를 하나 만들어야됨!
				
				b.setNo(rs.getString("rn")); 
				b.setTitle(rs.getString("title"));
				b.setWdate(rs.getString("wdate"));
				b.setName(rs.getString("name"));
				b.setCount(rs.getString("count"));
				b.setSeqno(rs.getString("seqno"));

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
			   sql = "select title , b.content content, b.id, b.open, ";
			   sql+= " 			TO_CHAR(b.wdate, 'yyyy\\\"년\\\"mm\\\"월\\\"dd\\\"일(\\\"DY\\\")\\\" HH:MI:SS PM') wdate,";
			   sql+= " 			count,(select name from member m where m.id = b.id) name";
			   sql+= " from board b";
			   sql+= " where b.seqno=?";
			   sql+= " union all";
			   sql+= " select '', content, r.id,'', ";
			   sql+= " 			TO_CHAR(r.wdate, 'yyyy\\\"년\\\"mm\\\"월\\\"dd\\\"일(\\\"DY\\\")\\\" HH:MI:SS PM'),";
			   sql+= " 			0, (select name from member m where m.id = r.id)";
			   sql+= " from reply r";
			   sql+= " where r.board_seqno = ?";
				
			   stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
						  ResultSet.CONCUR_UPDATABLE);
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
			   		board.setOpen(rs.getString("open"));
			   		board.setSeqno(seqno);
			   		
			   		//rs.last();
			   		//Reply[] re = new Reply[rs.getRow()-1]; //마지막행번호를 re에 담음
			   		List<Reply> re = new ArrayList<Reply>();
			   		//rs.beforeFirst(); //첫번째로가라다시
//			   		rs.next();
			   			while(rs.next()) {
				   			Reply reply = new Reply();
				   			reply.setId(rs.getString("id"));
				   			reply.setComment(rs.getString("content"));
				   			reply.setWdate(rs.getString("wdate"));
				   			reply.setName(rs.getString("name"));
				   			re.add(reply);
				   			//re[i] = reply;
			   			}
//			   		for(int i=0; i<re.length; i++) {
//			   			rs.next();
//			   			re[i].setId(rs.getString("id"));
//			   			re[i].setComment(rs.getString("content"));
//			   			re[i].setWdate(rs.getString("wdate"));
//			   		}

			   			board.setReply(re);		//마지막이 re니까 이걸 보드에 담으면 됨
			   			
			   
			   	//첨부파일 저장
	   			sql = "SELECT * FROM attachfile where board_seq = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, seqno);			
				rs = stmt.executeQuery();
						
				List<AttachFile> fileList = new ArrayList<AttachFile>();
					
				while(rs.next()) {
					AttachFile attachfile = new AttachFile();
					attachfile.setNo(rs.getString("no"));
					attachfile.setFileName(rs.getString("filename"));
					attachfile.setSaveFileName(rs.getString("savefilename"));
					attachfile.setFileSize(rs.getString("filesize"));
					attachfile.setFiletype(rs.getString("filetype"));
					attachfile.setFilePath(rs.getNString("filepath"));				
					
					if(rs.getString("filetype").contains("image")) {				
						sql = "SELECT * FROM attachfile_thumb WHERE attach_no = ? ";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, rs.getString("no"));			
						ResultSet rs2 = stmt.executeQuery();
						
						while(rs2.next()) {
							Thumbnail th = new Thumbnail();	
							th.setNo(rs2.getString("no"));
							th.setFileName(rs2.getString("filename"));
							th.setFileSize(rs2.getString("filesize"));
							th.setFilePath(rs2.getString("filepath"));
							attachfile.setThumbnail(th);
						}
					}
					fileList.add(attachfile);
				}
				
				board.setAttachfile(fileList);
			   			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return board;
	}

	
	public String insert(Board board, AttachFile attachFile) {
		
		String sql = "insert into board(seqno, title, content, open, id) values (board_seq.nextval,?,?,?,?)";
		PreparedStatement stmt;
		
		String seqno = null;
		
		   try {
			   //false를 주면 자동으로 commit이 안된다.
			  
			   conn.setAutoCommit(false);
			   
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setString(3, board.getOpen());
			stmt.setString(4, board.getId());
			stmt.executeQuery();
			
			//보드 시퀀스넘버 가져오는 것
			sql = "select max(seqno) as seqno from board";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			seqno = rs.getString("seqno");
			
			//첨부파일
			if(attachFile != null) {
				
				String attach_no = insertAttachFile(seqno, attachFile);				
				String fileType = attachFile.getFiletype();				
				
				//썸네일
				if(fileType.substring(0, fileType.indexOf("/")).equals("image")) {
					insertThumbNail(attach_no, attachFile);
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {						
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				System.out.println("rollback처리됨");
			}
			e.printStackTrace();
		}
		
		return seqno; 
	}
	
	void insertThumbNail(String attach_no, AttachFile attachFile) {
		//썸네일 저장
		String sql = "INSERT INTO attachfile_thumb(no, filename, filesize, filepath, attach_no) "
		    + " VALUES (ATTACHFILE_THUMB_SEQ.NEXTVAL, ?,?,?,?)";
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			Thumbnail thumb = attachFile.getThumbnail();
			stmt.setString(1, thumb.getFileName());
			stmt.setString(2, thumb.getFileSize());
			stmt.setString(3, thumb.getFilePath());
			stmt.setString(4, attach_no);
			stmt.executeQuery();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	
	String insertAttachFile(String seqno, AttachFile attachFile) {
		//첨부파일저장
		String sql = "INSERT INTO attachFile(no, filename, savefilename, filesize, filetype, filepath, board_seq)"
			+ " VALUES (ATTACHFILE_SEQ.NEXTVAL, ?,?,?,?,?,?)";
		PreparedStatement stmt;
		String attach_no = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, attachFile.getFileName());
			stmt.setString(2, attachFile.getSaveFileName());
			stmt.setString(3, attachFile.getFileSize());
			stmt.setString(4, attachFile.getFiletype());
			stmt.setString(5, attachFile.getFilePath());
			stmt.setString(6, seqno);
			stmt.executeQuery();
			
			sql = "SELECT max(no) FROM attachFile";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			attach_no =rs.getString(1);			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attach_no;
	}		

	public void update(Board board, AttachFile attachFile) {
		//보드 update
		String sql="call p_updateBoard(?,?,?,?)";
		CallableStatement stmt;
		try {
			stmt = conn.prepareCall(sql);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setString(3, board.getOpen());
			stmt.setString(4, board.getSeqno());
			stmt.executeQuery();
			//첨부파일
			if(attachFile != null) {				
				String attach_no = insertAttachFile(board.getSeqno(), attachFile);				
				String fileType = attachFile.getFiletype();				
				
				//썸네일
				if(fileType.substring(0, fileType.indexOf("/")).equals("image")) {
					insertThumbNail(attach_no, attachFile);
				}
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
	}


	public int getTotalRec() {
		int total =0;
		
		String sql="select count(*) as total from board ";
		PreparedStatement stmt;
		try {
			stmt=conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			total = rs.getInt("total");		
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
		return total;
	}

	//게시물 삭제
	public Map<String, String> deleteByNo(String seqno) {
		Map<String, String> map = new HashMap<String, String>();
		CallableStatement stmt;
		String sql ="call p_deleteBaord(?,?,?,?)";
		try {
			stmt = conn.prepareCall(sql);
			stmt.setString(1, seqno);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			stmt.executeQuery();
			map.put("savefilename", stmt.getString(2));
			map.put("filepath", stmt.getString(3));
			map.put("thumb_filename", stmt.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}













