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

	// 筛选
	private String screenStartTime = "0000-00-00";
	private String screenEndTime = "9999-99-99";
	private String screenHandleState = "-1";
	private String screenSearch = "";
	private String screenAuditState = "-1";
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

	public String getScreenStartTime() {
		return screenStartTime;
	}

	public void setScreenStartTime(String screenStartTime) {
		this.screenStartTime = screenStartTime;
	}

	public String getScreenEndTime() {
		return screenEndTime;
	}

	public void setScreenEndTime(String screenEndTime) {
		this.screenEndTime = screenEndTime;
	}

	public String getScreenHandleState() {
		return screenHandleState;
	}

	public void setScreenHandleState(String screenHandleState) {
		this.screenHandleState = screenHandleState;
	}

	public String getScreenSearch() {
		return screenSearch;
	}

	public void setScreenSearch(String screenSearch) {
		this.screenSearch = screenSearch;
	}

	public String getScreenAuditState() {
		return screenAuditState;
	}

	public void setScreenAuditState(String screenAuditState) {
		this.screenAuditState = screenAuditState;
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
		return "FeedbackRectificationVO [listFeedbackRectification=" + listFeedbackRectification + ", screenStartTime="
				+ screenStartTime + ", screenEndTime=" + screenEndTime + ", screenHandleState=" + screenHandleState
				+ ", screenSearch=" + screenSearch + ", screenAuditState=" + screenAuditState + ", currPage=" + currPage
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

}
