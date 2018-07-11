package com.pphgzs.domain.DTO;
/**
 * 
 * @author JXX
 *
 */

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit_service;

public class ServiceConnectDTO {
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_unit_service unitSer;

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_unit_service getUnitSer() {
		return unitSer;
	}

	public void setUnitSer(jwcpxt_unit_service unitSer) {
		this.unitSer = unitSer;
	}

	public ServiceConnectDTO(jwcpxt_service_definition serviceDefinition, jwcpxt_unit_service unitSer) {
		super();
		this.serviceDefinition = serviceDefinition;
		this.unitSer = unitSer;
	}

	@Override
	public String toString() {
		return "ServiceConnectDTO [serviceDefinition=" + serviceDefinition + ", unitSer=" + unitSer + "]";
	}

	public ServiceConnectDTO() {
		super();
	}

}
