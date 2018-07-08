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
<title>业务实例管理</title>
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
									<h4 class="title">业务实例管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button onclick="returnService()" class="btn btn-default">
										<i class="ti-back-left"></i>返回业务管理
									</button>
									<div style="float: right; margin-right: 10px;">
										<label>业务发生时间</label> <input onchange="changeQuery()"
											id="screenServiceInstanceStartDate" placeholder="请输入搜索内容" class="mydate form-control"
											style="display: inline; width: 150px;"><label>&nbsp;至&nbsp;</label><input
											onchange="changeQuery()" id="screenServiceInstanceStopDate"
											placeholder="请输入搜索内容" class="mydate form-control"
											style="display: inline; width: 150px;">
									</div>
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<div id="showContent">
										<table id="serviceTable" class="table table-striped"
											style="text-align: center;">
											<thead>
												<tr>
													<td>业务编号</td>
													<td>业务办理时间</td>
													<td><select class="form-control" id="humanSelect" onchange="changeQuery()" style="text-align:center;">
															<option value="" style="text-align:center;">是否分配测评员</option>
													</select></td>
													<td>查看当事人</td>
												</tr>
											</thead>
											<tbody>
												<template
													v-for="serviceInstanceDTO in instanceVO.serviceInstanceDTOList">
												<tr>
													<td>{{
														serviceInstanceDTO.serviceInstance.service_instance_nid }}</td>
													<td>{{
														serviceInstanceDTO.serviceInstance.service_instance_date
														}}</td>

													<template v-if="serviceInstanceDTO.judge == undefined">
													<td><a
														:id="serviceInstanceDTO.serviceInstance.jwcpxt_service_instance_id"
														onclick="allocationHuman(this)">分配测评员</a></td>
													</template>
													<template v-else>
													<td>{{ serviceInstanceDTO.judge.user_name }}</td>
													</template>
													<td><a
														:id="serviceInstanceDTO.serviceInstance.jwcpxt_service_instance_id"
														onclick="viewParties(this)">查看</a></td>
												</tr>
												</template>
											</tbody>
										</table>
										<!-- 分页 -->
										<div id="bottomPage" style="padding: 20px;">
											<span>当前页数:<span id="currPage">{{
													instanceVO.currPage }}</span>
											</span> <span>共:<span id="totalPage">{{
													instanceVO.totalPage }}</span>页
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
<script type="text/javascript">
	$.datetimepicker.setLocale('ch');
	$('.mydate').datetimepicker({
		yearStart : 1900, // 设置最小年份
		yearEnd : 2050, // 设置最大年份
		yearOffset : 0, // 年偏差
		timepicker : false, // 关闭时间选项
		format : 'Y-m-d', // 格式化日期年-月-日
		minDate : '1900/01/01', // 设置最小日期
		maxDate : '2050/01/01', // 设置最大日期
	});
	$('.mydate_minute').datetimepicker({
		yearStart : 1900, // 设置最小年份
		yearEnd : 2050, // 设置最大年份
		yearOffset : 0, // 年偏差
		timepicker : true, // 关闭时间选项
		format : 'Y-m-d H:i', // 格式化日期年-月-日
		minDate : '1900/01/01', // 设置最小日期
		maxDate : '2050/01/01', // 设置最大日期
	});
</script>
<script type="text/javascript"
	src="<%=basePath%>js/managerServiceInstance/managerServiceInstance.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/managerServiceInstance/showServiceInstance.js"></script>
</html>