package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import dao.MemberDao;
import dto.Member;


public class MemberServiceImp implements MemberService {
	MemberDao mdo = new MemberDao();
	
	@Override
	public Map<String, String> login(String id, String pw) {
		//MemberDao에 loginProc() 호출
		return mdo.loginProc(id, pw);
	}

	@Override
	public int insert(HttpServletRequest req) {
		return mdo.insertMember(req);
	}
	
	@Override
	public int idDoubleCheck(String id) {
		return mdo.selectByid(id);
	}

	@Override
	public List<Member> list() {
		return mdo.getMember();
	}
	

}
