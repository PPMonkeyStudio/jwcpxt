package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX
 *
 */
public class ReturnVisitDTO {
	private String returnVisitType;
	private Long returnCount;

	public String getReturnVisitType() {
		return returnVisitType;
	}

	public void setReturnVisitType(String returnVisitType) {
		this.returnVisitType = returnVisitType;
	}

	public Long getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(Long returnCount) {
		this.returnCount = returnCount;
	}

	@Override
	public String toString() {
		return "ReturnVisitDTO [returnVisitType=" + returnVisitType + ", returnCount=" + returnCount + "]";
	}

	public ReturnVisitDTO(String returnVisitType, Long returnCount) {
		super();
		this.returnVisitType = returnVisitType;
		this.returnCount = returnCount;
	}

	public ReturnVisitDTO() {
		super();
	}

}
