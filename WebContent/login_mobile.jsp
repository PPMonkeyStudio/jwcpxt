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
<meta name="viewport" content="width=device-width, initial-scale=1">
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

	<div style="width: 100%; background-size: cover;">
		<div class="panel" style="border-radius: 0px;" >
			<div class="panel-heading">
				<!-- 			<div> -->
				<%-- 				<img src="<%=basePath%>img/logo.jpg" height="40px"> --%>
				<!-- 			</div> -->
				<h2 style="text-align: center;"><!-- 萍乡市公安警务测评系统 -->萍乡市公安局<br/>服务群众测评系统</h2>
			</div>
			
			<div style="background-image: url(/jwcpxt/img/log2.png); background-size: cover; background-size: 100% 100%; width: 354px; height: 212px;"></div>
			
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
				<button style="float: left; width: 160px" id="button_login"
					class="btn btn-success" onclick="login('user')">用户登录</button>

				<button style="float: right; width: 160px;"
					class="btn btn-primary" onclick="login('unit')">单位登录</button>
			</div>
		</div>
		
	</div>
	<script type="text/javascript" src="<%=basePath%>js/user/login.js"></script>
</body>
</html>