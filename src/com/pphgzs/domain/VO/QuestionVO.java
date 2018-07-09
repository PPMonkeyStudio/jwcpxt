package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;

public class QuestionVO {
	private List<jwcpxt_question> questionList;

	private ServiceDefinitionDTO serviceDefinitionDTO;

	// 筛选问题类型
	private String screenType = "";

	// 搜索
	private String screenSearch = "";

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
		return "QuestionVO [questionList=" + questionList + ", serviceDefinitionDTO=" + serviceDefinitionDTO
				+ ", screenType=" + screenType + ", screenSearch=" + screenSearch + ", currPage=" + currPage
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

	public int getCurrPage() {
		return currPage;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getScreenSearch() {
		return screenSearch;
	}

	public void setScreenSearch(String screenSearch) {
		this.screenSearch = screenSearch;
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

	public ServiceDefinitionDTO getServiceDefinitionDTO() {
		return serviceDefinitionDTO;
	}

	public void setServiceDefinitionDTO(ServiceDefinitionDTO serviceDefinitionDTO) {
		this.serviceDefinitionDTO = serviceDefinitionDTO;
	}

	public List<jwcpxt_question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<jwcpxt_question> questionList) {
		this.questionList = questionList;
	}

}
