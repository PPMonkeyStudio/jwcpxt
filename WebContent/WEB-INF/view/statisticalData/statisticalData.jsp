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
<link rel="stylesheet"
	href="<%=basePath%>css/statisticalData/doublebox-bootstrap.css">
<title>统计数据</title>
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
									<h4 class="title">统计数据</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#statistical">
										<i class="ti-plus"></i>开始统计
									</button>
									<!-- 
										1.多选单位
										2.多选业务
										每个业务要给分数，所有业务的分数加起来等于100分，不等就要报错
									 -->
									<table class="table" style="text-align: center; width: 100%;">
										<thead>
											<tr>
												<th></th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="statistical" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" data-backdrop="false">
						<div class="modal-dialog" role="document" style="width:600px">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">统计</h4>
								</div>
								<div class="modal-body">
									<div class="form-group" v-show="step==1">
										<select class="statistical-unit" multiple="multiple" size="10"></select>
									</div>
									<div class="form-group" v-show="step==2">
										<select class="statistical-service" multiple="multiple"
											size="10"></select>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary">{{stemName}}</button>
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
<script src="<%=basePath%>js/statisticalData/doublebox-bootstrap.js"></script>
<script src="<%=basePath%>js/statisticalData/statisticalData.js"></script>
</html>