package com.ongc.liferay.passion.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author DKSrivastava
 *
 */

@XmlRootElement(name = "passion")
public class SubPassion {
	private String passionId;
	private String subPassionId;
	private String passionName;
	private String subPassion;
	private String createdBy;
	private Date createdOn;
	public String getPassionId() {
		return passionId;
	}
	
	public void setPassionId(String passionId) {
		this.passionId = passionId;
	}
	public String getSubPassionId() {
		return subPassionId;
	}
	@XmlAttribute
	public void setSubPassionId(String subPassionId) {
		this.subPassionId = subPassionId;
	}
	public String getPassionName() {
		return passionName;
	}
	public void setPassionName(String passionName) {
		this.passionName = passionName;
	}
	public String getSubPassion() {
		return subPassion;
	}
	@XmlElement
	public void setSubPassion(String subPassion) {
		this.subPassion = subPassion;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
