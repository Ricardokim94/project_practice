package service;

import java.util.List;

import dto.Board;
import dto.Criteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {

	public List<Board> list(Criteria cri); //dto에 있는거 import하면 된다.
	
	public Board searchBoard(String seqno);
	
	public String insertBoard(HttpServletRequest req, HttpServletResponse resp);
	
	public String update(HttpServletRequest req, HttpServletResponse resp);

	public int getTotalRec();

	public void delete(String seqno);
	
}
