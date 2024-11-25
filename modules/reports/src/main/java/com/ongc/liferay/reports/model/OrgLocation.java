package com.ongc.liferay.reports.model;

public class OrgLocation {

	private String idKey;
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	@Override
	public String toString() {
		return "OrgLocation [idKey=" + idKey + ", cityName=" + cityName + "]";
	}
	
	
}
