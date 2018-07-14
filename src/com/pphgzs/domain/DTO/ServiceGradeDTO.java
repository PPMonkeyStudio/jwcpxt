package com.pphgzs.domain.DTO;

/**
 * 
 * @author 孙毅
 * 
 *         业务分数DTO
 */
public class ServiceGradeDTO {
	private String service_id;
	private int grade;

	@Override
	public String toString() {
		return "ServiceGradeDTO [service_id=" + service_id + ", grade=" + grade + "]";
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
