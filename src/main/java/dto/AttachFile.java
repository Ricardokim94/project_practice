package dto;

public class AttachFile {
	private String fileName;
	private String saveFileName;
	private String filePath;
	private String fileSize;
	private String filetype;

	private Thumbnail thumbnail;
	
	
	
	
	public AttachFile() {
		super();
	}


	//AttacheFile을 생성하려면 thumbnail 을 무조건 넣어라 (의존관계)
	public AttachFile(Thumbnail thumbnail) {
		super();
		this.thumbnail = thumbnail;
	}

	
	
	public String getFiletype() {
		return filetype;
	}
	
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
