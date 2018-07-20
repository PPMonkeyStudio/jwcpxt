package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

/**
 * DTO
 * 
 * @author JXX
 *
 */
public class ClientInfoDTO {
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_instance serviceInstance;
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_user user;
	private jwcpxt_unit unit;

	public ClientInfoDTO() {
		super();
	}

	public ClientInfoDTO(jwcpxt_service_client serviceClient, jwcpxt_service_instance serviceInstance,
			jwcpxt_service_definition serviceDefinition, jwcpxt_user user, jwcpxt_unit unit) {
		super();
		this.serviceClient = serviceClient;
		this.serviceInstance = serviceInstance;
		this.serviceDefinition = serviceDefinition;
		this.user = user;
		this.unit = unit;
	}

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
	}

	public jwcpxt_service_instance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(jwcpxt_service_instance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "ClientInfoDTO [serviceClient=" + serviceClient + ", serviceInstance=" + serviceInstance
				+ ", serviceDefinition=" + serviceDefinition + ", user=" + user + ", unit=" + unit + "]";
	}

}
