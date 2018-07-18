package com.pphgzs.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_unit_service;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ClientInstanceDTO;
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
	private jwcpxt_service_client serviceClient;
	/* 
	 * 
	 */
	private ServiceDefinitionVO serviceDefinitionVO;
	private ServiceInstanceVO serviceInstanceVO;
	private List<jwcpxt_service_definition> listServiceDefinition;
	private List<ServiceConnectDTO> listServiceConnectDTO;
	private jwcpxt_unit unit;
	private jwcpxt_unit_service unitServic;
	private List<jwcpxt_service_grab> listServiceGrab;
	private ClientInstanceDTO clientInstanceDTO;

	/**
	 * 根据业务定义id获取业务定义信息
	 * 
	 * @throws IOException
	 */
	public void get_serviceDefinitionDo_byId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		serviceDefinition = serviceService.get_serviceDefinitionDo_byId(serviceDefinition);
		http_response.getWriter().write(gson.toJson(serviceDefinition));
	}

	/**
	 * 根据当事人id获取当事人信息
	 * 
	 * @throws IOException
	 */
	public void get_serviceClientDo_byId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		serviceClient = serviceService.get_serviceClientDo_byId(serviceClient);
		http_response.getWriter().write(gson.toJson(serviceClient));
	}

	/**
	 * 从session中拿到当前被分配的第一条测评人员的信息
	 * 
	 * @throws IOException
	 */
	public void get_notServiceClient_byServiceClientId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		jwcpxt_user user = new jwcpxt_user();
		user = (jwcpxt_user) ActionContext.getContext().getSession().get("user");
		clientInstanceDTO = serviceService.get_notServiceClient_byServiceClientId(user);
		http_response.getWriter().write(gson.toJson(clientInstanceDTO));
	}

	/**
	 * 根据业务抓取表id获取业务抓取表
	 * 
	 * @throws IOException
	 */
	public void get_serviceGrab() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		serviceGrab = serviceService.get_serviceGrab(serviceGrab);
		http_response.getWriter().write(gson.toJson(serviceGrab));
	}

	/**
	 * 获取所有的业务
	 * 
	 * @throws IOException
	 */
	public void list_serviceDefinition_all() throws IOException {
		List<jwcpxt_service_definition> serviceDefinitionList = serviceService.list_serviceDefinitionDO_all();
		jwcpxt_service_definition serviceDefinition = new jwcpxt_service_definition();
		//
		serviceDefinition.setJwcpxt_service_definition_id("revisit");
		serviceDefinition.setService_definition_describe("整改情况");
		serviceDefinitionList.add(0, serviceDefinition);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(serviceDefinitionList));
	}

	/**
	 * @throws IOException
	 *             根据业务定义Id获取所有业务抓取
	 */
	public void list_serviceGrab_byServiceDefinitionId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		//
		listServiceGrab = serviceService.list_serviceGrab_byServiceDefinitionId(serviceDefinition);
		http_response.getWriter().write(gson.toJson(listServiceGrab));
	}

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

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
	}

	public List<jwcpxt_service_grab> getListServiceGrab() {
		return listServiceGrab;
	}

	public void setListServiceGrab(List<jwcpxt_service_grab> listServiceGrab) {
		this.listServiceGrab = listServiceGrab;
	}

	public ClientInstanceDTO getClientInstanceDTO() {
		return clientInstanceDTO;
	}

	public void setClientInstanceDTO(ClientInstanceDTO clientInstanceDTO) {
		this.clientInstanceDTO = clientInstanceDTO;
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
