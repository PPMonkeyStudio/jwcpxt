package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.UserDTO;

public class UserVO {

	private List<UserDTO> UserDTOList;
	/*
	 * 
	 */
	// 搜索
	private String screenSearch = "";
	// 单位
	private String screenUnit = "";
	/*
	 * 
	 */
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;

	@Override
	public String toString() {
		return "UserVO 【\nUserDTOList=" + UserDTOList + ", \nscreenSearch=" + screenSearch + ", \nscreenUnit="
				+ screenUnit + ", \ncurrPage=" + currPage + ", \ntotalPage=" + totalPage + ", \ntotalCount="
				+ totalCount + ", \npageSize=" + pageSize + "\n】";
	}

	public List<UserDTO> getUserDTOList() {
		return UserDTOList;
	}

	public void setUserDTOList(List<UserDTO> userDTOList) {
		UserDTOList = userDTOList;
	}

	public String getScreenSearch() {
		return screenSearch;
	}

	public void setScreenSearch(String screenSearch) {
		this.screenSearch = screenSearch;
	}

	public String getScreenUnit() {
		return screenUnit;
	}

	public void setScreenUnit(String screenUnit) {
		this.screenUnit = screenUnit;
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

}
