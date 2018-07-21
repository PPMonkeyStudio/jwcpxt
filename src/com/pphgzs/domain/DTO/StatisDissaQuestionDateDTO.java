package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX
 *
 */
public class StatisDissaQuestionDateDTO {
	private String dateScale;
	private int count;

	public String getDateScale() {
		return dateScale;
	}

	public void setDateScale(String dateScale) {
		this.dateScale = dateScale;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StatisDissaQuestionDateDTO [dateScale=" + dateScale + ", count=" + count + "]";
	}

	public StatisDissaQuestionDateDTO(String dateScale, int count) {
		super();
		this.dateScale = dateScale;
		this.count = count;
	}

	public StatisDissaQuestionDateDTO() {
		super();
	}

}
