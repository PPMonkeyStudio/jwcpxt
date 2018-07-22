package com.pphgzs.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class loginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 得到拦截到的action的名称,看是否是login,当是login的时候,不用进行下面的检测了,直接执行下一个拦截器
		String actionName = invocation.getProxy().getActionName();
		if ("skipSystemIndex".equals(actionName) || "skipSidebar".equals(actionName)
				|| "skipNavbarIndex".equals(actionName) || "skipFooter".equals(actionName)) {
			return invocation.invoke();
		}
		// 如果不是login.则判断是否已登录,及检测session中key为user的值是否存在,如果不存在,跳回到登录页面
		String loginType = (String) invocation.getInvocationContext().getSession().get("loginType");
		if (loginType == null) {
			return "login";
		}
		// 进行到这里.说明用户已登录,则跳转到下一个拦截器
		return invocation.invoke();
	}
}
