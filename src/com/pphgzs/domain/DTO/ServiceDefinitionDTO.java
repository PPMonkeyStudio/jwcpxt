package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit;

public class ServiceDefinitionDTO {
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_unit unit;

	@Override
	public String toString() {
		return "ServiceDefinitionDTO 【\nserviceDefinition=" + serviceDefinition + ", \nunit=" + unit + "\n】";
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

}
