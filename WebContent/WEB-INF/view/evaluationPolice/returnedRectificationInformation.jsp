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
<title>当事人信息</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbarAppraisal" namespace="/Skip"
				executeResult="true" />
			<div class="" id="content"
				style="width: calc( 100% - 800px )   ;margin: 50px 400px 50px 400px ">
				<!-- 主内容 -->
				<div class="card" style="padding: 20px;">
					<div class="" style="">
						<form class="" v-cloak>
							<div style="" v-if="returnedParty">
								<div class="form-group" style="">
									<label class="">姓名:</label> 
									<span class="form-control-static">{{returnedParty.serviceClient.service_client_name}}</span>
								</div>
								<div class="form-group">
									<label class="">性别:</label> 
									<span v-if="returnedParty.serviceClient.service_client_sex" class="form-control-static">{{returnedParty.serviceClient.service_client_sex==1?"男":"女"}}</span>
								</div>
								<div class="form-group">
									<label class="">电话:</label> 
									<span class="form-control-static">{{returnedParty.serviceClient.service_client_phone}}</span>
								</div>
								<div class="form-group">
									<label class="">原办理业务:</label> 
									<span class="form-control-static">{{returnedParty.serviceInstance.service_instance_old_service_name}}</span>
								</div>
								<div class="form-group">
									<label class="">回访业务:</label> 
									<span class="form-control-static">{{returnedParty.serviceDefinition.service_definition_describe}}</span>
								</div>
								<div class="form-group">
									<label class="">办理时间</label> 
									<span class="form-control-static">{{returnedParty.serviceInstance.service_instance_date}}</span>
								</div>
								<div class="form-group">
									<label class="">办理单位</label> 
									<span class="form-control-static">{{returnedParty.unit.unit_name}}</span>
								</div>
							</div>
							<div style="" v-else>
								<div class="form-group" style="">
									<label class="">今日业务已回访完毕 </label>
								</div>
							</div>
							<div style="" v-if="returnedParty">
								<button style="width: 100%" type="button" @click="beginReturned"
									class="btn btn-primary">开始回访</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 引入底部 -->
		</div>
	</div>
</body>
<script type="text/javascript">
	/* 处理侧边栏选项 */
	$('#sidePolice').attr("class", "active");
</script>
<!-- 和业务回访可以通用 -->
<script
	src="<%=basePath%>js/evaluationPolice/returnedPartyInformation.js"></script>
</html>