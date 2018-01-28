package gov.uk.dvla.dto;

public class FileAttributes {

	private String fileName;
	private String contentType;
	private String fileExtension;
	private Long fileSize;
	
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	@Override
	public String toString() {
		return "FileAttributes [fileName=" + fileName + ", contentType=" + contentType + ", fileExtension="
				+ fileExtension + ", fileSize=" + fileSize + "]";
	}
}
