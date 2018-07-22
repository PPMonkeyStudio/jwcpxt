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

.hideALl {
	display: none;
}
</style>
<title>整改反馈</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbarAbarbeitung" namespace="/Skip"
				executeResult="true" />
			<div class="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div id="managerContent" class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">整改反馈</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<div style="float: right; margin-right: 10px;">
										<label style="color: black;">整改时间</label> <input
											onchange="changeQuery()" id="searchTimeStart"
											class="mydate form-control"
											style="display: inline; width: 150px;"><label
											style="color: black;">&nbsp;至&nbsp;</label><input
											onchange="changeQuery()" id="searchTimeEnd"
											class="mydate form-control"
											style="display: inline; width: 150px;"> <input
											id="searchTitle" style="display: inline; width: 250px;"
											onchange="changeQuery()" class="form-control"
											placeholder="请输入搜索内容">

									</div>
									<div id="showContent">
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<td>编号</td>
													<td>问题标题</td>
													<td><select id="searchHandleState"
														onchange="changeQuery()" class="form-control">
															<option value="-1">办理情况</option>
															<option value="1">未办</option>
															<option value="2">办结</option>
													</select></td>
													<td>当事人</td>
													<td><select id="searchAuditState"
														onchange="changeQuery()" class="form-control">
															<option value="-1">审核状态</option>
															<option value="1">未审核</option>
															<option value="2">主管部门审核通过</option>
															<option value="3">主管部门审核驳回</option>
															<option value="4">测评中心审核通过</option>
															<option value="5">测评中心审核驳回</option>
													</select></td>
													<td>操作</td>
												</tr>
											</thead>
											<tbody id="rectificationTable">
												<template
													v-for=" feedbackRectification in rectificationVO.listFeedbackRectificationDTO ">
												<tr>
													<td>{{ feedbackRectification.feedbackRectification.feedback_rectification_no
														}}</td>
													<td>{{
														feedbackRectification.feedbackRectification.feedback_rectification_title }}</td>
													<td><template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_handle_state == 1">
														<span class="label label-primary">未办</span> </template> <template
															v-else> <span class="label label-success">办结</span>
														</template></td>
													<td>{{
														feedbackRectification.feedbackRectification.feedback_rectification_client_name
														}}</td>
													<td><template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_audit_state == 1">
														未审核 </template> <template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_audit_state == 2">
														主管部门审核通过 </template> <template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_audit_state == 3">
														主管部门审核驳回 </template> <template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_audit_state == 4">
														测评中心审核通过 </template> <template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_audit_state == 5">
														测评中心审核驳回 </template></td>
													<td><template
															v-if="feedbackRectification.feedbackRectification.feedback_rectification_handle_state == 1">
														<a
															:id="feedbackRectification.feedbackRectification.jwcpxt_feedback_rectification_id"
															onclick="handleEnd(this)">整改|</a> </template> <a
														:id="feedbackRectification.feedbackRectification.jwcpxt_feedback_rectification_id"
														onclick="preview(this)">流转单 </a></td>
												</tr>
												</template>
											</tbody>
										</table>
										<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
											<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
										</div>
										<!-- 分页 -->
										<div id="bottomPage" style="padding: 20px;">
											<span>当前页数:<span id="currPage"></span>{{
												rectificationVO.currPage }}
											</span> <span>共:<span id="totalPage">{{
													rectificationVO.totalPage }}</span>页
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
	$('#sideAbarbeitung').attr("class", "active");
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
	src="<%=basePath%>js/rectification/showRectification.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/rectification/managerRectification.js"></script>
</html>