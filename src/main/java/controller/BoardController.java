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
import dto.Criteria;
import dto.Page;
import service.BoardService;
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
			String currentPage =req.getParameter("currentPage");
			String rowPerPage = req.getParameter("rowPerPage");
			if(currentPage == null) currentPage ="1";
			if(rowPerPage == null) rowPerPage ="3";
			
			Criteria cri = new Criteria(Integer.parseInt(currentPage), Integer.parseInt(rowPerPage));
			
			List<Board> board = bs.list(cri); //보드배열타입의 보드로 받아줘야된다 list를
			
			//페이지 객체를 만듬
			req.setAttribute("pageMaker", new Page(bs.getTotalRec(), cri)); //페이지 객체 만들어서 pageMaker에 담음
			req.setAttribute("board", board); //페이지에 넘길때는 이렇게 넘김
			goView(req, resp, "/board/boardList.jsp");
		}else if(cmd.equals("boardDetail.bo")) {  //제목눌렀을때 같으면 이거 실행해라
			//게시판 세부내용 출력
			String seqno = req.getParameter("seqno");
			if(seqno == null) {
				seqno = (String)req.getAttribute("seqno");
			}
			Board b = bs.searchBoard(seqno);
			req.setAttribute("board", b); //jsp 뷰에다 넘기려면 setA~해줘야한다.
			
			//게시물 수정
			String page = req.getParameter("page");
			if(page != null) {
				goView(req, resp, "/board/modify.jsp");
			}else {
				goView(req, resp, "/board/detail.jsp");
			}
			
		}else if(cmd.equals("boardRegForm.bo")) {
			//등록버튼 누르면 등록 뜨는것
			goView(req, resp, "/board/boardForm.jsp");
		}else if(cmd.equals("boardReg.bo")) { //boardForm 25줄
			req.setAttribute("seqno", bs.insertBoard(req, resp));
			goView(req, resp, "boardDetail.bo"); 
		}else if(cmd.equals("modify.bo")) {
			req.setAttribute("seqno", bs.update(req, resp));
			goView(req, resp, "boardDetail.bo"); 
		}
	}
	
	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(viewPage); 
		//보여줄 파일이름 적으면됨
		rd.forward(req, resp);
	}
	
}
