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
						<div class="card" style="text-align: center; height: 300px; padding-top: 70px;">
							<h1>警务测评系统</h1>
							<h3>欢迎来到警务测评系统</h3>
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
</html>