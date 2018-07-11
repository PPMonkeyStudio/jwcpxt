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
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.service.UnitService;

@SuppressWarnings("serial")
public class UnitAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	private UnitService unitService;
	/* 
	 * 
	 */
	private jwcpxt_unit unit;

	/*
	 * 
	 */
	public String unitList() {
		return "unitList";
	}

	/*
	 * 没写
	 */
	public void get_unitVO() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(unitService.get_unitVO()));
	}

	public void add_unit() throws IOException {

		if (unitService.save_unit(unit)) {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}

	}

	// public void delete_unit() throws IOException {
	// unitService.delete_unit(unit);
	// http_response.setContentType("text/html;charset=utf-8");
	// http_response.getWriter().write("1");
	//
	// }

	/**
	 * 修改单位名称、账号、电话号码
	 * 
	 * @throws IOException
	 */
	public void update_unit() throws IOException {
		unitService.update_unit(unit);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("1");

	}

	/*
	 * 修改密码
	 */
	public void update_unitPassword() throws IOException {
		unitService.update_unitPassword(unit);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("1");
	}

	/**
	 * 重置密码
	 * 
	 * @throws IOException
	 */
	public void reset_unitPassword() throws IOException {
		unitService.reset_unitPassword(unit);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("1");
	}

	/**
	 * 获取所有的单位列表
	 * 
	 * @throws IOException
	 */
	public void list_unit_all() throws IOException {
		List<jwcpxt_unit> unitList = unitService.list_unit_all();
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(unitList));

	}

	/**
	 * 通过单位id获得一个单位
	 * 
	 * @throws IOException
	 */
	public void get_unitDO_byID() throws IOException {
		jwcpxt_unit returnUnit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(returnUnit));
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

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	/*
	 * 
	 */
}
