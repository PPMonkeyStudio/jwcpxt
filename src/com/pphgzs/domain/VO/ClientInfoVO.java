package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.ClientInfoDTO;

/**
 * 
 * @author JXX
 *
 */
public class ClientInfoVO {
	private List<ClientInfoDTO> listClientInfoDTO;
	// 回访时间
	private String startHTime = "0000-00-00";
	private String endHTime = "9999-99-99";
	// 开始时间
	private String startTime = "0000-00-00";
	// 结束时间
	private String endTime = "9999-99-99";
	// 业务筛选
	private String screenService = "";
	// 回访情况筛选
	private String screenVisit = "";
	// 测评员筛选
	private String screenUser = "";
	// 搜索当事人姓名、电话号码、单位
	private String search = "";
	// 问题id
	private String screenQuestionId = "";
	//
	private String screenOptionId = "";
	// 当事人状态筛选
	private String screenClientState = "";
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;

	public List<ClientInfoDTO> getListClientInfoDTO() {
		return listClientInfoDTO;
	}

	public void setListClientInfoDTO(List<ClientInfoDTO> listClientInfoDTO) {
		this.listClientInfoDTO = listClientInfoDTO;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getScreenQuestionId() {
		return screenQuestionId;
	}

	public void setScreenQuestionId(String screenQuestionId) {
		this.screenQuestionId = screenQuestionId;
	}

	public String getScreenOptionId() {
		return screenOptionId;
	}

	public void setScreenOptionId(String screenOptionId) {
		this.screenOptionId = screenOptionId;
	}

	public String getStartHTime() {
		return startHTime;
	}

	public void setStartHTime(String startHTime) {
		this.startHTime = startHTime;
	}

	public String getEndHTime() {
		return endHTime;
	}

	public void setEndHTime(String endHTime) {
		this.endHTime = endHTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScreenService() {
		return screenService;
	}

	public void setScreenService(String screenService) {
		this.screenService = screenService;
	}

	public String getScreenVisit() {
		return screenVisit;
	}

	public void setScreenVisit(String screenVisit) {
		this.screenVisit = screenVisit;
	}

	public String getScreenUser() {
		return screenUser;
	}

	public void setScreenUser(String screenUser) {
		this.screenUser = screenUser;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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

	public String getScreenClientState() {
		return screenClientState;
	}

	public void setScreenClientState(String screenClientState) {
		this.screenClientState = screenClientState;
	}

	@Override
	public String toString() {
		return "ClientInfoVO [listClientInfoDTO=" + listClientInfoDTO + ", startHTime=" + startHTime + ", endHTime="
				+ endHTime + ", startTime=" + startTime + ", endTime=" + endTime + ", screenService=" + screenService
				+ ", screenVisit=" + screenVisit + ", screenUser=" + screenUser + ", search=" + search
				+ ", screenQuestionId=" + screenQuestionId + ", screenOptionId=" + screenOptionId
				+ ", screenClientState=" + screenClientState + ", currPage=" + currPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

	public ClientInfoVO(List<ClientInfoDTO> listClientInfoDTO, String startHTime, String endHTime, String startTime,
			String endTime, String screenService, String screenVisit, String screenUser, String search,
			String screenQuestionId, String screenOptionId, String screenClientState, int currPage, int totalPage,
			int totalCount, int pageSize) {
		super();
		this.listClientInfoDTO = listClientInfoDTO;
		this.startHTime = startHTime;
		this.endHTime = endHTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.screenService = screenService;
		this.screenVisit = screenVisit;
		this.screenUser = screenUser;
		this.search = search;
		this.screenQuestionId = screenQuestionId;
		this.screenOptionId = screenOptionId;
		this.screenClientState = screenClientState;
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}

	public ClientInfoVO() {
		super();
	}

}
