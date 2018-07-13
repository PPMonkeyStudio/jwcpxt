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
<title>用户信息</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbar" namespace="/Skip" executeResult="true" />
			<div class="content" id="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">问卷管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#addQuestionModal">
										<i class="ti-plus"></i>添加一个问题
									</button>
									<button type="button" class="btn btn-default" @click="preview">
										<i class="fa fa-th-list"></i>预览
									</button>
									<input @keyup="queryQuestion" placeholder="请输入搜索内容"
										name="questionVO.screenSearch" class="form-control"
										style="float: right; width: 250px;">
									<!-- <div v-if="!ready" id="loadingLayer"
										style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div> -->
									<table class="table" style="text-align: center; width: 100%;">
										<thead>
											<tr style="border-bottom: 2px solid #ddd">
												<th style="width: 60px;">序号</th>
												<th>问题描述</th>
												<th style="width: 120px;"><select class="form-control"
													@change="queryQuestion" name="questionVO.screenType">
														<option value="">全部</option>
														<option value="1">选择题</option>
														<option value="2">主观题</option>
												</select></th>
												<th style="width: 150px;">操作</th>
											</tr>
										</thead>
										<tbody v-cloak>
											<template v-for="(question,index) in questionVO.questionList">
											<tr style="border-top: 1px solid #ddd;">
												<th>{{question.question_sort}}</th>
												<td>{{question.question_describe}}</td>
												<td><span v-if="question.question_type==1"
													class="label label-primary">选择题</span> <span
													v-else-if="question.question_type==2"
													class="label label-info">主观题</span> <span v-else
													class="label label-warning">未定义</span></td>
												<td><i class="ti-pencil-alt"
													@click="modifyQuestion(index)"></i> &nbsp; <i
													class="fa fa-arrow-up" @click="moveQuestion(index,1)"></i>&nbsp;
													<i class="fa fa-arrow-down" @click="moveQuestion(index,2)"></i>&nbsp;
													<i class="ti-trash" @click="deleteQuestion(index)"></i></td>
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
											onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
											onblur="this.v();">
											<button class="btn btn-default" @click="toPage"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">跳转</button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- addQuestionModal添加一个问题的模态框 -->
					<div class="modal fade" id="addQuestionModal" tabindex="-1"
						role="dialog" data-backdrop="false">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">添加一个问题</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label for="questionDescribe">问题描述</label>
										<textarea class="form-control"
											v-model="addQuestionModalData.question_describe"
											placeholder="Problem description。。"></textarea>
									</div>
									<div class="form-group">
										<label for="questionType">问题类型</label> <select
											class="form-control"
											v-model="addQuestionModalData.question_type">
											<option value="">请选择问题类型</option>
											<option value="1">选择题</option>
											<option value="2">主观题</option>
										</select>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary"
										@click="addQuestion">保存</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

					<!-- checkQuestionModal查看问题的模态框 -->
					<div class="modal fade" id="checkQuestionModal"
						style="overflow-y: hidden;" role="dialog" data-backdrop="false">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">问题详情</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label>问题描述</label>
										<textarea class="form-control"
											placeholder="Problem description。。"
											v-model="checkQuestionModalData.question.question_describe"></textarea>
									</div>
									<%-- <div class="form-group">
										<label>问题类型</label> <select class="form-control"
											disabled="disabled"
											v-model="checkQuestionModalData.question.question_type">
											<option value="">请选择问题类型</option>
											<option value="1">选择题</option>
											<option value="2">主观题</option>
										</select>
									</div> --%>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-info"
										v-if="checkQuestionModalData.question.question_type==1"
										@click="modifyOpintion">修改选项</button>
									<button type="button" class="btn btn-primary"
										@click="modifyDescribe">修改描述</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

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
<script
	src="<%=basePath%>js/questionnaireDetails/questionnaireDetails.js"></script>
</html>