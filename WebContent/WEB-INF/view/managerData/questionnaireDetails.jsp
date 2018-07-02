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
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">问卷管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#addQuestionModal">
										<i class="ti-plus"></i>添加一个问题
									</button>
									<input id="searchContent" placeholder="请输入搜索内容"
										class="form-control" style="float: right; width: 250px;">
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<table id="questionTable" class="table table-striped"
										style="display: none; text-align: center;">
										<thead>
											<tr>
												<td>#</td>
												<td>问题描述</td>
												<td><select class="form-control">
														<option value="">请选择问题类型</option>
												</select></td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{ dto.user.user_account }}</td>
												<td>{{ dto.user.user_name }}</td>
												<td><i class="ti-pencil-alt"></i> <i
													class="fa fa-arrow-up" aria-hidden="true"></i> <i
													class="fa fa-arrow-down" aria-hidden="true"></i> <i
													class="ti-trash"></i></td>
											</tr>
										</tbody>
									</table>
									<!-- 分页 -->
									<div id="bottomPage" style="padding: 20px;">
										<span>当前页数:<span id="currPage">{{userVO.currPage}}</span></span>
										<span>共:<span id="totalPage">{{userVO.totalPage}}</span>页
										</span> <span class="pageOperation">首页</span> <span
											class="pageOperation">上一页</span> <span class="pageOperation">下一页</span>
										<span class="pageOperation">末页</span> <span> <input
											type="text"
											style="text-align: center; width: 60px; height: 30px;"
											class="queryInput">
											<button class="btn btn-default"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">跳转</button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- addQuestionModal添加一个问题的模态框 -->
				<div class="modal fade" id="addQuestionModal" tabindex="-1"
					role="dialog" aria-labelledby="addQuestionModal">
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
								<table class="table" style="width: 100%;">
									<tbody>
										<tr>
											<td>
												<div class="form-group">
													<label for="questionDescribe">问题描述</label> <input
														type="text" class="form-control" id="questionDescribe"
														placeholder="Problem description。。">
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label for="questionType">问题类型</label> <select
														class="form-control" id="questionType">
														<option value="">请选择问题类型</option>
														<option value="1">选择题</option>
														<option value="2">开放题</option>
													</select>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary">保存</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<!-- checkQuestionModal查看问题的模态框 -->
				<div class="modal fade" id="checkQuestionModal" tabindex="-1"
					role="dialog" aria-labelledby="checkQuestionModal">
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
								<table class="table" style="width: 100%;">
									<tbody>
										<tr>
											<td>
												<div class="form-group">
													<label for="questionDescribe">问题描述</label> <input
														type="text" class="form-control" id="questionDescribe"
														placeholder="Problem description。。">
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label for="questionType">问题类型</label> <select
														class="form-control" id="questionType">
														<option value="">请选择问题类型</option>
														<option value="1">选择题</option>
														<option value="2">开放题</option>
													</select>
												</div>
											</td>
										</tr>
										
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary">保存</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->



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