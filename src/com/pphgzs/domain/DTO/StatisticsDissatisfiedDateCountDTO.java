package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_service_definition;

public class StatisticsDissatisfiedDateCountDTO {
	private jwcpxt_service_definition serviceDefinition;

	// 存日期对应的数量 
	private int dayCount;

	@Override
	public String toString() {
		return "StatisticsDissatisfiedDateCountDTO 【\nserviceDefinition=" + serviceDefinition + ", \ndayCount="
				+ dayCount + "\n】";
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

}
