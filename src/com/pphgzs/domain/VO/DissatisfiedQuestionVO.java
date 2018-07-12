package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;

/**
 * 不满意反馈表以及问题表VO
 * 
 * @author JXX
 *
 */
public class DissatisfiedQuestionVO {
	private List<DissatisfiedQuestionDTO> listDissatisfiedQuestionDTO;
	// 时间筛选
	private String screenStartTime = "0000-00-00";
	private String screenEndTime = "9999-99-99";
	// 状态筛选
	private String screenState = "-1";
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;

	public List<DissatisfiedQuestionDTO> getListDissatisfiedQuestionDTO() {
		return listDissatisfiedQuestionDTO;
	}

	public void setListDissatisfiedQuestionDTO(List<DissatisfiedQuestionDTO> listDissatisfiedQuestionDTO) {
		this.listDissatisfiedQuestionDTO = listDissatisfiedQuestionDTO;
	}

	public String getScreenState() {
		return screenState;
	}

	public void setScreenState(String screenState) {
		this.screenState = screenState;
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

	@Override
	public String toString() {
		return "DissatisfiedQuestionVO [listDissatisfiedQuestionDTO=" + listDissatisfiedQuestionDTO
				+ ", screenStartTime=" + screenStartTime + ", screenEndTime=" + screenEndTime + ", screenState="
				+ screenState + ", currPage=" + currPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + "]";
	}

}
