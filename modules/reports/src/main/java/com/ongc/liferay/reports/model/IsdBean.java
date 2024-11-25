package com.ongc.liferay.reports.model;

public class IsdBean {

	private String country;
	private String isdCode;
	private String iddCode;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIsdCode() {
		return isdCode;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public String getIddCode() {
		return iddCode;
	}
	public void setIddCode(String iddCode) {
		this.iddCode = iddCode;
	}
	@Override
	public String toString() {
		return "IsdBean [country=" + country + ", isdCode=" + isdCode + ", iddCode=" + iddCode + "]";
	}
	
}
