package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {

	
	Map<String, String> login(String id, String pw);

	int insert(HttpServletRequest req);
	
}
