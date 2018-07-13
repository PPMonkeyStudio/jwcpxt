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
<!-- 项目公用css -->
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/chartist-custom.css">
<link rel="stylesheet" href="<%=basePath%>css/jquery-confirm.css" />
<link rel="stylesheet" href="<%=basePath%>css/toastr.css" />
<link rel="stylesheet" href="<%=basePath%>css/demo.css" />
<link rel="stylesheet" href="<%=basePath%>css/paper-dashboard.css" />
<link rel="stylesheet" href="<%=basePath%>css/themify-icons.css" />
<link rel="stylesheet" href="<%=basePath%>css/navbar/font-awesome.css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-select.min.css">
<link rel="stylesheet" href="<%=basePath%>css/jquery.datetimepicker.css">
<!-- 项目公用js -->
<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/bootstrap-select.js"></script>
<script src="<%=basePath%>js/toastr.js"></script>
<script src="<%=basePath%>js/jquery-confirm.js"></script>
<script src="<%=basePath%>js/jquery.bootstrap.wizard.js"></script>
<script src="<%=basePath%>js/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>js/bootstrap-checkbox-radio.js"></script>
<script src="<%=basePath%>js/jquery.datetimepicker.full.js"></script>
<script src="<%=basePath%>js/bootstrap-notify.js"></script>
<script src="<%=basePath%>js/chartist.min.js"></script>
<script src="<%=basePath%>js/demo.js"></script>
<script src="<%=basePath%>js/paper-dashboard.js"></script>
<script src="<%=basePath%>js/vue.js"></script>
<!---页面公用------------------------------------------------------------------------------>

<title>Insert title here</title>
</head>

<body>
	<div class="sidebar" data-background-color="white"
		data-active-color="danger">
		<div class="sidebar-wrapper">
			<div class="logo">
				<a href="#" class="simple-text"> 警务测评系统 </a>
			</div>

			<ul class="nav">
				<li id="sideIndex"><a href="<%=basePath%>Skip/skipSystemIndex">
						<i class="ti-world"></i>
						<p>首页</p>
				</a></li>
				<s:if test="#session.loginType=='user'">
					<s:if test="#session.user.user_type==1">
						<li id="sidePolice"><a
							href="<%=basePath%>Skip/skipReturnedPartyInformation"> <i
								class="ti-user"></i>
								<p>测评警务</p>
						</a></li>
					</s:if>
				</s:if>
				<s:if test="#session.loginType=='unit'">
					<li id="sideAbarbeitung"><a
						href="<%=basePath%>Skip/skipAbarbeitungIndex"> <i
							class="ti-hummer"></i>
							<p>整改审核</p>
					</a></li>
				</s:if>
				<s:if test="#session.loginType=='user'">
					<s:if test="#session.user.user_type==2">
						<li id="sideStatis"><a href="#"> <i class="ti-stats-up"></i>
								<p>统计数据</p>
						</a></li>
					</s:if>
				</s:if>
				<s:if test="#session.loginType=='unit'">
					<s:if test="#session.unit.unit_grade==1">
						<li id="sideManager"><a
							href="<%=basePath%>Skip/skipManagerIndex"> <i
								class="ti-panel"></i>
								<p>管理数据</p>
						</a></li>
					</s:if>
				</s:if>
			</ul>
		</div>
	</div>
</body>

<style>
th {
	vertical-align: middle !important;
}

td {
	line-height: 33px !important;
	vertical-align: middle !important;
}

td i {
	line-height: 33px !important;
}

table select {
	text-align: center !important;
}

td button i {
	line-height: 20px !important;
}

td .label {
	line-height: 33px !important;
}
</style>
<script type="text/javascript" src="<%=basePath %>js/user/login.js"></script>
<script type="text/javascript" src="<%=basePath %>js/user/updatePassword.js"></script>
</html>