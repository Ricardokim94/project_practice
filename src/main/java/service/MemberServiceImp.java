package service;

import java.util.Map;

import dao.MemberDao;
import javax.servlet.http.HttpServletRequest;

public class MemberServiceImp implements MemberService {
	MemberDao dao= new MemberDao();

	@Override
	public Map<String, String> login(String id, String pw) {
		return dao.loginProc(id, pw);
	}

	@Override
	public int insert(HttpServletRequest req) {
			
		return dao.insertMember(req);
	}

}



