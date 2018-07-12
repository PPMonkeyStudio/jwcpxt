package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;

/**
 * 整改反馈VO
 * 
 * @author JXX
 *
 */
public class FeedbackRectificationVO {
	private List<jwcpxt_feedback_rectification> listFeedbackRectification;
	private String screenState = "";
	private String screenNameSearch = "";
	private String screenUnitSearch = "";
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;

	public List<jwcpxt_feedback_rectification> getListFeedbackRectification() {
		return listFeedbackRectification;
	}

	public void setListFeedbackRectification(List<jwcpxt_feedback_rectification> listFeedbackRectification) {
		this.listFeedbackRectification = listFeedbackRectification;
	}

	public String getScreenState() {
		return screenState;
	}

	public void setScreenState(String screenState) {
		this.screenState = screenState;
	}

	public String getScreenNameSearch() {
		return screenNameSearch;
	}

	public void setScreenNameSearch(String screenNameSearch) {
		this.screenNameSearch = screenNameSearch;
	}

	public String getScreenUnitSearch() {
		return screenUnitSearch;
	}

	public void setScreenUnitSearch(String screenUnitSearch) {
		this.screenUnitSearch = screenUnitSearch;
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

	@Override
	public String toString() {
		return "FeedbackRectificationVO [listFeedbackRectification=" + listFeedbackRectification + ", screenState="
				+ screenState + ", screenNameSearch=" + screenNameSearch + ", screenUnitSearch=" + screenUnitSearch
				+ ", currPage=" + currPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize="
				+ pageSize + "]";
	}

}
