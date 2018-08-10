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
<title>超时未整改</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbarIndex" namespace="/Skip" executeResult="true" />
			<div class="content" id="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">未整改</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<div style="float: right; margin-right: 10px;">
										 <select class="selectpicker" title="选择业务..." name="feedbackRectificationExceedTimeVO.searchService" data-live-search="true" onchange="changeQuery(this)"></select>
										 <label style="color: black;">整改时间</label> <input
											onchange="changeQuery(this)" name="feedbackRectificationExceedTimeVO.searchTimeStart"
											class="mydate form-control"
											style="display: inline; width: 150px;"><label
											style="color: black;">&nbsp;至&nbsp;</label><input
											onchange="changeQuery(this)" name="feedbackRectificationExceedTimeVO.searchTimeEnd"
											class="mydate form-control" style="display: inline; width: 150px;">
										    <input name="feedbackRectificationExceedTimeVO.search"
											style="display: inline; width: 250px;" placeholder="请输入(编号,标题,单位,负责人)"
											@keyup="searchRectification($event)" class="form-control"> 
									</div>
									<div id="notRectification" v-cloak>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<td>编号</td>
													<td>问题标题</td>
													<td>单位</td>
													<td>单位负责人</td>
													<td>负责人电话</td>
													<td>当事人姓名</td>
													<!-- <td>当事人性别</td> -->
													<td>当事人电话</td>
												</tr>
											</thead>
											<tbody>
												<template
													v-for="feedbackRectificationDTO in listFeedbackRectificationDTO">
												<tr>
													<td>{{feedbackRectificationDTO.feedbackRectification.feedback_rectification_no}}</td>
													<td>{{feedbackRectificationDTO.question.question_describe}}</td>
													<td>{{feedbackRectificationDTO.unit.unit_name}}</td>
													<td>{{feedbackRectificationDTO.unit.unit_contacts_name}}</td>
													<td>{{feedbackRectificationDTO.unit.unit_phone}}</td>
													<td>{{feedbackRectificationDTO.serviceClient.service_client_name}}</td>
													<!-- <td>{{feedbackRectificationDTO.serviceClient.service_client_sex==1?"男":"女"}}</td> -->
													<td>{{feedbackRectificationDTO.serviceClient.service_client_phone}}</td>
												</tr>
												</template>
											</tbody>
										</table>
										<!-- 分页 -->
										<div id="bottomPage" style="padding: 20px;" v-cloak>
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
	$('#bellNotEectificationPage').attr("class", "active");
</script>
<script src="<%=basePath%>js/abarbeitung/notEectificationOverTime.js"></script>
</html>