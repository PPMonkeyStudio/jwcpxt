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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--------------------------------------------------------------------------------->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-select.min.css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap-select.js"></script>
<!--------------------------------------------------------------------------------->
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/chartist-custom.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/main.css">
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/style.css">
<link rel="stylesheet" href="<%=basePath%>css/table.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/toastr.css" />
<script type="text/javascript" src="<%=basePath%>js/toastr.js"></script>
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/loginAndLogout/login.js"></script>
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<title>警务测评系统</title>
</head>
<body style="background-color: #f3f5f8;">

	<div
		style="margin: calc(( 100VH - 350px)/2) calc(50% - 550px) 0; width: 1100px; float: left; background-size: cover;">
		<div class="panel"
			style="width: 480px; padding: 20px; float: left; border-radius: 0px;">
			<div class="panel-heading">
				<!-- 			<div> -->
				<%-- 				<img src="<%=basePath%>img/logo.jpg" height="40px"> --%>
				<!-- 			</div> -->
				<h2 style="text-align: center;">萍乡市公安警务测评系统</h2>
			</div>
			<div class="panel-body" style="margin: 0 0 20px 0;">
				<div class="form-group" style="margin: 20px auto 30px;">
					<!-- 				<label>：</label> -->
					<input type="email" class="form-control" id="login_username"
						placeholder="账号">
				</div>
				<div class="form-group" style="margin: 30px auto 20px;">
					<input type="password" class="form-control" id="login_password"
						placeholder="密码">
				</div>
				<br>
				<button style="float: right; width: 195px" id="button_login"
					class="btn btn-success" onclick="login('user')">用户登录</button>

				<button style="float: right; width: 195px; margin-right: 18px;"
					class="btn btn-primary" onclick="login('unit')">单位登录</button>
			</div>
		</div>
		<div
			style="background-image: url(/jwcpxt/img/log2.png); background-size: cover; background-size: 100% 100%; width: 620px; height: 368px; float: right;"></div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/user/login.js"></script>
</body>
</html>