package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_distribution;
import com.pphgzs.domain.DO.jwcpxt_service_instance;

public interface ServiceDao {

	public List<jwcpxt_service_definition> list_serviceDefinition_all();

	public int get_serviceDefinitionTotalRecords();

	public int get_serviceInstanceTotalRecords();

	public int get_serviceDistributionTotalRecords();

	public List<jwcpxt_service_instance> list_serviceInstance_all();

	public jwcpxt_service_definition get_serviceDefinition_byID(String serviceDefinitionID);

	public jwcpxt_service_instance get_serviceInstance_byID(String serviceInstanceID);

	public jwcpxt_service_distribution get_serviceDistribution_byID(String serviceDistributionID);

	public List<jwcpxt_service_client> list_serviceClient_byInstance(String instanceID);

	public List<jwcpxt_service_distribution> list_serviceDistribution_all();

	public boolean add_serviceDefinition(jwcpxt_service_definition serviceDefinition);

}
