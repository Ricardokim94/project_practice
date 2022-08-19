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

@WebServlet("/get")
public class GetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;Charset=utf-8");
		PrintWriter out = response.getWriter();
				
		HttpSession sess = request.getSession();
		ServletContext ctx = getServletContext();//ServletContext 객체의 사용범위를 알아보자
		
		String reqMSG = (String)request.getAttribute("request");
		String sessMSG = (String)sess.getAttribute("session");
		String ctxMSG = (String)ctx.getAttribute("context");
		
		out.print("request 메세지 : " + reqMSG + "<br>");
		out.print("session 메세지 : " + sessMSG + "<br>");
		out.print("context 메세지 : " + ctxMSG + "<br>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
