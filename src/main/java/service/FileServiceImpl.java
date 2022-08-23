package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import dao.FileDao;
import dto.AttachFile;
import dto.Thumbnail;
import net.coobird.thumbnailator.Thumbnails;

public class FileServiceImpl implements FileService {

	FileDao fileDao = new FileDao();
	
	@Override
	public AttachFile fileUpload(FileItem item) {				
		//첨부파일 : 바이너리파일
		AttachFile attachFile = null;		
		long fileSize = item.getSize();
		
		System.out.println("업로드 파일사이즈 : " + fileSize);
		if(fileSize > 0) {
			String fileUploadPath = "d:/KCM/upload/";
			String fileName = item.getName();
			System.out.println("업로드 파일이름 :" + fileName);
			
			//방법1
			int idx = fileName.lastIndexOf(".");												
			String split_fileName = fileName.substring(0,idx);
			String split_extension = fileName.substring(idx+1);
			
			//방법2
			split_fileName = FilenameUtils.getBaseName(fileName);
			split_extension = FilenameUtils.getExtension(fileName);
			
			//중복된 파일을 업로드 하지 않기 위해 UID값 생성
			UUID uid = UUID.randomUUID();
			String saveFileName = split_fileName + "_" + uid + "." + split_extension;
			System.out.println("저장할 파일이름 :" + saveFileName);
			
			//업로드 파일 저장
			File file = new File(fileUploadPath+saveFileName);
			try {
				item.write(file);
			} catch (Exception e) {				
				e.printStackTrace();
			}						
			
			attachFile = new AttachFile();
			attachFile.setFileName(fileName);
			attachFile.setSaveFileName(saveFileName);
			attachFile.setFilePath(fileUploadPath);
			attachFile.setFileSize(String.valueOf(fileSize));
			attachFile.setFiletype(item.getContentType());
			
			//이미지 파일타입 확인
			String fileType = item.getContentType();
			String type = fileType.substring(0, fileType.indexOf("/"));
			System.out.println("업로드 파일 타입 :" + type );						

			if(type.equals("image")) {																		
				attachFile.setThumbnail(setThumbnail(file, saveFileName));
			}
		}
		
		return attachFile;
	}

	
	//썸네일 파일 저장
	@Override
	public Thumbnail setThumbnail(File file, String saveFileName) {
		String thumFileName = "thumb_200x200_" + saveFileName;
		String thumFilePath = "d:/KCM/upload/thumbnail/";
		File thumFile = new File(thumFilePath + thumFileName);
		try {
			Thumbnails.of(file).size(200,200).toFile(thumFile);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		Thumbnail thumNail = new Thumbnail();
		thumNail.setFileName(thumFileName);
		thumNail.setFilePath(thumFilePath);						
		//쎔네일 파일사이즈구하기
		thumNail.setFileSize(String.valueOf(thumFile.length()));	
		
		return thumNail;
	}
	
	
	
	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response)  {
		
		/*
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {			
			e1.printStackTrace();
		}
		*/
		
		String filename = request.getParameter("filename");
		String saveFileName = request.getParameter("savefilename");
		String filepath = request.getParameter("filepath");
		
		File file = new File(filepath + saveFileName);
		
		try {
			InputStream in = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			
			response.reset(); // 이미 열려있는 출력스트림을 비움
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(filename, "UTF-8"));
			byte[] fileByte = new byte[(int)file.length()];
			
			int readByte = 0;
			while( (readByte = in.read(fileByte)) > 0) {
				os.write(fileByte, 0, readByte);
			}
			
			in.close();
			os.flush();
			os.close();
		
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

	@Override
	public int delete(String no, String savefilename, String filepath, String thumb_filename) {
		//attachfile 레코드 삭제
		int rs=0;
		rs = fileDao.deleteByNo(no);
		
		//첨부파일삭제
		File file = new File(filepath+savefilename);
		if(file.exists()) {
			file.delete();	
			rs=1;
		}
		
		//썸네일 삭제
		if(thumb_filename != null && rs == 1) {
			File thumb_file = new File(filepath + "thumbnail/" + thumb_filename);
			if(thumb_file.exists()) {
				thumb_file.delete();
			}
		}
		
		return rs;
	}

}








