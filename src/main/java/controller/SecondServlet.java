package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/second")
public class SecondServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf8");
		resp.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI(); //uri 정보 가져올 수 있다.
		String path = req.getContextPath(); // 루트경로 가져올 수 있다.
		resp.getWriter().append("uri:" + uri + " path : " + path);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init메소드 호출");
	}
}
