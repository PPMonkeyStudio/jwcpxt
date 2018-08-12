package com.pphgzs.domain.DTO;

/**
 * 提供数据接口当月当日总数以及满意数
 * 
 * @author JXX
 *
 */
public class MonthDayMountDTO {
	// 总数
	private int totalCount = 0;
	// 成功访问数
	private int totalSuccessCount = 0;
	// 满意数
	private int totalStatisCount = 0;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalSuccessCount() {
		return totalSuccessCount;
	}

	public void setTotalSuccessCount(int totalSuccessCount) {
		this.totalSuccessCount = totalSuccessCount;
	}

	public int getTotalStatisCount() {
		return totalStatisCount;
	}

	public void setTotalStatisCount(int totalStatisCount) {
		this.totalStatisCount = totalStatisCount;
	}

	@Override
	public String toString() {
		return "MonthDayMountDTO [totalCount=" + totalCount + ", totalSuccessCount=" + totalSuccessCount
				+ ", totalStatisCount=" + totalStatisCount + "]";
	}

	public MonthDayMountDTO(int totalCount, int totalSuccessCount, int totalStatisCount) {
		super();
		this.totalCount = totalCount;
		this.totalSuccessCount = totalSuccessCount;
		this.totalStatisCount = totalStatisCount;
	}

	public MonthDayMountDTO() {
		super();
	}

}
