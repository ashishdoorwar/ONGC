package com.ongc.liferay.reports.model;

public class FeedbackHrCategory {

	private int hrCatId;
	private String hrCategory;
	
	public int getHrCatId() {
		return hrCatId;
	}
	public void setHrCatId(int hrCatId) {
		this.hrCatId = hrCatId;
	}
	public String getHrCategory() {
		return hrCategory;
	}
	public void setHrCategory(String hrCategory) {
		this.hrCategory = hrCategory;
	}
	@Override
	public String toString() {
		return "FeedbackHrCategory [hrCatId=" + hrCatId + ", hrCategory=" + hrCategory + "]";
	}
	
}
