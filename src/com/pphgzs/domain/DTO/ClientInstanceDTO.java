package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;

/**
 * 
 * @author JXX
 *
 */
public class ClientInstanceDTO {
	private jwcpxt_service_instance serviceInstance;
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_definition serviceDefinition;

	public jwcpxt_service_instance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(jwcpxt_service_instance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	@Override
	public String toString() {
		return "ClientInstanceDTO [serviceInstance=" + serviceInstance + ", serviceClient=" + serviceClient
				+ ", serviceDefinition=" + serviceDefinition + "]";
	}

}
