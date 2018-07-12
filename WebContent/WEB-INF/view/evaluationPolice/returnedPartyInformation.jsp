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
			<s:action name="skipNavbar" namespace="/Skip" executeResult="true" />
			<div class="content" id="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">当事人信息</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<form class="form-horizontal" v-cloak>
										<div class="form-group">
											<label class="col-sm-2 control-label">姓名</label>
											<div>
												<p class="form-control-static">{{returnedParty.serviceClient.service_client_name}}</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">性别</label>
											<div>
												<p v-if="returnedParty.serviceClient.service_client_sex"
													class="form-control-static">{{returnedParty.serviceClient.service_client_sex==1?"男":"女"}}</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">电话</label>
											<div>
												<p class="form-control-static">{{returnedParty.serviceClient.service_client_phone}}</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">回访业务</label>
											<div>
												<p class="form-control-static">{{returnedParty.serviceDefinition.service_definition_describe}}</p>
											</div>
										</div>
										<button type="button" @click="beginReturned"
											class="btn btn-primary">开始回访</button>
									</form>
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
	$('#sidePolice').attr("class", "active");
</script>
<script
	src="<%=basePath%>js/evaluationPolice/returnedPartyInformation.js"></script>
</html>