package com.ongc.liferay.model;
import java.util.Comparator;

public class QnOptionBean implements Comparator<QnOptionBean>{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QnOptionBean other = (QnOptionBean) obj;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		return true;
	}

	private Long qnOptionId;
	private String qnOption;
	private QnBean qnId;
	private String op;
	

	public Long getQnOptionId() {
		return qnOptionId;
	}

	public void setQnOptionId(Long qnOptionId) {
		this.qnOptionId = qnOptionId;
	}

	public String getQnOption() {
		return qnOption;
	}

	public void setQnOption(String qnOption) {
		this.qnOption = qnOption;
	}

	public QnBean getQnId() {
		return qnId;
	}

	public void setQnId(QnBean qnId) {
		this.qnId = qnId;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	@Override
	public int compare(QnOptionBean arg0, QnOptionBean arg1) {
		return arg0.getOp().compareTo(arg1.getOp());
	}

	@Override
	public String toString() {
		return "QnOptionBean [qnOptionId=" + qnOptionId + ", qnOption=" + qnOption + ", qnId=" + qnId + ", op=" + op
				+ "]";
	}
	
	
	
	
	

}
