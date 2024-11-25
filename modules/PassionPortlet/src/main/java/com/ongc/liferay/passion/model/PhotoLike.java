package com.ongc.liferay.passion.model;

import java.sql.Timestamp;

public class PhotoLike {
	private int idkey;
	private String cpfNo;
	private String photoId;
	private Timestamp postDate;
	
	public int getIdkey() {
		return idkey;
	}
	public void setIdkey(int idkey) {
		this.idkey = idkey;
	}
	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public Timestamp getPostDate() {
		return postDate;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	
}
