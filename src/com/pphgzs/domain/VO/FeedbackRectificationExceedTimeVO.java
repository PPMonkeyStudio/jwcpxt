package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.FeedbackRectificationDTO;

/**
 * 
 * @author JXX
 *
 */
public class FeedbackRectificationExceedTimeVO {
	// 搜索
	private String search = "";
	// 当前页
	private int currPage = 1;
	// 业务名称
	private String searchService = "";
	// 整改的创建时间开始时间
	private String searchTimeStart = "0000-00-00";
	// 整改的创建时间结束时间
	private String searchTimeEnd = "9999-99-99";
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;
	private List<FeedbackRectificationDTO> listFeedbackRectificationDTO;

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

	public List<FeedbackRectificationDTO> getListFeedbackRectificationDTO() {
		return listFeedbackRectificationDTO;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setListFeedbackRectificationDTO(List<FeedbackRectificationDTO> listFeedbackRectificationDTO) {
		this.listFeedbackRectificationDTO = listFeedbackRectificationDTO;
	}

	public String getSearchService() {
		return searchService;
	}

	public void setSearchService(String searchService) {
		this.searchService = searchService;
	}

	public String getSearchTimeStart() {
		return searchTimeStart;
	}

	public void setSearchTimeStart(String searchTimeStart) {
		this.searchTimeStart = searchTimeStart;
	}

	public String getSearchTimeEnd() {
		return searchTimeEnd;
	}

	public void setSearchTimeEnd(String searchTimeEnd) {
		this.searchTimeEnd = searchTimeEnd;
	}

	public FeedbackRectificationExceedTimeVO(String search, int currPage, String searchService, String searchTimeStart,
			String searchTimeEnd, int totalPage, int totalCount, int pageSize,
			List<FeedbackRectificationDTO> listFeedbackRectificationDTO) {
		super();
		this.search = search;
		this.currPage = currPage;
		this.searchService = searchService;
		this.searchTimeStart = searchTimeStart;
		this.searchTimeEnd = searchTimeEnd;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.listFeedbackRectificationDTO = listFeedbackRectificationDTO;
	}

	public FeedbackRectificationExceedTimeVO() {
		super();
	}

}
