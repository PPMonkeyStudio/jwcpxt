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
</style>
<title>用户信息</title>
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
									<h4 class="title">单位管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<table id="unitTable" class="table table-striped"
										style="text-align: center;">
										<thead>
											<tr>
												<td>单位名称</td>
												<td>机构代码</td>
												<!-- <td>账号</td> -->
												<td>联系人姓名</td>
												<td>联系号码</td>
												<td>操作</td>
												<td>子单位</td>
												<td>业务</td>
											</tr>
										</thead>
										<tbody>
											<template v-for="unit in unitVO.unitList">
											<tr>
												<td>{{ unit.unit_name }}</td>
												<td>{{ unit.unit_num }}</td>
												<!-- <td>{{ unit.unit_account }}</td> -->
												<td>{{ unit.unit_contacts_name }}</td>
												<td>{{ unit.unit_phone }}</td>
												<td><a :id="unit.jwcpxt_unit_id" onclick="updateUnit(this)">修改</a>|<a
													:id="unit.jwcpxt_unit_id" onclick="resetPassword(this)">重置密码</a></td>
												<td><a :id="unit.jwcpxt_unit_id" onclick="managerSonUnit(this)">管理子单位</a></td>
												<td><a :id="unit.jwcpxt_unit_id" onclick="managerService(this)">管理业务</a></td>
											</tr>
											</template>
										</tbody>
									</table>
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
	src="<%=basePath%>js/managerUnit/showUnit.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/managerUnit/managerUnit.js"></script>
</html>