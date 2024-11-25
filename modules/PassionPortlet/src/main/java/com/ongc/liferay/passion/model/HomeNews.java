package com.ongc.liferay.passion.model;

import java.sql.Blob;
import java.sql.Date;

public class HomeNews {
	private String newsId;
	private Date date;
	private String newsTitle;
	private String newsDesc;
	private String newsCreatedOn;
	private String published;
	private String fileCaption;
	private Blob fileData;
	
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getNewsId() {
		return newsId;
	}
	public String getFileCaption() {
		return fileCaption;
	}
	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}
	public Blob getFileData() {
		return fileData;
	}
	public void setFileData(Blob fileData) {
		this.fileData = fileData;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsDesc() {
		return newsDesc;
	}
	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}
	public String getNewsCreatedOn() {
		return newsCreatedOn;
	}
	public void setNewsCreatedOn(String newsCreatedOn) {
		this.newsCreatedOn = newsCreatedOn;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
