package com.pphgzs.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.UserDTO;
import com.pphgzs.domain.VO.UserVO;
import com.pphgzs.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private UserService userService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;

	/* 
	 * 
	 */
	private jwcpxt_user user;
	private UserVO userVO;

	/*
	 * 
	 */
	public String userList() {
		return "userList";
	}

	/*
	 * 
	 */
	public void get_userVO() throws IOException {
		if (userVO == null) {
			userVO = new UserVO();
		}
		userVO = userService.get_userVO(userVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(userVO));
	}

	/**
	 * 获得所有用户的DTO列表
	 * 
	 * @throws IOException
	 */
	public void list_userDTO_all() throws IOException {
		List<UserDTO> userDTOList = userService.list_userDTO_all();
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(userDTOList));
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void get_userDTO_byUserID() throws IOException {
		UserDTO userDTO = userService.get_userDTO_byUserID(user.getJwcpxt_user_id());
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(userDTO));
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void list_user_all() throws IOException {

		List<jwcpxt_user> userList = new ArrayList<jwcpxt_user>();

		userList = userService.list_user_all();
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(userList));

	}

	public void save_user() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (userService.save_user(user)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	public void delete_user() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (userService.delete_user(user)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	public void update_user() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (userService.update_user(user)) {
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	/*
	 * 
	 */
}
