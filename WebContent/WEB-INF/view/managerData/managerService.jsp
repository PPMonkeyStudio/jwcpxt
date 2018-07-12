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
<style type="text/css">
a:hover {
	cursor: pointer;
}

.pageOperation:hover {
	cursor: pointer;
}
.interfaceTable tr{
	
}
</style>
<title>业务管理</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbar" namespace="/Skip" executeResult="true" />
			<div class="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div id="managerContent" class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">业务管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button onclick="addService()" class="btn btn-default">
										<i class="ti-plus"></i>新建一个业务
									</button>
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<table id="serviceTable" class="table table-striped"
										style="text-align: center; display: none;">
										<thead>
											<tr>
												<td>业务名</td>
												<td>创建时间</td>
												<td>进入问题</td>
												<td>修改业务</td>
												<td>接口管理</td>
											</tr>
										</thead>
										<tbody>
											<template v-for="service in serviceList">
												<tr>
													<td>{{ service.service_definition_describe }}</td>
													<td>{{ service.service_definition_gmt_create }}</td>
													<td><a :id="service.jwcpxt_service_definition_id" onclick="intoQuestion(this)"><i class="ti-arrow-right"></i></a></td>
													<td><a :id="service.jwcpxt_service_definition_id" onclick="updateService(this)"><i class="ti-pencil-alt"></i></a></td>
													<td><a :id="service.jwcpxt_service_definition_id" onclick="showInterface(this)"><i class="ti-key"></i></a></td>
												</tr>
											</template>
										</tbody>
									</table>
									<!-- 分页 -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 引入底部 -->
			<s:action name="skipFooter" namespace="/Skip" executeResult="true" />
		</div>
	</div>
</body>
<script type="text/javascript">
	/* 处理侧边栏选项 */
	$('#sideManager').attr("class", "active");
</script>
<script type="text/javascript"
	src="<%=basePath%>js/managerService/showService.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/managerService/managerService.js"></script>
</html>