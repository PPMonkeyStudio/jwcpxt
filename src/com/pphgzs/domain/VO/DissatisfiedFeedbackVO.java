package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.RectificationFeedbackDTO;

/**
 * 
 * @author JXX
 *
 */
public class DissatisfiedFeedbackVO {
	// 反馈整改表
	private List<RectificationFeedbackDTO> listRectificationFeedback;
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;
	// 时间
	private String rectificationStartTime = "";
	private String rectificationStopTime = "";
	private String unit = "";

	public List<RectificationFeedbackDTO> getListRectificationFeedback() {
		return listRectificationFeedback;
	}

	public void setListRectificationFeedback(List<RectificationFeedbackDTO> listRectificationFeedback) {
		this.listRectificationFeedback = listRectificationFeedback;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getRectificationStartTime() {
		return rectificationStartTime;
	}

	public void setRectificationStartTime(String rectificationStartTime) {
		this.rectificationStartTime = rectificationStartTime;
	}

	public String getRectificationStopTime() {
		return rectificationStopTime;
	}

	public void setRectificationStopTime(String rectificationStopTime) {
		this.rectificationStopTime = rectificationStopTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "DissatisfiedFeedbackVO [listRectificationFeedback=" + listRectificationFeedback + ", currPage="
				+ currPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", rectificationStartTime=" + rectificationStartTime + ", rectificationStopTime="
				+ rectificationStopTime + ", unit=" + unit + "]";
	}

}
