package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/set")
public class SetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//setAttribute방법 1 [ContentType]	
		response.setContentType("text/html;Charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setAttribute("request", "요청 객체에 바인딩");
	
	//setAttribute방법 2 [session]	
		HttpSession sess = request.getSession();
		sess.setMaxInactiveInterval(10);//세션의 시간을 정하는 것 (초단위임)
		sess.setAttribute("session", "세션의 아이디");
	
	//setAttribute방법 3 [ServletContext]		
		ServletContext ctx = getServletContext();//ServletContext 객체의 사용범위를 알아보자
		ctx.setAttribute("context", "서블릿컨텍스트에 바인딩됩니다.");
		
		out.print("바인딩 수행했습니다");
//		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
