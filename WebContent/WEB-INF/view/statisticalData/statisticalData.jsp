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

i {
	cursor: pointer;
}

th {
	text-align: center !important;
}
</style>
<link rel="stylesheet"
	href="<%=basePath%>css/statisticalData/doublebox-bootstrap.css">
<link rel="stylesheet"
	href="<%=basePath%>css/statisticalData/element.min.css">
<title>统计数据</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbarIndex" namespace="/Skip"
				executeResult="true" />
			<div class="content" id="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">统计数据</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<!-- <button type="button" class="btn btn-default"
										@click="beginStatistical">
										<i class="ti-plus"></i>开始统计
									</button> -->

									<div style="height: 70px; width: 100%; ">
										<el-steps simple finish-status="success" :active="4">
										<el-step title="选择单位" description="选择单位"></el-step> <el-step
											title="选择业务" description="选择业务"></el-step> <el-step
											title="业务分数" description="业务分数"> </el-step> <el-step
											title="时间区间" description="时间区间"> </el-step> </el-steps>
									</div>
									<div style="width: 100%; margin-top: 50px;">
										<div style="width: 90%; margin: auto;">
											<div class="form-group">
												<h2>选择单位</h2>
												<select class="statistical-selected" id="unit"
													multiple="multiple" size="10"></select>
											</div>
										</div>
										<div style="width: 90%; margin: auto; margin-top: 50px;">
											<div class="form-group">
												<h2>选择业务</h2>
												<select class="statistical-selected" id="service"
													multiple="multiple" size="10"></select>
											</div>
										</div>
										<div style="width: 90%; margin: auto; margin-top: 50px;"
											v-cloak>
											<h2>业务分数</h2>
											<template v-for="(service,index) in serviceSelect">
											<div class="form-group">
												<label>{{service.service_definition_describe}}</label> <input
													type="text" style="width:240px;"
													class="form-control inputScore" :index="index"
													:serviceid="service.jwcpxt_service_definition_id"
													placeholder="请输入业务分数..." onblur="this.v();"
													onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)">
											</div>
											</template>
										</div>
										<div style="width: 90%; margin: auto; margin-top: 30px;">
											<h2>时间区间</h2>
											<div class="form-group" style="margin: auto;">
												<label>起始时间</label> <input id="beginTime" placeholder="起始时间"
													class="mydate form-control"
													style="display: inline; width: 150px;"> <label>结束时间</label>
												<input id="endTime" placeholder="结束时间"
													class="mydate form-control"
													style="display: inline; width: 150px;">
											</div>
										</div>
										<div
											style="width: 90%; margin: auto; margin-top: 100px; text-align: center;">
											<button type="button" class="btn btn-default"
												@click="reviewResult" style="margin: auto;">
												<i class="ti-plus"></i>预览统计结果
											</button>
											<button type="button" class="btn btn-default"
												@click="exportResult" style="margin: auto;">
												<i class="ti-plus"></i>导出统计结果
											</button>
										</div>
									</div>

									<!-- 
										1.多选单位
										2.多选业务
										每个业务要给分数，所有业务的分数加起来等于100分
									 -->
									<!-- <table v-if="ready" class="table"
										style="text-align: center; width: 100%;">
										<thead>
											<tr>
												<template v-if="statisticalResultsData.length>0">
												<th style="width: 150px;">评测内容单位</th>
												<template
													v-for="serviceGradeDTO in statisticalResultsData[0].serviceGradeBelongUnitDTOList">
												<th>{{serviceGradeDTO.serviceDefinition.service_definition_describe}}</th>
												</template>
												<th style="width: 60px;">测评总分</th>
												<th style="width: 40px;">排名</th>
												</template>
											</tr>
										</thead>
										<tbody>
											<template
												v-for="(statisticalResultsDTO,index) in statisticalResultsData">
											<tr>
												<td>{{statisticalResultsDTO.unit.unit_name}}</td>
												<template
													v-for="serviceGradeDTO in statisticalResultsDTO.serviceGradeBelongUnitDTOList">
												<td>{{serviceGradeDTO.grade}}</td>
												</template>
												<td>{{100}}</td>
												<td>{{index+1}}</td>
											</tr>
											</template>
										</tbody>
									</table> -->
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
	<form method="POST" action="" id="exportForm"></form>
</body>
<script type="text/javascript">
	/* 处理侧边栏选项 */
	$('#sideStatis').attr("class", "active");
</script>
<script src="<%=basePath%>js/statisticalData/element.min.js"></script>
<script src="<%=basePath%>js/statisticalData/doublebox-bootstrap.js"></script>
<script src="<%=basePath%>js/statisticalData/statisticalData.js"></script>
</html>