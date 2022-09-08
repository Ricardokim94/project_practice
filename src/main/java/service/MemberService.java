package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

	
	Map<String, String> login(String id, String pw);

	int insert(HttpServletRequest req);
	
	public int idDoubleCheck(String id);

	public List<Member> list();
	
	
}
