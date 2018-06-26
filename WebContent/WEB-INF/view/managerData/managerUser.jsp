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
									<button onclick="addUser()" class="btn btn-default">
										<i class="ti-plus"></i>新建一个警员
									</button>
									<input oninput="changeQuery()" id="searchContent"
										placeholder="请输入搜索内容" class="form-control"
										style="float: right; width: 250px;">
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<table id="userTable" class="table table-striped"
										style="display: none;">
										<thead>
											<tr>
												<td>账号</td>
												<td>姓名</td>
												<td><select onchange="changeQuery()" id="searchUnit"
													class="form-control">
														<option value="">请选择单位</option>
												</select></td>
												<td>状态</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<template v-for="dto in userVO.UserDTOList">
											<tr>
												<td>{{ dto.user.user_account }}</td>
												<td>{{ dto.user.user_name }}</td>
												<template v-if="dto.unit==undifined">
												<td></td>
												</template>
												<template v-else>
												<td>{{ dto.unit.unit_name }}</td>
												</template>
												<template v-if="dto.user.user_state==1">
												<td>正常</td>
												</template>
												<template v-else>
												<td>禁用</td>
												</template>
												<td><a :id="dto.user.jwcpxt_user_id"
													onclick="updateUser(this)"><i class="ti-pencil-alt"></i></a>
													<a :id="dto.user.jwcpxt_user_id" onclick="deleteUser(this)"><i
														class="ti-trash"></i></a></td>
											</tr>
											</template>
										</tbody>
									</table>
									<!-- 分页 -->
									<div id="bottomPage" style="padding: 20px;">
										<span>当前页数:<span id="currPage">{{
												userVO.currPage }}</span></span> <span>共:<span id="totalPage">{{
												userVO.totalPage }}</span>页
										</span> <span onclick="skipToIndexPage()" id="indexPage"
											class="pageOperation">首页</span> <span
											onclick="skipToPrimaryPage()" id="previousPage"
											class="pageOperation">上一页</span> <span
											onclick="skipToNextPage()" id="nextPage"
											class="pageOperation">下一页</span> <span
											onclick="skipToLastPage()" id="lastPage"
											class="pageOperation">末页</span> <span> <input
											id="skipPage" type="text"
											style="text-align: center; width: 60px; height: 30px;"
											class="queryInput">
											<button onclick="skipToArbitrarilyPage()"
												class="btn btn-default"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">跳转</button>
										</span>
									</div>
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
	src="<%=basePath%>js/managerUser/showUser.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/managerUser/managerUser.js"></script>
</html>