package com.pphgzs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.StatisticsVO;
import com.pphgzs.service.StatisticsService;

/**
 * 
 * @author 孙毅
 *
 */
public class StatisticsAction implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private StatisticsService statisticsService;
	private List<ServiceGradeDTO> serviceGradeDTOList;
	private String[] unitIds;
	private String searchTimeStart;
	private String searchTimeEnd;

	public void getGradeByCondition() {
		StatisticsVO statisticsVO = statisticsService.getGradeByCondition(unitIds, searchTimeStart, searchTimeEnd,
				serviceGradeDTOList);
	}

	public String[] getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(String[] unitIds) {
		this.unitIds = unitIds;
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

	public List<ServiceGradeDTO> getServiceGradeDTOList() {
		return serviceGradeDTOList;
	}

	public void setServiceGradeDTOList(List<ServiceGradeDTO> serviceGradeDTOList) {
		this.serviceGradeDTOList = serviceGradeDTOList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

}
