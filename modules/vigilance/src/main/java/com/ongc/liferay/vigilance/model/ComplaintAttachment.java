package com.ongc.liferay.vigilance.model;

import java.util.Arrays;

public class ComplaintAttachment {

	private int attachmentId;
	private int complaintId;
	private String fileName;
	private byte[] fileData;
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	@Override
	public String toString() {
		return "ComplaintAttachment [attachmentId=" + attachmentId + ", complaintId=" + complaintId + ", fileName="
				+ fileName + ", fileData=" + Arrays.toString(fileData) + "]";
	}
	
	
}
