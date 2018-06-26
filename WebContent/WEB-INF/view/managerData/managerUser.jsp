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
a:hover {
	cursor: pointer;
}
</style>
<title>用户信息</title>
</head>
<body>
	<div class="wrapper">
		<!-- 引入侧边栏 -->
		<s:action name="skipSidebar" namespace="/Skip" executeResult="true" />
		<div class="main-panel">
			<!-- 引入导航条  -->
			<s:action name="skipNavbar" namespace="/Skip" executeResult="true" />
			<div class="content">
				<div class="container-fluid">
					<!-- 主内容 -->
					<div id="managerContent" class="row">
						<div class="col-md-12">
							<div class="card" style="padding: 10px;">
								<div class="header">
									<h4 class="title">警员管理</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<button onclick="addUser()" class="btn btn-default">
										<i class="ti-plus"></i>新建一个警员
									</button>
									<div id="loadingLayer" style="margin: 0 auto; width: 45px;">
										<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
									</div>
									<table id="userTable" class="table table-striped"
										style="display: none;">
										<thead>
											<tr>
												<td>账号</td>
												<td>姓名</td>
												<td>所属单位</td>
												<td>修改时间</td>
												<td>状态</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
									<!-- 分页 -->
									<div id="bottomPage" style="padding: 20px;">
										<span>当前页数:<span id="currPage">1</span></span> <span>共:<span id="totalPage">1</span>页
										</span> <span onclick="skipToIndexPage()" id="indexPage"
											class="pageOperation">首页</span> <span
											onclick="skipToPrimaryPage()" id="previousPage"
											class="pageOperation">上一页</span> <span
											onclick="skipToNextPage()" id="nextPage"
											class="pageOperation">下一页</span> <span
											onclick="skipToLastPage()" id="lastPage"
											class="pageOperation">末页</span> <span> <input
											id="skipPage" type="text"
											style="text-align: center; width: 60px; height: 30px;"
											class="queryInput">
											<button onclick="skipToArbitrarilyPage()"
												class="btn btn-default"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">跳转</button>
										</span>
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
</html>