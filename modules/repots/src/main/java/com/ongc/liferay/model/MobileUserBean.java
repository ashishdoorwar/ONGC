package com.ongc.liferay.model;

public class MobileUserBean
{
  private String userName;
  private String cpfNo;
  private String imeiNo;
  private String loginDate;
  
  public String getImeiNo()
  {
    return this.imeiNo;
  }
  
  public void setImeiNo(String imeiNo)
  {
    this.imeiNo = imeiNo;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getCpfNo()
  {
    return this.cpfNo;
  }
  
  public void setCpfNo(String cpfNo)
  {
    this.cpfNo = cpfNo;
  }
  
  public String getLoginDate()
  {
    return this.loginDate;
  }
  
  public void setLoginDate(String loginDate)
  {
    this.loginDate = loginDate;
  }
}