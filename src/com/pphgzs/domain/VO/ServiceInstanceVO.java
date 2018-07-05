package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.ServiceInstanceDTO;

public class ServiceInstanceVO {

	private List<ServiceInstanceDTO> serviceInstanceDTOList;
	/*
	 * 
	 */
	// 筛选测评人员
	private String screenServiceInstanceJudge = "";
	// 筛选业务办理时间
	private String screenServiceInstanceStartDate = "";
	private String screenServiceInstanceStopDate = "";
	// 筛选业务定义
	private String screenServiceDefinition = "";
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
		return "ServiceInstanceVO 【\nserviceInstanceDTOList=" + serviceInstanceDTOList
				+ ", \nscreenServiceInstanceJudge=" + screenServiceInstanceJudge + ", \nscreenServiceInstanceStartDate="
				+ screenServiceInstanceStartDate + ", \nscreenServiceInstanceStopDate=" + screenServiceInstanceStopDate
				+ ", \nscreenServiceDefinition=" + screenServiceDefinition + ", \ncurrPage=" + currPage
				+ ", \ntotalPage=" + totalPage + ", \ntotalCount=" + totalCount + ", \npageSize=" + pageSize + "\n】";
	}

	public String getScreenServiceDefinition() {
		return screenServiceDefinition;
	}

	public void setScreenServiceDefinition(String screenServiceDefinition) {
		this.screenServiceDefinition = screenServiceDefinition;
	}

	public List<ServiceInstanceDTO> getServiceInstanceDTOList() {
		return serviceInstanceDTOList;
	}

	public void setServiceInstanceDTOList(List<ServiceInstanceDTO> serviceInstanceDTOList) {
		this.serviceInstanceDTOList = serviceInstanceDTOList;
	}

	public String getScreenServiceInstanceJudge() {
		return screenServiceInstanceJudge;
	}

	public void setScreenServiceInstanceJudge(String screenServiceInstanceJudge) {
		this.screenServiceInstanceJudge = screenServiceInstanceJudge;
	}

	public String getScreenServiceInstanceStartDate() {
		return screenServiceInstanceStartDate;
	}

	public void setScreenServiceInstanceStartDate(String screenServiceInstanceStartDate) {
		this.screenServiceInstanceStartDate = screenServiceInstanceStartDate;
	}

	public String getScreenServiceInstanceStopDate() {
		return screenServiceInstanceStopDate;
	}

	public void setScreenServiceInstanceStopDate(String screenServiceInstanceStopDate) {
		this.screenServiceInstanceStopDate = screenServiceInstanceStopDate;
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
