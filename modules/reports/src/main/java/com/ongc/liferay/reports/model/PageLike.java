package com.ongc.liferay.reports.model;


import java.sql.Timestamp;

public class PageLike
{
  private int idkey;
  private String cpfNo;
  private String pageId;
  private Timestamp postDate;
  private String contentId;
  private String sectionName;

  public int getIdkey()
  {
    return this.idkey;
  }

  public void setIdkey(int idkey) {
    this.idkey = idkey;
  }

  public String getCpfNo() {
    return this.cpfNo;
  }

  public void setCpfNo(String cpfNo) {
    this.cpfNo = cpfNo;
  }

  public String getPageId() {
    return this.pageId;
  }

  public void setPageId(String pageId) {
    this.pageId = pageId;
  }

  public Timestamp getPostDate() {
    return this.postDate;
  }

  public void setPostDate(Timestamp postDate) {
    this.postDate = postDate;
  }

public String getContentId() {
	return contentId;
}

public void setContentId(String contentId) {
	this.contentId = contentId;
}

public String getSectionName() {
	return sectionName;
}

public void setSectionName(String sectionName) {
	this.sectionName = sectionName;
}

@Override
public String toString() {
	return "PageLike [idkey=" + idkey + ", cpfNo=" + cpfNo + ", pageId=" + pageId + ", postDate=" + postDate
			+ ", contentId=" + contentId + ", sectionName=" + sectionName + "]";
}


}

