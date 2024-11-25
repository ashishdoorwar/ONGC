package com.ongc.liferay.reports.model;

public class MscBean {

	private String network;
	private String circle;
	private String circleCategory;
	private String mobileOperator;
	private String mncCode;
	private String startSeries;
	private String endSeries;
	private  String codeDetails;
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getCircleCategory() {
		return circleCategory;
	}
	public void setCircleCategory(String circleCategory) {
		this.circleCategory = circleCategory;
	}
	public String getMobileOperator() {
		return mobileOperator;
	}
	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}
	public String getMncCode() {
		return mncCode;
	}
	public void setMncCode(String mncCode) {
		this.mncCode = mncCode;
	}
	public String getStartSeries() {
		return startSeries;
	}
	public void setStartSeries(String startSeries) {
		this.startSeries = startSeries;
	}
	public String getEndSeries() {
		return endSeries;
	}
	public void setEndSeries(String endSeries) {
		this.endSeries = endSeries;
	}
	public String getCodeDetails() {
		return codeDetails;
	}
	public void setCodeDetails(String codeDetails) {
		this.codeDetails = codeDetails;
	}
	@Override
	public String toString() {
		return "StdBean [network=" + network + ", circle=" + circle + ", circleCategory=" + circleCategory
				+ ", mobileOperator=" + mobileOperator + ", mncCode=" + mncCode + ", startSeries=" + startSeries
				+ ", endSeries=" + endSeries + ", codeDetails=" + codeDetails + "]";
	}
	
	
}
