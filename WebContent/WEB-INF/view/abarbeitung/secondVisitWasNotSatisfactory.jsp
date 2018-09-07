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

i, a {
	cursor: pointer;
}

th {
	text-align: center !important;
}
</style>
<title>二次回访</title>
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
									<h4 class="title">二次回访仍不满意列表</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<div style="float: right; margin-right: 10px;">
										<select class="selectpicker" title="选择业务..."
											name="secondDistatisVO.searchService" data-live-search="true"
											onchange="changeQuery(this)"></select> <label
											style="color: black;">回访时间</label> <input
											@change="searchRectification($event)"
											name="secondDistatisVO.searchTimeStart"
											class="mydate form-control" placeholder="起始时间"
											style="display: inline; width: 150px;"><label
											style="color: black;">&nbsp;至&nbsp;</label><input
											@change="searchRectification($event)"
											name="secondDistatisVO.searchTimeEnd"
											class="mydate form-control" placeholder="结束时间"
											style="display: inline; width: 150px;"> <input
											name="secondDistatisVO.search"
											style="display: inline; width: 250px;"
											@keyup="searchRectification($event)" class="form-control"
											placeholder="请输入(单位,问题)">
									</div>
									<div id="notRectification" v-cloak>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<td>单位</td>
													<td>原业务流转单</td>
													<td>单位负责人</td>
													<td>单位号码</td>
													<td>业务名称</td>
													<td>问题标题</td>
													<td>当事人姓名</td>
													<td>当事人电话</td>
													<td><select style="width: 100px;" class="form-control"
														name="secondDistatisVO.feedbackState"
														@change="searchRectification($event)">
															<option value="">全部</option>
															<option value="1">未处理</option>
															<option value="2">已处理</option>
													</select></td>
													<td>操作</td>
												</tr>
											</thead>
											<tbody>
												<template v-for="SecondDistatisDTO in listSecondDistatisDTO">
												<tr>
													<td>{{SecondDistatisDTO.unit.unit_name}}</td>
													<td><a
														:id="SecondDistatisDTO.serviceInstance.service_instance_belong_feedback"
														onclick="preview(this)">流转单 </a></td>
													<td>{{SecondDistatisDTO.unit.unit_contacts_name}}</td>
													<td>{{SecondDistatisDTO.unit.unit_phone}}</td>
													<td>{{SecondDistatisDTO.serviceDefinition.service_definition_describe}}</td>
													<td>{{SecondDistatisDTO.question.question_describe}}</td>
													<td>
														<a href="javascript:;"  onclick="skipToClientInfomation(this)"
														   :phone="SecondDistatisDTO.serviceClient.service_client_phone"
														   :definitionId="SecondDistatisDTO.serviceDefinition.jwcpxt_service_definition_id">
														{{SecondDistatisDTO.serviceClient.service_client_name}}</a>
													</td>
													<td>{{SecondDistatisDTO.serviceClient.service_client_phone}}</td>
													<td>
														<template v-if="SecondDistatisDTO.serviceInstance.service_instance_feedback_state=='1'">
															<span class="label label-warning">未处理</span>
														</template>
														<template v-if="SecondDistatisDTO.serviceInstance.service_instance_feedback_state=='2'">
															<span class="label label-info">已处理</span>
														</template>
													</td>
													<td>
														<template v-if="SecondDistatisDTO.serviceInstance.service_instance_feedback_state=='1'">
															<a href="javascript:;" :id="SecondDistatisDTO.serviceInstance.jwcpxt_service_instance_id" onclick="changeState(this)">处理</a>
														</template>
														<template v-if="SecondDistatisDTO.serviceInstance.service_instance_feedback_state=='2'">
															<a href="javascript:;" :id="SecondDistatisDTO.serviceInstance.jwcpxt_service_instance_id" onclick="viewReason(this)">查看</a>
														</template>
													</td>
												</tr>
												</template>
											</tbody>
										</table>
										<!-- 分页 -->
										<div id="bottomPage" style="padding: 20px;" v-cloak>
											<span>总记录数:<span id="currPage">{{page.totalCount}}</span></span>
											<span>当前页数:<span id="currPage">{{page.currPage}}</span></span>
											<span>共:<span id="totalPage">{{page.totalPage}}</span>页
											</span> <span class="pageOperation" @click="firstPage">首页</span> <span
												class="pageOperation" @click="prePage">上一页</span> <span
												class="pageOperation" @click="nextPage">下一页</span> <span
												class="pageOperation" @click="lastPage">末页</span> <span><input
												type="text" id="toPageInput"
												style="text-align: center; width: 60px; height: 30px;"
												onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)"
												onblur="this.v();">
												<button class="btn btn-default" @click="toPage"
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
	$('#bellTwiceVisitPage').attr("class", "active");
</script>
<script
	src="<%=basePath%>js/abarbeitung/secondVisitWasNotSatisfactory.js"></script>
</html>