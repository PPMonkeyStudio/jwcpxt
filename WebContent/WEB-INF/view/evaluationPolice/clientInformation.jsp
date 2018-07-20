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
<title>警务测评</title>
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
									<select style="width:120px; float: left;" class="form-control"
										id="allAppraisal" @change="queryClient">
										<option></option>
									</select>
									<div style="width: 500px; float: left; margin-left: 10px;">
										<div class="form-group" style="margin: auto;">
											<input id="beginTime" placeholder="起始时间"
												@change="queryClient" class="mydate form-control"
												style="display: inline; width: 150px;">--<input
												@change="queryClient" id="endTime" placeholder="结束时间"
												class="mydate form-control"
												style="display: inline; width: 150px;">
										</div>
									</div>
									<input @keyup="queryClient" placeholder="请输入搜索内容"
										name="questionVO.screenSearch" class="form-control"
										style="float: right; width: 250px;">
									<table class="table" style="text-align: center; width: 100%;">
										<thead>
											<tr style="border-bottom: 2px solid #ddd">
												<th style="width: 60px;">姓名</th>
												<th style="width: 60px;">性别</th>
												<th style="width: 60px;">电话</th>
												<th style="width: 60px;"><select class="form-control"
													@change="queryClient" name="questionVO.screenType">
														<option value="">全部</option>
														<option value="1">成功</option>
														<option value="2">未回访</option>
														<option value="3">空号</option>
														<option value="4">无人接听</option>
														<option value="5">占线</option>
														<option value="6">停机</option>
														<option value="7">拒访</option>
														<option value="8">其他</option>
												</select></th>
												<th style="width: 60px;">所属业务</th>
												<th style="width: 60px;">办理单位</th>
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
													class="label label-info">开放题</span> <span v-else
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
<script src="<%=basePath%>js/evaluationPolice/clientInformation.js"></script>
</html>