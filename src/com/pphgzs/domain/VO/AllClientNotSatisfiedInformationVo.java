package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ClientNotSatisfiedQusetionAndOptionDTO;

public class AllClientNotSatisfiedInformationVo {
	private jwcpxt_service_client client;
	private jwcpxt_service_instance instance;
	private jwcpxt_unit unit;
	private jwcpxt_user user;
	private jwcpxt_service_definition definition;
	private List<ClientNotSatisfiedQusetionAndOptionDTO> QusetionAndOptionDTO;

	public jwcpxt_service_client getClient() {
		return client;
	}

	public void setClient(jwcpxt_service_client client) {
		this.client = client;
	}

	public jwcpxt_service_instance getInstance() {
		return instance;
	}

	public void setInstance(jwcpxt_service_instance instance) {
		this.instance = instance;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}

	public jwcpxt_service_definition getDefinition() {
		return definition;
	}

	public void setDefinition(jwcpxt_service_definition definition) {
		this.definition = definition;
	}

	public List<ClientNotSatisfiedQusetionAndOptionDTO> getQusetionAndOptionDTO() {
		return QusetionAndOptionDTO;
	}

	public void setQusetionAndOptionDTO(List<ClientNotSatisfiedQusetionAndOptionDTO> qusetionAndOptionDTO) {
		QusetionAndOptionDTO = qusetionAndOptionDTO;
	}

	@Override
	public String toString() {
		return "AllClientNotSatisfiedInformationVo [client=" + client + ", instance=" + instance + ", unit=" + unit
				+ ", user=" + user + ", definition=" + definition + ", QusetionAndOptionDTO=" + QusetionAndOptionDTO
				+ ", getClient()=" + getClient() + ", getInstance()=" + getInstance() + ", getUnit()=" + getUnit()
				+ ", getUser()=" + getUser() + ", getDefinition()=" + getDefinition() + ", getQusetionAndOptionDTO()="
				+ getQusetionAndOptionDTO() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
