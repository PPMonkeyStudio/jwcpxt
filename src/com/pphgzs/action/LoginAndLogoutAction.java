package com.pphgzs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.mapping.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.service.LoginAndLogoutService;

@SuppressWarnings("serial")
public class LoginAndLogoutAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private LoginAndLogoutService loginAndLogoutService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */

	private String account;
	private String password;
	private String loginType;
	/*
	 * 
	 */

	/**
	 * 登录并存入session
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		/*
		 * 
		 */
		if (loginType.equals("user")) {
			jwcpxt_user user = loginAndLogoutService.userLogin(account, password);
			if (user == null) {
				http_response.getWriter().write("-1");
			} else {
				ActionContext.getContext().getSession().remove("user");
				ActionContext.getContext().getSession().put("user", user);
				System.out.println(user.getUser_type());
				ActionContext.getContext().getSession().put("loginType", "user");
				http_response.getWriter().write("1");
			}
		} else {
			jwcpxt_unit unit = loginAndLogoutService.unitLogin(account, password);
			if (unit == null) {
				http_response.getWriter().write("-1");

			} else {
				ActionContext.getContext().getSession().remove("unit");
				ActionContext.getContext().getSession().put("unit", unit);
				ActionContext.getContext().getSession().put("loginType", "unit");
				http_response.getWriter().write("2");
			}
		}
		/*
		 * 
		 */
	}

	/**
	 * 
	 * 登出并跳转到登录页
	 * 
	 * @return
	 * @throws IOException
	 */
	public void logout() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("user") != null) {
			ActionContext.getContext().getSession().remove("user");
			ActionContext.getContext().getSession().remove("loginType");
			http_response.getWriter().write("1");
		} else if (ActionContext.getContext().getSession().get("unit") != null) {
			ActionContext.getContext().getSession().remove("unit");
			ActionContext.getContext().getSession().remove("loginType");
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("1");
		}
	}

	/*
	 * 获取当前session信息的currentUser
	 */
	public void getCurrentUser() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (http_request.getSession().getAttribute("user") != null
				&& http_request.getSession().getAttribute("user") != "") {
			http_response.getWriter().write(gson.toJson(http_request.getSession().getAttribute("user")));
		} else if (http_request.getSession().getAttribute("unit") != null
				&& http_request.getSession().getAttribute("unit") != "") {
			http_response.getWriter().write(gson.toJson(http_request.getSession().getAttribute("unit")));
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

	public LoginAndLogoutService getLoginAndLogoutService() {
		return loginAndLogoutService;
	}

	public void setLoginAndLogoutService(LoginAndLogoutService loginAndLogoutService) {
		this.loginAndLogoutService = loginAndLogoutService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/*
	 * 
	 */
}
