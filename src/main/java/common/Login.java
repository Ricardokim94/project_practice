package common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Map<String, String> map = dao.loginProc(id, pw);
		
		switch (map.get("login")) {
		case "ok" :	//로그인 성공
			//세션설정!
			HttpSession sess = request.getSession();
			LoginImpl loginUser = new LoginImpl(id, map.get("name"));
			sess.setAttribute("loginUser", loginUser);
			request.setAttribute("msg", "loginOk");
			break; 
		default: //로그인실패
			request.setAttribute("msg", "loginFail");
		
		}
		//뷰페이지에 던져야 되니까
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

}













