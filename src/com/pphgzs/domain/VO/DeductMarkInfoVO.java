package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.DeductMarkInfoDTO;

/**
 * 
 * @author JXX
 *
 */
public class DeductMarkInfoVO {
	// 数据
	private List<DeductMarkInfoDTO> listDeductMarkInfoDTO;
	// 对回访时间开始的筛选
	private String screenTimeStart = "0000-00-00 00:00:00";
	// 对回访时间结束的筛选
	private String screenTimeEnd = "9999-99-99 23:59:59";
	// 对单位的筛选
	private String screenUnit = "";
	// 对业务的筛选
	private String screenDefinitionId = "";
	// 对测评员的筛选
	private String screenJudge = "";
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalCount = 0;
	// 每页记录数
	private int pageSize = 10;

	public List<DeductMarkInfoDTO> getListDeductMarkInfoDTO() {
		return listDeductMarkInfoDTO;
	}

	public void setListDeductMarkInfoDTO(List<DeductMarkInfoDTO> listDeductMarkInfoDTO) {
		this.listDeductMarkInfoDTO = listDeductMarkInfoDTO;
	}

	public String getScreenTimeStart() {
		return screenTimeStart;
	}

	public void setScreenTimeStart(String screenTimeStart) {
		this.screenTimeStart = screenTimeStart;
	}

	public String getScreenTimeEnd() {
		return screenTimeEnd;
	}

	public void setScreenTimeEnd(String screenTimeEnd) {
		this.screenTimeEnd = screenTimeEnd;
	}

	public String getScreenUnit() {
		return screenUnit;
	}

	public void setScreenUnit(String screenUnit) {
		this.screenUnit = screenUnit;
	}

	public String getScreenDefinitionId() {
		return screenDefinitionId;
	}

	public void setScreenDefinitionId(String screenDefinitionId) {
		this.screenDefinitionId = screenDefinitionId;
	}

	public String getScreenJudge() {
		return screenJudge;
	}

	public void setScreenJudge(String screenJudge) {
		this.screenJudge = screenJudge;
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
		return "DeductMarkInfoVO [listDeductMarkInfoDTO=" + listDeductMarkInfoDTO + ", screenTimeStart="
				+ screenTimeStart + ", screenTimeEnd=" + screenTimeEnd + ", screenUnit=" + screenUnit
				+ ", screenDefinitionId=" + screenDefinitionId + ", screenJudge=" + screenJudge + ", currPage="
				+ currPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

	public DeductMarkInfoVO(List<DeductMarkInfoDTO> listDeductMarkInfoDTO, String screenTimeStart, String screenTimeEnd,
			String screenUnit, String screenDefinitionId, String screenJudge, int currPage, int totalPage,
			int totalCount, int pageSize) {
		super();
		this.listDeductMarkInfoDTO = listDeductMarkInfoDTO;
		this.screenTimeStart = screenTimeStart;
		this.screenTimeEnd = screenTimeEnd;
		this.screenUnit = screenUnit;
		this.screenDefinitionId = screenDefinitionId;
		this.screenJudge = screenJudge;
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}

	public DeductMarkInfoVO() {
		super();
	}

}
