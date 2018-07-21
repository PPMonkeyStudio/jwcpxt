package com.pphgzs.domain.DTO;
/**
 * 业务数量
 * @author JXX
 *
 */

import com.pphgzs.domain.DO.jwcpxt_service_definition;

public class StatisDIssaServiceDTO {
	private jwcpxt_service_definition serviceDefinition;
	private int count = 0;

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StatisDIssaServiceDTO [serviceDefinition=" + serviceDefinition + ", count=" + count + "]";
	}

	public StatisDIssaServiceDTO(jwcpxt_service_definition serviceDefinition, int count) {
		super();
		this.serviceDefinition = serviceDefinition;
		this.count = count;
	}

	public StatisDIssaServiceDTO() {
		super();
	}

}
