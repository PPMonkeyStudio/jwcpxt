package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit_service;

/**
 * 这是一个业务的数据
 * 
 * @author ZB
 *
 */
public class StatisticsDissatisfiedDayDataDTO {

	private jwcpxt_service_definition serviceDefinition;

	private jwcpxt_unit_service unitService;

	// 存日期对应的数量
	private List<Integer> dayNumList;

	@Override
	public String toString() {
		return "StatisticsDissatisfiedDayDataDTO 【\nserviceDefinition=" + serviceDefinition + ", \nunitService="
				+ unitService + ", \ndayNumList=" + dayNumList + "\n】";
	}

	public List<Integer> getDayNumList() {
		return dayNumList;
	}

	public void setDayNumList(List<Integer> dayNumList) {
		this.dayNumList = dayNumList;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_unit_service getUnitService() {
		return unitService;
	}

	public void setUnitService(jwcpxt_unit_service unitService) {
		this.unitService = unitService;
	}

}
