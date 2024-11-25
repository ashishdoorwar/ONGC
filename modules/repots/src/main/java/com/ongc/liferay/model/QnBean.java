package com.ongc.liferay.model;
import java.util.List;

public class QnBean {
	private long qid;
	
	private String qnDesc;
	private String qnNo;
	
	private String orgUnit;
	
	private List<QnOptionBean>qnOpList;
	
	public List<QnOptionBean> getQnOpList() {
		return qnOpList;
	}

	public void setQnOpList(List<QnOptionBean> qnOpList) {
		this.qnOpList = qnOpList;
	}

	public String getQnDesc() {
		return qnDesc;
	}

	public void setQnDesc(String qnDesc) {
		this.qnDesc = qnDesc;
	}

	

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public String getQnNo() {
		return qnNo;
	}

	public void setQnNo(String qnNo) {
		this.qnNo = qnNo;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	@Override
	public String toString() {
		return "QnBean [qnId=" + qid + ", qnDesc=" + qnDesc + ", qnNo=" + qnNo + ", orgUnit=" + orgUnit + ", qnOpList="
				+ qnOpList + "]";
	}

	
	
}
