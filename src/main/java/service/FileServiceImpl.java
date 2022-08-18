package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class FileServiceImpl implements FileService {

	@Override
	public void fileUpload(FileItem item) {

	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		String filename = request.getParameter("filename");
		String saveFileName = request.getParameter("savefilename");
		String filePath = request.getParameter("filepath");
	
		File file = new File(filePath + saveFileName);
		
		try {
			InputStream in = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			
			response.reset(); // 이미 열려있는 출력스트림을 비우는 역할을 함.
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(filename, "UTF-8"));
			byte[] fileByte = new byte[(int)file.length()];

			int readByte =0;
			while( (readByte = in.read(fileByte)) > 0 ) {
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

}










