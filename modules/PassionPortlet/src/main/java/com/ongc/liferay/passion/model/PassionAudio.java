package com.ongc.liferay.passion.model;
/**
 * 
 * @author DKSrivastava
 *
 */
public class PassionAudio {
	private String audioId;
	private String audioName;
	private String audioDescription;
	private String audioFileName;
	private String audioPSN_MPID;
	private String audioEndorsedCount;
	private String audioEndorsedBy;
	private String audioEndorsedByCpf;
	private String audioComments;
	private int commentId;
	//private String audioTag;
	
	public String getAudioId() {
		return audioId;
	}
	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public String getAudioDescription() {
		return audioDescription;
	}
	public void setAudioDescription(String audioDescription) {
		this.audioDescription = audioDescription;
	}
	public String getAudioFileName() {
		return audioFileName;
	}
	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}
	public String getAudioPSN_MPID() {
		return audioPSN_MPID;
	}
	public void setAudioPSN_MPID(String audioPSN_MPID) {
		this.audioPSN_MPID = audioPSN_MPID;
	}
	public String getAudioEndorsedCount() {
		return audioEndorsedCount;
	}
	public void setAudioEndorsedCount(String audioEndorsedCount) {
		this.audioEndorsedCount = audioEndorsedCount;
	}
	public String getAudioEndorsedBy() {
		return audioEndorsedBy;
	}
	public void setAudioEndorsedBy(String audioEndorsedBy) {
		this.audioEndorsedBy = audioEndorsedBy;
	}
	public String getAudioComments() {
		return audioComments;
	}
	public void setAudioComments(String audioComments) {
		this.audioComments = audioComments;
	}
	public String getAudioEndorsedByCpf() {
		return audioEndorsedByCpf;
	}
	public void setAudioEndorsedByCpf(String audioEndorsedByCpf) {
		this.audioEndorsedByCpf = audioEndorsedByCpf;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	


}
