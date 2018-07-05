package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_user;

public class ServiceInstanceDTO {

	// 业务实例
	private jwcpxt_service_instance serviceInstance;
	// 所属业务定义
	private ServiceDefinitionDTO serviceDefinitionDTO;
	// 当事人列表
	private List<jwcpxt_service_client> serviceClientList;
	// 测评员
	private jwcpxt_user judge;

	public ServiceInstanceDTO() {
	}

	public ServiceInstanceDTO(jwcpxt_service_instance serviceInstance, ServiceDefinitionDTO serviceDefinitionDTO,
			List<jwcpxt_service_client> serviceClientList, jwcpxt_user judge) {
		this.serviceInstance = serviceInstance;
		this.serviceDefinitionDTO = serviceDefinitionDTO;
		this.serviceClientList = serviceClientList;
		this.judge = judge;
	}

	@Override
	public String toString() {
		return "ServiceInstanceDTO 【\nserviceInstance=" + serviceInstance + ", \nserviceDefinitionDTO="
				+ serviceDefinitionDTO + ", \nserviceClientList=" + serviceClientList + ", \njudge=" + judge + "\n】";
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

	public jwcpxt_user getJudge() {
		return judge;
	}

	public void setJudge(jwcpxt_user judge) {
		this.judge = judge;
	}

}
