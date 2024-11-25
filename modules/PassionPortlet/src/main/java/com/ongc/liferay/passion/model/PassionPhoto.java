package com.ongc.liferay.passion.model;
/**
 * 
 * @author DKSrivastava
 *
 */
public class PassionPhoto {

private String photoId;
private String photoName;
private String photoDescription;
private String fileName;
private String psnMPID;
private String endorsedCount;
private String endorsedBy;
private String endorsedByCpf;
private String photoComments;
//private String photoTag;
private String CommentsOn;
private int commentId;

public String getPhotoId() {
	return photoId;
}
public void setPhotoId(String photoId) {
	this.photoId = photoId;
}
public String getPhotoName() {
	return photoName;
}
public void setPhotoName(String photoName) {
	this.photoName = photoName;
}
public String getPhotoDescription() {
	return photoDescription;
}
public void setPhotoDescription(String photoDescription) {
	this.photoDescription = photoDescription;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getPsnMPID() {
	return psnMPID;
}
public void setPsnMPID(String psnMPID) {
	this.psnMPID = psnMPID;
}
public String getEndorsedCount() {
	return endorsedCount;
}
public void setEndorsedCount(String endorsedCount) {
	this.endorsedCount = endorsedCount;
}
public String getEndorsedBy() {
	return endorsedBy;
}
public void setEndorsedBy(String endorsedBy) {
	this.endorsedBy = endorsedBy;
}
public String getPhotoComments() {
	return photoComments;
}
public void setPhotoComments(String photoComments) {
	this.photoComments = photoComments;
}
public String getCommentsOn() {
	return CommentsOn;
}
public void setCommentsOn(String commentsOn) {
	CommentsOn = commentsOn;
}
public String getEndorsedByCpf() {
	return endorsedByCpf;
}
public void setEndorsedByCpf(String endorsedByCpf) {
	this.endorsedByCpf = endorsedByCpf;
}
public int getCommentId() {
	return commentId;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}


	
}
