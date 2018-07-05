package com.pphgzs.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceDistributionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;
import com.pphgzs.service.ServiceService;

@SuppressWarnings("serial")
public class ServiceAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private ServiceService serviceService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_service_instance serviceInstance;

	/* 
	 * 
	 */
	private ServiceDefinitionVO serviceDefinitionVO;
	private ServiceInstanceVO serviceInstanceVO;
	private ServiceDistributionVO serviceDistributionVO;

	/*
	 * 
	 */

	/**
	 * 获取业务定义列表页面的VO类
	 * 
	 * @throws IOException
	 */
	public void get_serviceDefinitionVO() throws IOException {

		serviceDefinitionVO = serviceService.get_serviceDefinitionVO(serviceDefinitionVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceDefinitionVO));
	}

	/**
	 * 添加业务定义
	 * 
	 * @throws IOException
	 */
	public void save_serviceDefinition() throws IOException {
		if (serviceService.save_serviceDefinition(serviceDefinition)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 根据ID修改业务定义的描述
	 * 
	 * @throws IOException
	 */
	public void update_serviceDefinition() throws IOException {
		if (serviceService.update_serviceDefinition(serviceDefinition)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 获得一个业务定义
	 * 
	 * @throws IOException
	 */
	public void get_serviceDefinition_byServiceDefinitionID() throws IOException {
		serviceDefinition = serviceService
				.get_serviceDefinition_byServiceDefinitionID(serviceDefinition.getJwcpxt_service_definition_id());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceDefinition));
	}

	/**
	 * 通过业务实例ID获取相关当事人列表
	 * 
	 * @throws IOException
	 */
	public void list_client_byServiceInstanceID() throws IOException {
		List<jwcpxt_service_client> serviceClientList = serviceService
				.list_client_byServiceInstanceID(serviceInstance.getJwcpxt_service_instance_id());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceClientList));

	}

	/**
	 * 通过业务定义id获得相关业务实例DTO列表
	 * 
	 * @throws IOException
	 */
	public void list_serviceInstanceDTO_byServiceDefinitionID() throws IOException {
		List<ServiceInstanceDTO> serviceInstanceDTOList = serviceService
				.list_serviceInstanceDTO_byServiceDefinitionID(serviceDefinition.getJwcpxt_service_definition_id());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceInstanceDTOList));
	}

	/*
	 * 
	 */
	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public void setServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO) {
		this.serviceDefinitionVO = serviceDefinitionVO;
	}

	public ServiceDefinitionVO getServiceDefinitionVO() {
		return serviceDefinitionVO;
	}

	public ServiceInstanceVO getServiceInstanceVO() {
		return serviceInstanceVO;
	}

	public ServiceDistributionVO getServiceDistributionVO() {
		return serviceDistributionVO;
	}

	public void setServiceInstanceVO(ServiceInstanceVO serviceInstanceVO) {
		this.serviceInstanceVO = serviceInstanceVO;
	}

	public void setServiceDistributionVO(ServiceDistributionVO serviceDistributionVO) {
		this.serviceDistributionVO = serviceDistributionVO;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_service_instance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(jwcpxt_service_instance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	/*
	 * 
	 */
}
