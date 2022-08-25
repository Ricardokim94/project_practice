package service;



import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import common.LoginImpl;
import dao.BoardDao;
import dto.AttachFile;
import dto.Board;
import dto.Criteria;



public class BoardServiceImp implements BoardService {	
	BoardDao boardDao = new BoardDao();
	private static final String CHARSET = "utf-8";

	@Override
	public List<Board> list(Criteria cri) {
		return boardDao.boardList(cri);
	}
	
	@Override
	public Board searchBoard(String seqno) {
		return boardDao.boardDetail(seqno);		
	}
	
	@Override
	public String insertBoard(HttpServletRequest req, HttpServletResponse resp) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setDefaultCharset(CHARSET);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		Board board = new Board();
		AttachFile attachFile = null;
		FileService fileService = new FileServiceImpl(); 
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			System.out.println("폼에서 넘어온 개수 :" + items.size());
			for(FileItem item : items) {
				if(item.isFormField()) {
					board = getFormPararmeter(item, board);
				} else {
					attachFile = fileService.fileUpload(item);
				}
			}						
		} catch (FileUploadException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");
		
		board.setId(login.getId());			
		
		return boardDao.insert(board, attachFile);
	}

	public String update(HttpServletRequest req, HttpServletResponse resp) {		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setDefaultCharset(CHARSET);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		Board board = new Board();
		AttachFile attachFile = null;
		FileService fileService = new FileServiceImpl(); 
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			System.out.println("폼에서 넘어온 개수 :" + items.size());
			for(FileItem item : items) {
				if(item.isFormField()) {
					board = getFormPararmeter(item, board);
				} else {
					attachFile = fileService.fileUpload(item);
				}
			}						
		} catch (FileUploadException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");		
		board.setId(login.getId());
		
		boardDao.update(board, attachFile);
		
		return board.getSeqno();
		
	}
	
	Board getFormPararmeter(FileItem item, Board board) {		
		System.out.printf("필드이름 : %s, 필드값: %s\n", item.getFieldName(), item.getString());
		if(item.getFieldName().equals("title")) {
			board.setTitle(item.getString());
		}
		if(item.getFieldName().equals("open")) {
			board.setOpen(item.getString());
		}
		if(item.getFieldName().equals("content")) {
			board.setContent(item.getString());
		}
		if(item.getFieldName().equals("seqno")) {
			board.setSeqno(item.getString());
		}
		
		return board;
	}

	@Override
	public int getTotalRec() {
		
		return boardDao.getTotalRec();
	}
	

}









