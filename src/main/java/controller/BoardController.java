package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardServiceImp;

@WebServlet(urlPatterns = {"*.bo"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		BoardServiceImp bs = new BoardServiceImp(); // 계속 쓸꺼니까 밖으로 뱀 (원래는 if문안에 있었음)
		
		if(cmd.equals("boardList.bo")) {
			//게시판리스트
			List<Board> board = bs.list(); //보드배열타입의 보드로 받아줘야된다 list를
			req.setAttribute("board", board); //페이지에 넘길때는 이렇게 넘김
			goView(req, resp, "/board/boardList.jsp");
		}else if(cmd.equals("boardDetail.bo")) {  //제목눌렀을때 같으면 이거 실행해라
			//게시판 댓글
			String seqno = req.getParameter("seqno");
			Board b = bs.searchBoard(seqno);
			req.setAttribute("board", b); //jsp 뷰에다 넘기려면 setA~해줘야한다.
			goView(req, resp, "/board/boardDetail.jsp");
		}else if(cmd.equals("boardRegForm.bo")) {
			//등록버튼 누르면 등록 뜨는것
			goView(req, resp, "/board/boardForm.jsp");
		}else if(cmd.equals("boardReg.bo")) { //boardForm 25줄
			bs.insertBoard(req, resp);
		}
	}
	
	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(viewPage); 
		//보여줄 파일이름 적으면됨
		rd.forward(req, resp);
	}
	
}
