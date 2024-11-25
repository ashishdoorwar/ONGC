package com.ongc.liferay.passion.model;

public class PassionVideo {
	private String videoId;
	private String videoName;
	private String videoDescription;
	private String videoFileName;
	private String videoPSN_MPID;
	private String videoEndorsedCount;
	private String videoEndorsedBy;
	private String videoEndorsedByCpf;
	private String videoComments;
	private int commentId;
	//private String videoTag;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}
	public String getVideoFileName() {
		return videoFileName;
	}
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	public String getVideoPSN_MPID() {
		return videoPSN_MPID;
	}
	public void setVideoPSN_MPID(String videoPSN_MPID) {
		this.videoPSN_MPID = videoPSN_MPID;
	}
	public String getVideoEndorsedCount() {
		return videoEndorsedCount;
	}
	public void setVideoEndorsedCount(String videoEndorsedCount) {
		this.videoEndorsedCount = videoEndorsedCount;
	}
	public String getVideoEndorsedBy() {
		return videoEndorsedBy;
	}
	public void setVideoEndorsedBy(String videoEndorsedBy) {
		this.videoEndorsedBy = videoEndorsedBy;
	}
	public String getVideoComments() {
		return videoComments;
	}
	public void setVideoComments(String videoComments) {
		this.videoComments = videoComments;
	}
	public String getVideoEndorsedByCpf() {
		return videoEndorsedByCpf;
	}
	public void setVideoEndorsedByCpf(String videoEndorsedByCpf) {
		this.videoEndorsedByCpf = videoEndorsedByCpf;
	}
	
	
	
	
}
