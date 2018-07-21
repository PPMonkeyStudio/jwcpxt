package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX
 *
 */
public class StatisticsDissatisfiedOptionDTO {
	private String option;
	private int count;

	public StatisticsDissatisfiedOptionDTO() {
		super();
	}

	public StatisticsDissatisfiedOptionDTO(String option, int count) {
		super();
		this.option = option;
		this.count = count;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StatisticsDissatisfiedOptionDTO [option=" + option + ", count=" + count + "]";
	}

}
