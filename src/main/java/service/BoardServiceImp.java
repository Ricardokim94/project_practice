package service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BoardDao;
import dto.AttachFile;
import dto.Board;
import dto.Thumbnail;
import net.coobird.thumbnailator.Thumbnails;

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
		
		Board board = new Board();
		AttachFile attachFile = null; //try문 밖에서 선언해야 안사라진다.
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			System.out.println("폼에서 넘어온 개수 : " + items.size());
			for(FileItem item: items) {
				if(item.isFormField()) {
					//<input> 테그값
					System.out.printf("필드이름 : %s, 필드값:%s\n", item.getFieldName(), item.getString());
					if(item.getFieldName() .equals("title")) {
						board.setTitle(item.getString());
					}
					if(item.getFieldName().equals("open")) {
						board.setOpen(item.getString());
					}
					if(item.getFieldName().equals("content")) {
						board.setContent(item.getString());
					}
					 board.setId((String)req.getSession().getAttribute("sess_id"));
				}else {
				
					//첨부파일 : 바이너리파일
						long filesize = item.getSize();
						System.out.println("업로드한 파일 사이즈 : " + filesize);
						if(filesize > 0) { //0보다 크다는건 파일이 업로드 된거다
							String fileUploadPath = "d:/KCM/upload/";
							String fileName = item.getName();
							System.out.println("업로드 파일 이름 " + fileName);
	
							int idx = fileName.lastIndexOf(".");
							
							String split_fileName = fileName.substring(0,idx);
							String split_extension = fileName.substring(idx+1);
						
					//중복된 파일을 업로드 하지 않기 위해 UID값 생성
						UUID uid = UUID.randomUUID();
						String saveFileName = split_fileName + "_" + uid + "." + split_extension;
						System.out.println("저장할 파일 이름" + fileName);
					//업로드 파일 저장
						File file = new File(fileUploadPath + saveFileName); //디렉토리 위치에 파일이름으로 등록하겠다는 것임
						item.write(file);
					//썸네일 파일 저장
						String thumbFileName = "thumb" + "_" + saveFileName;
						String thumbFilePath = "d:/KCM/upload/thumbnail/";
						File thumbFile = new File(thumbFilePath + thumbFileName);
						Thumbnails.of(file).size(200, 200).toFile(thumbFile);
						
						Thumbnail thumbnail = new Thumbnail();
						thumbnail.setFileName(thumbFileName); // 파일을 다 담아야된다.
						thumbnail.setFilePath(thumbFilePath); //경로 해야됨
					//파일 사이즈 구하기
						thumbnail.setFileSize(String.valueOf(thumbFile.length())); //파일 사이즈 담아야됨
					
						attachFile = new AttachFile(thumbnail);
						//저장함(42열에 선언한 것에)
						attachFile.setFileName(fileName);
						attachFile.setSaveFileName(saveFileName); 
						attachFile.setFilePath(fileUploadPath); 
						attachFile.setFileSize(String.valueOf(filesize)); 
						attachFile.setFiletype(item.getContentType());
					}
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boardDao.insert(board, attachFile);
		
	}
}
















