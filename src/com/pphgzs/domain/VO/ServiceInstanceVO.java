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
	private String screenServiceInstanceDate = "";
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

}
