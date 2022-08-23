package service;

import java.util.Map;

import javax.servlet.http.*;

import dao.MemberDao;


public class MemberServiceImp implements MemberService {

	@Override
	public Map<String, String> login(String id, String pw) {
		//MemberDao에 loginProc() 호출
		MemberDao mdo = new MemberDao();		
		return mdo.loginProc(id, pw);
	}

	@Override
	public int insert(HttpServletRequest req) {
		MemberDao mdo = new MemberDao();		
		return mdo.insertMember(req);
	}

}
