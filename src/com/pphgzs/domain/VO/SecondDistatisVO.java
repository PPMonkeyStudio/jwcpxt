package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.SecondDistatisDTO;

/**
 * 
 * @author JXX
 *
 */
public class SecondDistatisVO {
	private List<SecondDistatisDTO> listSecondDistatisDTO;
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;
	// 搜素
	private String search = "";
	// 业务名称
	private String searchService = "";
	// 当事人表修改时间——也就是回访时间
	private String searchTimeStart = "0000-00-00";
	private String searchTimeEnd = "9999-99-99";

	public List<SecondDistatisDTO> getListSecondDistatisDTO() {
		return listSecondDistatisDTO;
	}

	public void setListSecondDistatisDTO(List<SecondDistatisDTO> listSecondDistatisDTO) {
		this.listSecondDistatisDTO = listSecondDistatisDTO;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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

	@Override
	public String toString() {
		return "SecondDistatisVO [listSecondDistatisDTO=" + listSecondDistatisDTO + ", currPage=" + currPage
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + ", search="
				+ search + ", searchService=" + searchService + ", searchTimeStart=" + searchTimeStart
				+ ", searchTimeEnd=" + searchTimeEnd + "]";
	}

	public SecondDistatisVO(List<SecondDistatisDTO> listSecondDistatisDTO, int currPage, int totalPage, int totalCount,
			int pageSize, String search, String searchService, String searchTimeStart, String searchTimeEnd) {
		super();
		this.listSecondDistatisDTO = listSecondDistatisDTO;
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.search = search;
		this.searchService = searchService;
		this.searchTimeStart = searchTimeStart;
		this.searchTimeEnd = searchTimeEnd;
	}

	public SecondDistatisVO() {
		super();
	}

}
