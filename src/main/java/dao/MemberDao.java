package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import common.OracleConn;
import oracle.jdbc.OracleTypes;

public class MemberDao {

	private final Connection conn = OracleConn.getInstance().getConn();
	PreparedStatement stmt = null;

	public Map<String, String> loginProc(String id, String pw){
		Map<String, String> status= new HashMap<String, String>();

		//2.sql문 작성
		String sql = "Select * from member where id = ?";  
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery(); 

			if(rs.next()) {
				if(rs.getString("pw").equals(pw)){ 
					status.put("login", "ok"); 
					status.put("name", rs.getString("name"));
				}else{
					status.put("login", "pwFail"); 
				}
			}
			else {
				status.put("login", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return status;
	}

	public int insertMember(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		String hobby[] = request.getParameterValues("hobby");
		String hobby_str = new String();
		for(int i=0; i < hobby.length; i++){
			hobby_str += hobby[i];
			if(i != hobby.length-1){
				break;
			}
			hobby_str += ",";
		}

		String email = request.getParameter("eid") + "@" + request.getParameter("domain");
		String intro = request.getParameter("intro");
		//sql문장 작성
		Statement stmt;
		int rs = 0;
		try {
			stmt = conn.createStatement();
			String sql = String.format("INSERT INTO member" +
					"(id, pw, name, gender, hobby, email, intro)" +
					" values('%s','%s','%s','%s','%s','%s','%s')",
					id, pw, name, gender, hobby_str, email, intro);
			System.out.println(sql);
			rs = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public int selectByid(String id) {
		CallableStatement stmt = null;
		int rs = 0;
		String sql ="call p_idDoubleCheck(?, ?)";
		try {
			stmt = conn.prepareCall(sql);
			stmt.setNString(1, id);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			
			rs = stmt.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	
	
	
	
	
	//	private void resourceClose() {
	//		//자원반납
	//		try {
	//			stmt.close();
	//			conn.close();
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}
	//	}
}



