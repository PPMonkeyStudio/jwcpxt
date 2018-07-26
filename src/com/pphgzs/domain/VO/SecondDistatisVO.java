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

	@Override
	public String toString() {
		return "SecondDistatisVO [listSecondDistatisDTO=" + listSecondDistatisDTO + ", currPage=" + currPage
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize + "]";
	}

	public SecondDistatisVO(List<SecondDistatisDTO> listSecondDistatisDTO, int currPage, int totalPage, int totalCount,
			int pageSize) {
		super();
		this.listSecondDistatisDTO = listSecondDistatisDTO;
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}

	public SecondDistatisVO() {
		super();
	}

}
