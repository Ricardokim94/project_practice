package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public interface FileService {
	public void fileUpload(FileItem item);
	
	public void fileDown(HttpServletRequest request, HttpServletResponse response);
}
