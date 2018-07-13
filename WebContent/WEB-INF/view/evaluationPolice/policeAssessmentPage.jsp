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
<link href="<%=basePath%>css/square/blue.css" rel="stylesheet">
<title>警务测评</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<script src="<%=basePath%>js/icheck.js"></script>
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
									<h4 class="title">问卷测评</h4>
								</div>
								<div class="content table-responsive table-full-width" v-cloak>
<!-- 									<div class="panel panel-def"> -->
										<div class="panel-heading">{{serviceDefinition.service_definition_describe}}</div>
										<div class="panel-body">
										    <form class="form-inline">
											  <div class="form-group">
											    <label for="name">姓名</label>
											    <div class="col-sm-10">
											      <p class="form-control-static">{{serviceClien.service_client_name}}</p>
											    </div>
											  </div>
											  <div class="form-group">
											    <label for="sex">性别</label>
											    <div class="col-sm-10">
											      <p class="form-control-static">{{serviceClien.service_client_sex}}</p>
											    </div>
											  </div>
											  <div class="form-group">
											    <label for="phone">号码</label>
											    <div class="col-sm-10">
											      <p class="form-control-static">{{serviceClien.service_client_phone}}</p>
											    </div>
											  </div>
											</form>
										</div>
										<div style="padding: 20px">
										<template v-for="(questionDTO,index) in questionData">
										<hr>
<!-- 										<li class="list-group-item"> -->
											<!-- 选择题 -->
											<template v-if="questionDTO.question.question_type==1">
												 <div class="form-group">
													 <label>{{questionDTO.question.question_sort +'.'+ questionDTO.question.question_describe}}</label>
													 <!-- 选项循环 -->
													 <template v-for="(optionDTO,index1) in questionDTO.listOptionDTO">
													 <div class="form-group">
													 	 <input type="radio" :name="index" :optionIndex="index1" :optionID="optionDTO.option.jwcpxt_option_id">
														 <template v-if="optionDTO.option.option_push==1">
														 	<label class="control-label"><span style="color:red;">{{optionDTO.option.option_describe}}</span></label>
														 </template>
														 <template v-if="optionDTO.option.option_push==2">
														 	<label class="control-label">{{optionDTO.option.option_describe}}</label>
														 </template><small style="color:red;" v-if="optionDTO.listInquiriesOptionDTO.length>0">(追问)</small>
														 <div style="margin-left: 20px;" class="inquiriesContent"></div>
													 </div>
													 </template>
												</div>
											</template>
											<!-- 主观题 -->
											<template v-else-if="questionDTO.question.question_type==2">
												 <div class="form-group">
													 <label>{{questionDTO.question.question_sort +'.'+ questionDTO.question.question_describe}}</label>
													 <textarea class="form-control" rows="3" @change="inputTextarea($event,index)"></textarea>
												</div>
											</template>
											<template v-else>
												 <div class="form-group">
													 <label>null</label>
													 <textarea class="form-control" rows="3"></textarea>
												</div>
											</template>
											
<!-- 										</li> -->
										</template>
									</div>
									</div>
<!-- 								</div> -->
							</div>
							<button type="button" @click="finishReturned($event)" class="btn btn-primary">结束回访</button>
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
	$('#sidePolice').attr("class", "active");
</script>
<script src="<%=basePath%>js/evaluationPolice/policeAssessmentPage.js"></script>
</html>