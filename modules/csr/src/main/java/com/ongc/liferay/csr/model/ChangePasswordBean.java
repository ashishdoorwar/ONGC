package com.ongc.liferay.csr.model;


public class ChangePasswordBean {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	/*static{
		log.info("ChangePasswordBean.......static");
	}
	public ChangePasswordBean(){
		log.info("ChangePasswordBean....cons...");
	}*/
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	} 
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
