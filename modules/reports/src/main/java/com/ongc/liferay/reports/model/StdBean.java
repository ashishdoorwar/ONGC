package com.ongc.liferay.reports.model;

public class StdBean {

	private int stdId;
	private String circleName;
	private String ldcsName;
	private String sdcaName;
	private String sdcaCode;
	public int getStdId() {
		return stdId;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getLdcsName() {
		return ldcsName;
	}
	public void setLdcsName(String ldcsName) {
		this.ldcsName = ldcsName;
	}
	public String getSdcaName() {
		return sdcaName;
	}
	public void setSdcaName(String sdcaName) {
		this.sdcaName = sdcaName;
	}
	public String getSdcaCode() {
		return sdcaCode;
	}
	public void setSdcaCode(String sdcaCode) {
		this.sdcaCode = sdcaCode;
	}
	@Override
	public String toString() {
		return "StdBean [stdId=" + stdId + ", circleName=" + circleName + ", ldcsName=" + ldcsName + ", sdcaName="
				+ sdcaName + ", sdcaCode=" + sdcaCode + "]";
	}
	
	
	
}
