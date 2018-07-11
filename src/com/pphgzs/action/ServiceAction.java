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
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_unit_service;
import com.pphgzs.domain.DTO.ServiceConnectDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
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
	private jwcpxt_service_grab serviceGrab;
	/* 
	 * 
	 */
	private ServiceDefinitionVO serviceDefinitionVO;
	private ServiceInstanceVO serviceInstanceVO;
	private List<jwcpxt_service_definition> listServiceDefinition;
	private List<ServiceConnectDTO> listServiceConnectDTO;
	private jwcpxt_unit unit;
	private jwcpxt_unit_service unitServic;

	/**
	 * 修改一个抓取表
	 * 
	 * @throws IOException
	 */
	public void update_serviceGrab_byServiceGrabId() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.update_serviceGrab_byServiceGrabId(serviceGrab)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 删除业务抓取表
	 */
	public void delete_serviceGrab_byServiceGrabId() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.delete_serviceGrab_byServiceGrabId(serviceGrab)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 添加业务定义
	 */

	/**
	 * 业务列表
	 * 
	 * @throws IOException
	 */
	public void list_serviceDefinition() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		//
		listServiceDefinition = serviceService.list_serviceDefinition();
		http_response.getWriter().write(gson.toJson(listServiceDefinition));
	}

	/**
	 * 根据id获取关联表
	 * 
	 * @throws IOException
	 */
	public void get_untServic_byUnitServicId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		unitServic = serviceService.get_untServic_byUnitServicId(unitServic);
		http_response.getWriter().write(gson.toJson(unitServic));
	}

	/**
	 * 修改评测数量
	 * 
	 * @throws IOException
	 */
	public void update_unitServicCount_byunitServicId() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.update_unitServiceCount_byUnitServiceId(unitServic)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 获取所有关联某个单位的业务
	 */
	public void list_serviceDefinitionDTO_connectService() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		listServiceConnectDTO = serviceService.list_serviceDefinitionDTO_connectService(unit);
		http_response.getWriter().write(gson.toJson(listServiceConnectDTO));
	}

	/**
	 * 创建单位业务表
	 * 
	 * @throws IOException
	 *
	 */
	public void save_unitServic() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.save_unitService(unitServic)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 获得此单位未关联的业务
	 * 
	 * @throws IOException
	 * 
	 */
	public void list_serviceDefinition_notConnectService() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		listServiceDefinition = serviceService.list_serviceDefinition_notConnectService(unit);
		http_response.getWriter().write(gson.toJson(listServiceDefinition));
	}

	/**
	 * 添加业务定义
	 * 
	 * @update 2018-7-11
	 * @throws IOException
	 */
	public void save_serviceDefinition() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.save_serviceDefinition(serviceDefinition)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 创建业务抓取表
	 * 
	 * @throws IOException
	 */
	public void save_serviceGrab() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (serviceService.save_serviceGrab(serviceGrab)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
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

	public void get_serviceInstanceVO() throws IOException {

		serviceInstanceVO = serviceService.get_serviceInstanceVO(serviceInstanceVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceInstanceVO));
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
	 * 修改业务抓取的抓取字段
	 * 
	 * @throws IOException
	 */
	public void update_serviceGrab() throws IOException {
		if (serviceService.update_serviceGrab(serviceGrab)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 根据业务定义id获取抓取字段表DO
	 * 
	 * @throws IOException
	 */
	public void get_serviceGrabDO_byServiceDefinitionID() throws IOException {
		serviceGrab = serviceService
				.get_serviceGrabDO_byServiceDefinitionID(serviceGrab.getService_grab_service_definition());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceGrab));
	}

	/**
	 * 获得一个业务定义
	 * 
	 * @throws IOException
	 */
	public void get_serviceDefinition_byServiceDefinitionID() throws IOException {
		serviceDefinition = serviceService
				.get_serviceDefinitionDO_byServiceDefinitionID(serviceDefinition.getJwcpxt_service_definition_id());
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
				.list_clientDO_byServiceInstanceID(serviceInstance.getJwcpxt_service_instance_id());
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

	/**
	 * @throws IOException
	 * 
	 */
	public void distribution_judge() throws IOException {
		if (serviceService.distribution_judge(serviceInstance.getJwcpxt_service_instance_id(),
				serviceInstance.getService_instance_judge())) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 
	 */

	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public List<jwcpxt_service_definition> getListServiceDefinition() {
		return listServiceDefinition;
	}

	public List<ServiceConnectDTO> getListServiceConnectDTO() {
		return listServiceConnectDTO;
	}

	public void setListServiceConnectDTO(List<ServiceConnectDTO> listServiceConnectDTO) {
		this.listServiceConnectDTO = listServiceConnectDTO;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	public jwcpxt_unit_service getunitServic() {
		return unitServic;
	}

	public jwcpxt_unit_service getUnitServic() {
		return unitServic;
	}

	public void setUnitServic(jwcpxt_unit_service unitServic) {
		this.unitServic = unitServic;
	}

	public void setListServiceDefinition(List<jwcpxt_service_definition> listServiceDefinition) {
		this.listServiceDefinition = listServiceDefinition;
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

	public void setServiceInstanceVO(ServiceInstanceVO serviceInstanceVO) {
		this.serviceInstanceVO = serviceInstanceVO;
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

	public jwcpxt_service_grab getServiceGrab() {
		return serviceGrab;
	}

	public void setServiceGrab(jwcpxt_service_grab serviceGrab) {
		this.serviceGrab = serviceGrab;
	}

	/*
	 * 
	 */
}
