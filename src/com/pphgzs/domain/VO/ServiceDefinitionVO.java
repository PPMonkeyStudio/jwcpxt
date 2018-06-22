package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.ServiceDefinitionDTO;

public class ServiceDefinitionVO {

	private List<ServiceDefinitionDTO> serviceDefinitionDTOList;

	// 筛选单位

	private String screen_unit = "";

	// 当前页
	private int pageIndex = 1;

	// 总记录数
	private int totalRecords = 0;

	// 每页显示记录数
	private int pageSize = 10;

	// 总页数
	private int totalPages = 1;

	// 是否有上一页
	private boolean HavePrePage = false;

	// 是否有下一页
	private boolean HaveNextPage = false;

	@Override
	public String toString() {
		return "ServiceDefinitionVO 【\nserviceDefinitionDTOList=" + serviceDefinitionDTOList + ", \nscreen_unit="
				+ screen_unit + ", \npageIndex=" + pageIndex + ", \ntotalRecords=" + totalRecords + ", \npageSize="
				+ pageSize + ", \ntotalPages=" + totalPages + ", \nHavePrePage=" + HavePrePage + ", \nHaveNextPage="
				+ HaveNextPage + "\n】";
	}

	public String getScreen_unit() {
		return screen_unit;
	}

	public void setScreen_unit(String screen_unit) {
		this.screen_unit = screen_unit;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHavePrePage() {
		return HavePrePage;
	}

	public void setHavePrePage(boolean havePrePage) {
		HavePrePage = havePrePage;
	}

	public boolean isHaveNextPage() {
		return HaveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		HaveNextPage = haveNextPage;
	}

	public List<ServiceDefinitionDTO> getServiceDefinitionDTOList() {
		return serviceDefinitionDTOList;
	}

	public void setServiceDefinitionDTOList(List<ServiceDefinitionDTO> serviceDefinitionDTOList) {
		this.serviceDefinitionDTOList = serviceDefinitionDTOList;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

}
