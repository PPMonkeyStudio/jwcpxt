package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_instance;

public class ServiceInstanceDTO {

	private jwcpxt_service_instance serviceInstance;

	private ServiceDefinitionDTO serviceDefinitionDTO;

	private List<jwcpxt_service_client> serviceClientList;

	@Override
	public String toString() {
		return "ServiceInstanceDTO 【\nserviceInstance=" + serviceInstance + ", \nserviceDefinitionDTO="
				+ serviceDefinitionDTO + ", \nserviceClientList=" + serviceClientList + "\n】";
	}

	public jwcpxt_service_instance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(jwcpxt_service_instance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	public ServiceDefinitionDTO getServiceDefinitionDTO() {
		return serviceDefinitionDTO;
	}

	public void setServiceDefinitionDTO(ServiceDefinitionDTO serviceDefinitionDTO) {
		this.serviceDefinitionDTO = serviceDefinitionDTO;
	}

	public List<jwcpxt_service_client> getServiceClientList() {
		return serviceClientList;
	}

	public void setServiceClientList(List<jwcpxt_service_client> serviceClientList) {
		this.serviceClientList = serviceClientList;
	}

}
