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
	
<title>系统首页</title>
</head>
<body>
<div class="wrapper">
	<!-- 引入侧边栏 -->
	<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
	<div class="main-panel">
		<!-- 引入导航条  -->
		<s:action name="skipNavbarIndex" namespace="/Skip" executeResult="true" />
		<div class="content">
			<div class="container-fluid">
				<!-- 主内容 -->
				<div class="row">
					<div class="col-md-12">
						<div class="card" style="text-align: center; height: 300px; padding-top: 10px;">
						 	<h1>警务测评系统</h1>
							<h3>欢迎来到警务测评系统</h3>
								<div class="content table-responsive table-full-width">
									<img alt="" src="<%=basePath%>img/process.png">
									<!-- <div style="width: 100%">
										<div class="form-group" style="margin: auto; padding-left: 20px;">
											<label>起始时间</label> 
											<input type="text" id="beginTime" onchange="searchBeginTime(this)" placeholder="起始时间" class="mydate form-control" style="display: inline; width: 150px;"> 
											<label>结束时间</label>
											<input type="text" id="endTime" onchange="searchEndTime(this)" placeholder="结束时间" class="mydate form-control" style="display: inline; width: 150px;">
										</div>
										<div id="main" style="width: 100%;height:400px; margin-top: 50px;"></div>
									</div> -->
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
$('#sideIndex').attr("class","active");
</script>
<%-- <script src="<%=basePath%>js/statisticalData/echarts.min.js"></script>
<script src="<%=basePath%>js/indexPage/indexChart.js"></script> --%>
</html>