package service;

import java.util.List;

import dto.Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {

	public List<Board> list(); //dto에 있는거 import하면 된다.
	
	public Board searchBoard(String seqno);
	
	public void insertBoard(HttpServletRequest req, HttpServletResponse resp);
	
}
