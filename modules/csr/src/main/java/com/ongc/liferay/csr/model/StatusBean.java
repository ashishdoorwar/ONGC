package com.ongc.liferay.csr.model;

/**
 * @author Ranjeet
 */
public class StatusBean {

	private int id;
	private String status;
	private String createdOn;
	private String createdBy;
	private String active;
	private String statusCode;




	public StatusBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}


	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "StatusBean [id=" + id + ", status=" + status + ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ ", active=" + active + ", statusCode=" + statusCode + "]";
	}
	
	
}
