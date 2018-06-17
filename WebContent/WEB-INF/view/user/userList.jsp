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
	
<title>用户信息</title>
</head>
<body>
<s:action name="User_navbar" namespace="/user" executeResult="true" />
<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">用户管理</h3>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">

						<!--  -->
						<div class="panel-body">
							<div style="height: 34px; width: 100%;">
								<div style="width: 150px; float: left; margin: 0 20px 0 0">
									<button class="btn btn-default role_one"
										onclick="createPolice()">
										<i class="fa fa-plus-square"></i> 新增用户
									</button>

								</div>
								<!-- 检索 -->
								<div class="input-group" style="width: 300px; float: right;">
									<input id="input_PoliceSearchText" class="form-control" oninput="List_Police_By_Page(1);" type="text" placeholder="搜索警号、姓名或电话号码" />
									<span class="input-group-addon" style="border-radius: unset;">
										<i class="fa fa-search"></i>   
									</span>
								</div>

							</div>

							<table id="table_police" class="table table-hover "
								style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
										<th>序号</th>
										<th>账号</th>
										<th>姓名</th>
										<th>所属单位</th>
										<th>权限</th>
										<th>状态</th>
										<th>操作</th>
										<th><label class="fancy-checkbox"> <input
												id="checkbox_all_select" type="checkbox"
												onclick="all_select()"> <span></span>
										</label></th>
									</tr>
								</tbody>
							</table>
							<!-- 加载图标 -->
							<div id="i_pulse" style="text-align: center;">
								<i class="fa fa-spinner fa-pulse fa-3x"></i>
							</div>
							<!-- 删除按钮 -->
							<div style="height: 34px;display:none;" id="btn_delete" >
								<button class="btn btn-danger role_one" onclick="deletePolice()"
									style="float: right; margin: 0 10px;">
									<i class="fa fa-trash-o"></i> 删除所选
								</button>
							</div>
							<!--翻页  -->
							<div id="page_flip"
								style="margin: 0 auto; width: 400px; text-align: center;display:none;">
								<span> <a onclick="flip(1)"><i
										class="fa fa-angle-double-left">首页</i> </a> &nbsp&nbsp <a
									onclick="flip(2)"><i class="fa fa-angle-left"></i>上一页 </a>
									&nbsp&nbsp <a onclick="flip(3)">下一页<i
										class="fa fa-angle-right"></i>
								</a> &nbsp&nbsp <a onclick="flip(4)">尾页<i
										class="fa fa-angle-double-right"></i>
								</a> <br />
									<p class='info' style="margin-top: 5px;">
										第<span id="span_pageIndex">1</span>页&nbsp&nbsp共 <span
											id="span_totalPages">1</span>页&nbsp&nbsp共 <span
											id="span_totalRecords">0</span>条记录

									</p></span>

							</div>

						</div>
					</div>
					<!-- END TABLE HOVER -->
				</div>

			</div>
		</div>
	</div>
</body>
</html>