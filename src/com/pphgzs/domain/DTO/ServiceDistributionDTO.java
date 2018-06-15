package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_service_distribution;
import com.pphgzs.domain.DO.jwcpxt_user;

public class ServiceDistributionDTO {
	private jwcpxt_user user;
	private jwcpxt_service_distribution serviceDistribution;
	private ServiceInstanceDTO serviceInstanceDTO;

	@Override
	public String toString() {
		return "ServiceDistributionDTO 【\nuser=" + user + ", \nserviceDistribution=" + serviceDistribution
				+ ", \nserviceInstanceDTO=" + serviceInstanceDTO + "\n】";
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}

	public jwcpxt_service_distribution getServiceDistribution() {
		return serviceDistribution;
	}

	public void setServiceDistribution(jwcpxt_service_distribution serviceDistribution) {
		this.serviceDistribution = serviceDistribution;
	}

	public ServiceInstanceDTO getServiceInstanceDTO() {
		return serviceInstanceDTO;
	}

	public void setServiceInstanceDTO(ServiceInstanceDTO serviceInstanceDTO) {
		this.serviceInstanceDTO = serviceInstanceDTO;
	}

}
