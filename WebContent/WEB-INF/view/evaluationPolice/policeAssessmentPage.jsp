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
			<s:action name="skipNavbarAppraisal" namespace="/Skip"
				executeResult="true" />
			<div class="content" id="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">警务测评</h4>
									<div class="dropdown" style="float:right;">
										<button style="background-color: #d9534f;"
											class="btn btn-danger dropdown-toggle" type="button"
											id="termination" data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="true">
											终止回访 <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="termination">
											<li><a @click="terminationReturned($event)"
												terminationNum="3" href="javascript:;">空号</a></li>
											<li><a @click="terminationReturned($event)"
												terminationNum="4" href="javascript:;">无人接听</a></li>
											<li><a @click="terminationReturned($event)"
												terminationNum="5" href="javascript:;">占线</a></li>
											<li><a @click="terminationReturned($event)"
												terminationNum="6" href="javascript:;">停机</a></li>
											<li><a @click="terminationReturned($event)"
												terminationNum="7" href="javascript:;">拒访</a></li>
											<li><a @click="terminationReturned($event)"
												terminationNum="8" href="javascript:;">其他</a></li>
										</ul>
									</div>
								</div>
								<div class="content table-responsive table-full-width" v-cloak>
									<!-- <div class="panel panel-def"> -->
									<div class="panel-heading"><strong>业务名称:</strong>{{serviceDefinition.service_definition_describe}}</div>
									<div class="panel-body">
										<div style="width: 50%; float: left;">
											<form class="form-horizontal">
												<div class="form-group">
													 <label for="name">姓名:</label>
													 <p class="form-control-static">{{serviceClien.service_client_name}}</p>
												</div>
												<div class="form-group">
													  <label for="sex">性别</label>
													  <p class="form-control-static">{{serviceClien.service_client_sex=='1'?'男':'女'}}</p>
												</div>
												<div class="form-group">
													  <label for="phone">号码</label>
													  <p class="form-control-static">{{serviceClien.service_client_phone}}</p>
												</div>
												<div class="form-group">
													<label class="">办理时间</label> 
													<p class="form-control-static">{{returnedParty.serviceInstance.service_instance_date}}</p>
												</div>
												<div class="form-group">
													<label class="">办理单位</label> 
													<p class="form-control-static">{{returnedParty.unit.unit_name}}</p>
												</div>
											</form>
										</div>
										<div style="width: 50%; float: left;">
											<p>今天回访数量总数为:<span style="color:red;">{{todayCount}}</span></p>
											<p>今天回访成功数量为:<span style="color:green;">{{todaySuccessCount}}</span></p>
											<br>
											<br>
											<br>
											<p class="form-control-static">
												   <template v-if="serviceDefinition.service_definition_describe.indexOf('110')>-1">
													   <p>110报警:</p>
													   <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													   <p>您于{{returnedParty.serviceInstance.service_instance_date}}向110报过警,为更好改进公安工作,</p>
													   <p>我们特向您做个回访,大约需要耽误您2分钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('交通事故')>-1">
													   <p>交通事故报警:</p>
												 	   <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
												 	   <p>您于{{returnedParty.serviceInstance.service_instance_date}}向122报过交通事故警,为更好改进交通工作,</p>
												 	   <p>我们特向您做个回访,大约需要耽误您2分钟时间，对您回答的问题,我们将严格保密。  </p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('车管')>-1">
													    <p>车管所:</p>
													    <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													    <p> 您于{{returnedParty.serviceInstance.service_instance_date}}在{{returnedParty.unit.unit_name}}交警大队车管所办理机动车驾驶证业务,请问您是本人办的吗?</p>
													    <p>(是)为改进公安工作,我们特向您做个回访,大约需要耽误您2纷钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('出入境')>-1">
													    <p>出入境:</p>
													    <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													    <p>您于{{returnedParty.serviceInstance.service_instance_date}}在公安局办理业务,请问您是自己办的吗?</p>
													   	<p>(是)为改进公安工作,我们特向您做个回访,大约需要耽误您2分钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('刑事案件')>-1">
													    <p>刑事案件:</p>
													    <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													    <p>您于{{returnedParty.serviceInstance.service_instance_date}}在{{returnedParty.unit.unit_name}}向110报过刑事案件警,请问您是当事人本人吗?</p>
													   	<p>(是)为了好改进公安工作,我们特向您作个回访,大约需要耽误您2分钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('特种行业')>-1">
													    <p>特种行业:</p>
													    <p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													    <p>请问您是{{}}负责人吗?</p>
													  	<p>(是)为改进公安机关的常管理我们特向您做个国访大约需要耽误您2分钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('户政')>-1">
													   	<p>户政办证:</p>
													   	<p>您好,我们是萍乡市公安局服务群众测评中心,</p>
													   	<p>您于{{returnedParty.serviceInstance.service_instance_date}}在{{returnedParty.unit.unit_name}}办理过户政业务,请问是本人去办的吗?</p>
													   	<p>(是)为改进公安机关工作我们特向您做个回访,大约需要耽误您2分钟时间,对您回答的问题,我们将严格保密。</p>
												   </template>
												   <template v-else-if="serviceDefinition.service_definition_describe.indexOf('安全感满意度')>-1">
													    <p> 安全感满意度:</p>
													    <p> 您好,这里是萍乡市公安局服务群众测评中心,我们想对您就当地社会治安状况做个电话调查需要和说您几分钟时间。</p>
												   </template>
											</p>
										</div>
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
							<button type="button" @click="finishReturned($event)"  style="float: left;" class="btn btn-primary">结束回访</button>
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