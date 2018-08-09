package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DTO.FeedbackRectificationDTO;

/**
 * 审核所需的整改
 * 
 * @author JXX
 *
 */
public class CheckFeedbackRectificationVO {

	private List<FeedbackRectificationDTO> listFeedbackRectificationDTO;
	private String screenCheckState = "-1";
	private String screenSearch = "";
	private String screenStartTime = "0000-00-00";
	private String screenEndTime = "9999-99-99";
	private String searchHandleState = "%";
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;
	// 业务筛选
	private String searchService;

	public List<FeedbackRectificationDTO> getListFeedbackRectificationDTO() {
		return listFeedbackRectificationDTO;
	}

	public void setListFeedbackRectificationDTO(List<FeedbackRectificationDTO> listFeedbackRectificationDTO) {
		this.listFeedbackRectificationDTO = listFeedbackRectificationDTO;
	}

	public String getScreenCheckState() {
		return screenCheckState;
	}

	public void setScreenCheckState(String screenCheckState) {
		this.screenCheckState = screenCheckState;
	}

	public String getScreenSearch() {
		return screenSearch;
	}

	public void setScreenSearch(String screenSearch) {
		this.screenSearch = screenSearch;
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

	public String getSearchHandleState() {

		return searchHandleState;
	}

	public void setSearchHandleState(String searchHandleState) {
		if ("".equals(searchHandleState) || searchHandleState == null) {
			this.searchHandleState = "%";
		} else
			this.searchHandleState = searchHandleState;
	}

	public String getSearchService() {
		return searchService;
	}

	public void setSearchService(String searchService) {
		this.searchService = searchService;
	}

	@Override
	public String toString() {
		return "CheckFeedbackRectificationVO [listFeedbackRectificationDTO=" + listFeedbackRectificationDTO
				+ ", screenCheckState=" + screenCheckState + ", screenSearch=" + screenSearch + ", screenStartTime="
				+ screenStartTime + ", screenEndTime=" + screenEndTime + ", currPage=" + currPage + ", totalPage="
				+ totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

}
