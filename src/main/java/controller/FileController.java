package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileService;
import service.FileServiceImpl;

@WebServlet("/file/*")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	FileService fileService = new FileServiceImpl();
	
    public FileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") +1);
		
		switch(cmd) {
			case "fileDown" :
				fileService.fileDown(request, response);
				
				break;
			
			default :
		}
	}

}
















