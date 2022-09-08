package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.MemberService;
import service.MemberServiceImp;

@WebServlet(urlPatterns = {"*.do"})

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceImp mr = new MemberServiceImp();  
    
	
	public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	//get으로 오든 post로 오든 doAction으로 처리 하겠다는 거임
	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=utf8");
		//req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI(); //uri 정보 가져올 수 있다.
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		
		//login.do로 요청하면 Memberservice에 login()호출
		
		if(cmd.equals("login.do")) {
			
			String id =req.getParameter("id");
			String pw =req.getParameter("pw");

			Map<String, String> status = mr.login(id, pw);
			
			switch (status.get("login")) {
				case "ok" :	//로그인 성공
					//세션설정!
					HttpSession sess = req.getSession();
					sess.setAttribute("sess_id", id);
					sess.setAttribute("sess_name", status.get("name"));
					
					req.setAttribute("msg", "loginOk");
					break; 
				default: //로그인실패
					req.setAttribute("msg", "loginFail"); //setAttribute : 변수설정해주는것을 참조해줄수있음
				}
					goView(req, resp, "/"); 
		} 
		
		else if(cmd.equals("logout.do")) { //로그아웃
			req.getSession().invalidate(); //세션파괴
			resp.sendRedirect("/");
		
		} else if(cmd.equals("memRegForm.do")) {
			goView(req, resp, "member/memRegForm.jsp");
		
		}else if(cmd.equals("memberReg.do")) {
			System.out.println("회원등록요청");
			mr.insert(req);
			req.setAttribute("msg", "memberOk"); //메세지에 memberOk뜨게 하고
			goView(req, resp, "/");				//서버에서 "/" 로 보냄
		}else if(cmd.equals("idDoubleCheck.do")) {
			String id = req.getParameter("id"); //아이디 더블체크하는것
			int rs = mr.idDoubleCheck(id);
			PrintWriter out = resp.getWriter();
			out.print(rs);
		}else if(cmd.equals("list.do")) {
			List<Member> member = mr.list();
		}
		
		
	}

	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(viewPage); 
		//보여줄 파일이름 적으면됨
		rd.forward(req, resp);
	}
	
}
	
	






















