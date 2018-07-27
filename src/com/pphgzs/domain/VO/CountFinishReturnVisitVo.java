package com.pphgzs.domain.VO;

public class CountFinishReturnVisitVo {
	private String appraisalId;
	private String beginTime;
	private String endTime;
	private String countType;
	private String type;

	public String getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CountFinishReturnVisitVo [appraisalId=" + appraisalId + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", countType=" + countType + ", type=" + type + ", getAppraisalId()=" + getAppraisalId()
				+ ", getBeginTime()=" + getBeginTime() + ", getEndTime()=" + getEndTime() + ", getCountType()="
				+ getCountType() + ", getType()=" + getType() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
