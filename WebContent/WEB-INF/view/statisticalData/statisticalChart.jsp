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
										<div style="width: 100%; height:450px; margin-top: 20px;">
											<!-- 群众关注 -->
											<div id="crowdFocus" style="width: 50%;height:400px; float: left;"></div>
											<!-- 群众不满意 -->
											<div id="crowdNotSatisfied" style="width: 50%;height:400px; float: left;"></div>
										</div>
										<div style="width: 100%; height:450px;">
											<!-- 群众关注 -->
											<div id="crowdFocus_bar" style="width: 50%;height:400px; float: left;"></div>
											<!-- 群众不满意 -->
											<div id="crowdNotSatisfied_bar" style="width: 50%;height:400px; float: left;"></div>
										</div>
										<div class="form-group" style="margin: auto; padding-left: 20px;">
											<p><h3>图表</h3></p>
											<label>单位筛选</label>
											<select class="selectpicker" id="jwcpxt_unit_id" onchange="search(this)" title="选择一个单位..." data-live-search="true" ></select> 
											<label style="margin-left: 10px;">时间区间从</label>
											<input type="text" id="startTime" onchange="search(this)" placeholder="起始时间" class="mydate form-control" style="display: inline; width: 150px;"> 
											<label>到</label>
											<input type="text" id="endTime" onchange="search(this)" placeholder="结束时间" class="mydate form-control" style="display: inline; width: 150px;">
										</div>
										<div class="form-group" style="margin: auto; padding-left: 20px; margin-top: 10px;">
											<p>
												<label>按照时间区间类型：</label>
												<button type="button" onclick="checkTimeType(this)" time-type="1" class="btn btn-primary btn-xs timeType" disabled="disabled">日统计</button>
												<button type="button" onclick="checkTimeType(this)" time-type="2" class="btn btn-success btn-xs timeType">周统计</button>
												<button type="button" onclick="checkTimeType(this)" time-type="3" class="btn btn-info btn-xs timeType">月统计</button>
											</p>
										</div>
										<div id="allDissatisfaction" style="width: 100%;height:1000px; margin-top: 20px;"></div>
										<div style="width: 100%; height:700px; margin-top: 50px;">
											<div id="dissatisfiedService" style="width: 50%;height:700px; float: left;"></div>
											<div id="dissatisfactionProblem" style="width: 50%;height:700px; float: right;"></div>
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
	$.datetimepicker.setLocale('ch');
	$('.mydate').datetimepicker({
		pickerPosition : "top-right",
		yearStart : 1900, // 设置最小年份
		yearEnd : 2050, // 设置最大年份
		yearOffset : 0, // 年偏差
		timepicker : false, // 关闭时间选项
		format : 'Y-m-d', // 格式化日期年-月-日
		minDate : '1900/01/01', // 设置最小日期
		maxDate : '2050/01/01', // 设置最大日期
	});
</script>
<script src="<%=basePath%>js/statisticalData/echarts.min.js"></script>
<script src="<%=basePath%>js/statisticalData/echarts-wordcloud.js"></script>
<script src="<%=basePath%>js/statisticalData/statisticalChart.js"></script>
</html>