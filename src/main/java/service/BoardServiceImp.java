package service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BoardDao;
import dto.Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardServiceImp implements BoardService {
	//구현클래스임 
	BoardDao boardDao =new BoardDao();
	private static final String CHARSET	 = "utf-8";
	
	@Override
	public List<Board> list() {
		return boardDao.boardList();
	}
	@Override
	public Board searchBoard(String seqno) {
		return boardDao.boardDetail(seqno);
	}
	@Override //파일업로드 할때 쓰는것!
	public void insertBoard(HttpServletRequest req, HttpServletResponse resp) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setDefaultCharset(CHARSET);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
