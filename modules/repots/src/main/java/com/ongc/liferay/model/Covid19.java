package com.ongc.liferay.model;



public class Covid19 {
	private int covidId;
	private String cpfNo;
	private String concern;
	private String createdDate;
	private String status;

	public int getCovidId() {
		return covidId;
	}

	public void setCovidId(int covidId) {
		this.covidId = covidId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCpfNo() {
		return cpfNo;
	}

	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Covid19 [covidId=" + covidId + ", cpfNo=" + cpfNo + ", concern=" + concern + ", createdDate="
				+ createdDate + ", status=" + status + "]";
	}

	
}
