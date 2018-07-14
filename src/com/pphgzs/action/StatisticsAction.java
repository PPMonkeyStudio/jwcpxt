package com.pphgzs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;
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
		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
		for (int i = 0; i < statisticsVO.getUnitHaveServiceGradeDTOList().size(); i++) {
			for (int j = i + 1; i < statisticsVO.getUnitHaveServiceGradeDTOList().size(); i++) {
				if (statisticsVO.getUnitHaveServiceGradeDTOList().get(i).getTotalGrade() > statisticsVO
						.getUnitHaveServiceGradeDTOList().get(j).getTotalGrade()) {
					unitHaveServiceGradeDTO = statisticsVO.getUnitHaveServiceGradeDTOList().get(i);
					statisticsVO.getUnitHaveServiceGradeDTOList().set(i,
							statisticsVO.getUnitHaveServiceGradeDTOList().get(j));
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j,
							statisticsVO.getUnitHaveServiceGradeDTOList().get(i));
				}
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(statisticsVO);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void downloadExcel() {

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
