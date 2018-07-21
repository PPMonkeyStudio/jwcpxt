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
<style type="text/css"></style>
<title>统计图表</title>
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
									<h4 class="title">统计图表</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<div style="width: 100%">
										<div class="form-group" style="margin: auto; padding-left: 20px;">
											<label>单位筛选</label>  
											<select class="selectpicker" id="SearchUnit" onchange="searchUnit(this)" title="选择一个单位..." data-live-search="true" ></select> 
											<label>起始时间</label> 
											<input type="text" id="beginTime" onchange="searchBeginTime(this)" placeholder="起始时间" class="mydate form-control" style="display: inline; width: 150px;"> 
											<label>结束时间</label>
											<input type="text" id="endTime" onchange="searchEndTime(this)" placeholder="结束时间" class="mydate form-control" style="display: inline; width: 150px;">
										</div>
										<div id="allDissatisfaction" style="width: 100%;height:600px; margin-top: 50px;"></div>
										<div style="width: 100%; height:650px; margin-top: 50px;">
											<div id="dissatisfiedService" style="width: 50%;height:600px;"></div>
											<div id="dissatisfactionProblem" style="width: 50%;height:600px;"></div>
										</div>
										<!-- <div id="main" style="width: 100%;height:400px; margin-top: 50px;"></div> -->
										<!-- <div id="main2" style="width: 100%;height:400px; margin-top: 50px;"></div> -->
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
<script src="<%=basePath%>js/statisticalData/echarts.min.js"></script>
<script src="<%=basePath%>js/statisticalData/statisticalChart.js"></script>
</html>