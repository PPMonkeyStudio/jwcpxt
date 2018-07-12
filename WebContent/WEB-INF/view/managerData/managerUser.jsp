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
[v-cloak] {
	display: none;
}

a:hover {
	cursor: pointer;
}

i {
	cursor: :pointer !important;
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
									<h4 class="title">警员管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button @click="addUser" class="btn btn-default">
										<i class="ti-plus"></i>新建一个警员
									</button>
									<!-- <input oninput="changeQuery()" id="searchContent"
										placeholder="请输入搜索内容" class="form-control"
										style="float: right; width: 250px;"> -->
									<!-- <div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div> -->
									<table class="table" style=" text-align: center;">
										<thead>
											<tr>
												<td>账号</td>
												<td>姓名</td>
												<td>类型</td>
												<%-- <td><select onchange="changeQuery()" id="searchUnit"
													class="form-control">
														<option value="">请选择单位</option>
												</select></td> --%>
												<td>状态</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody v-cloak>
											<template v-for="(user,index) in userInfo">
											<tr>
												<td>{{user.user_account}}</td>
												<td>{{user.user_name}}</td>
												<td><template v-if="user.user_type==1"> <span
														class="label label-info">测评员</span> </template> <template
														v-else-if="user.user_type==2"> <span
														class="label label-primary">统计员</span> </template></td>
												<td><template v-if="user.user_state==1"> <span
														class="label label-info">活动</span> </template> <template
														v-if="user.user_state==2"> <span
														class="label label-danger">禁用</span> </template></td>
												<td><a><i @click="resetPassword(index)" class="fa fa-undo"></i></a>&nbsp;
													<a><i @click="banUser(index)" class="fa fa-ban fa-fw"></i></a></td>
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
	src="<%=basePath%>js/managerUser/managerUser.js"></script>
</html>