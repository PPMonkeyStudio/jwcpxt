<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<ul id="changeNavbar" class="nav navbar-nav navbar-left">
				<li><a href="#">
						<p>首页</p>
				</a></li>
				<li>
			</ul>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<!-- <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="ti-panel"></i>
						<p>Stats</p>
				</a></li> -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="ti-bell"></i>
						<p class="notification">1</p>
						<p>通知</p> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">张斌天天写bug</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="ti-user"></i>
						<p>
							<s:if test="#session.loginType=='user'">
								<s:property value="#session.user.user_name" />
							</s:if>
							<s:else>
								<s:property value="#session.unit.unit_name" />
							</s:else>
						</p> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<s:if test="#session.loginType == 'user'">
							<li><a
								id='<s:property value="#session.user.jwcpxt_user_id" />'
								href="#" onclick="updatePasswordUser(this)">修改密码</a></li>
						</s:if>
						<s:if test="#session.loginType == 'unit'">
							<li><a
								id='<s:property value="#session.unit.jwcpxt_unit_id" />'
								href="#" onclick="updatePasswordUnit(this)">修改密码</a></li>
						</s:if>
						<li><a href="#" onclick="logout()">退出</a></li>
					</ul></li>
			</ul>

		</div>
	</div>
	</nav>
</body>
</html>