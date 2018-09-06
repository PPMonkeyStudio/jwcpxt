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
<title>警务测评</title>
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
									<h4 class="title">当事人查看</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<%-- <select v-if="isUnit" style="width:120px; float: left;" --%>
									<s:if
										test="(#session.unit!=undefined && #session.unit.unit_grade==1) ||  (#session.user!=undefined &&(#session.user.user_type==2 || #session.user.user_type==1))">
										<select style="width: 140px; float: left;"
											class="form-control" name="clientInfoVO.screenUser"
											@change="queryClient">
											<option value="">选择测评员</option>
											<option v-for="appraisal in allAppraisal"
												:value="appraisal.jwcpxt_user_id">{{appraisal.user_name}}</option>
										</select>
										<select style="width: 120px; float: left; margin-left: 10px;"
											@change="queryClient" name="clientInfoVO.screenClientState"
											class="form-control">
											<option value="">全部</option>
											<option value="满意">满意</option>
											<option value="比较满意">比较满意</option>
											<option value="不太满意">不太满意</option>
											<option value="不满意">不满意</option>
											<option value="不了解">不了解</option>
											<option value="有建议">建议</option>
										</select>
										<div style="float: left; margin-left: 10px;">
											<div class="form-group" style="margin: auto;">
												<label>办理时间</label> <input name="clientInfoVO.startTime"
													@blur="queryClient" class="mydate form-control"
													id="beginTime" placeholder="起始时间"
													style="display: inline; width: 140px;"> <label>到</label>
												<input name="clientInfoVO.endTime" @blur="queryClient"
													id="endTime" placeholder="结束时间" class="mydate form-control"
													style="display: inline; width: 140px;"> <label>回访时间</label>
												<input name="clientInfoVO.startHTime" @blur="queryClient"
													class="mydate form-control" id="beginTime"
													placeholder="起始时间" style="display: inline; width: 140px;">
												<label>到</label> <input name="clientInfoVO.endHTime"
													@blur="queryClient" id="endTime" placeholder="结束时间"
													class="mydate form-control"
													style="display: inline; width: 140px;">

												<button type="button" class="btn btn-default"
													@click="previewChart">
													<i class="fa fa-adjust"></i>图表
												</button>
												
												<button type="button" class="btn btn-default"
													@click="exportClient">
													<i class="fa fa-superpowers"></i>导出不满意当事人
												</button>
											</div>
										</div>
										<input @keyup="queryClient" placeholder="搜索内容(姓名、电话号码、单位名称)"
											name="clientInfoVO.search" class="form-control"
											style="float: right; width: 260px;">
									</s:if>
									<table class="table" style="text-align: center; width: 100%;">
										<thead>
											<tr style="border-bottom: 2px solid #ddd">
												<td style="width: 60px;">姓名</td>
												<td style="width: 60px;">性别</td>
												<td style="width: 60px;">电话</td>
												<td style="width: 60px;"><select class="form-control"
													@change="queryClient" name="clientInfoVO.screenVisit">
														<option value="">全部</option>
														<option value="1">成功</option>
														<option value="2">未回访</option>
														<option value="3">空号</option>
														<option value="4">无人接听</option>
														<option value="5">占线</option>
														<option value="6">停机</option>
														<option value="7">拒访</option>
														<option value="9">关机</option>
														<option value="10">非本人</option>
														<option value="8">其他</option>
												</select></td>
												<td style="width: 80px;"><select class="form-control"
													@change="queryClient" name="clientInfoVO.screenService">
														<option value="">全部业务</option>
														<option v-for="service in allService"
															:value="service.jwcpxt_service_definition_id">{{service.service_definition_describe}}</option>
												</select></td>
												<td style="width: 150px;">办理单位</td>
												<td style="width: 60px;">办理时间</td>
												<td style="width: 60px;">回访时间</td>
												<s:if test="#session.user.user_type==1">
													<td style="width: 60px;">操作</td>
												</s:if>
											</tr>
										</thead>
										<tbody v-cloak>
											<template v-for="(ClientInfoDTO,index) in clientInfoVO">
											<tr style="border-top: 1px solid #ddd;">
												<s:if
													test="(#session.unit!=undefinded && #session.unit.unit_grade==1) || (#session.user!=undefinded && #session.loginType=='user')">
													<th><a href="javascript:;"
														@click="showClientInfomation($event)"
														:id="ClientInfoDTO.serviceClient.jwcpxt_service_client_id">
															<span
															v-html="ClientInfoDTO.serviceClient.service_client_name"></span>
													</a></th>
												</s:if>
												<s:else>
													<th><span
														v-html="ClientInfoDTO.serviceClient.service_client_name"></span>
													</th>
												</s:else>
												<td>{{ClientInfoDTO.serviceClient.service_client_sex=='1'?'男':'女'}}</td>
												<td
													v-html="ClientInfoDTO.serviceClient.service_client_phone"></td>
												<td><span
													v-if="ClientInfoDTO.serviceClient.service_client_visit==1"
													class="label label-primary">成功</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==2"
													class="label label-info">未回访</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==3"
													class="label label-warning">空号</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==4"
													class="label label-warning">无人接听</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==5"
													class="label label-warning">占线</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==6"
													class="label label-warning">停机</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==7"
													class="label label-warning">拒访</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==8"
													class="label label-warning">其他</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==9"
													class="label label-warning">关机</span> <span
													v-else-if="ClientInfoDTO.serviceClient.service_client_visit==10"
													class="label label-warning">非本人</span> <span v-else
													class="label label-warning">未定义</span></td>
												<td>{{ClientInfoDTO.serviceDefinition.service_definition_describe}}</td>
												<td
													v-html="ClientInfoDTO.unit.unit_name.replace('江西省萍乡市公安局','')"></td>
												<td>{{ClientInfoDTO.serviceInstance.service_instance_date}}</td>
												<td><template
														v-if="ClientInfoDTO.serviceClient.service_client_visit==2">
													{{ClientInfoDTO.serviceClient.service_client_gmt_modified}}
													</template> <template v-else>
													{{ClientInfoDTO.serviceClient.service_client_gmt_modified}}
													</template></td>
												<s:if test="#session.user.user_type==1">
													<template
														v-if="ClientInfoDTO.serviceClient.service_client_visit!=1">
													<td><a href="javascript:;"
														@click="pageTo(ClientInfoDTO.serviceDefinition.jwcpxt_service_definition_id,ClientInfoDTO.serviceClient.jwcpxt_service_client_id)">业务回访</a>
													</td>
													</template>
													<template v-else>
													<td><span>暂无操作</span></td>
													</template>
												</s:if>
											</tr>
											</template>
										</tbody>
									</table>
									<!-- 分页 -->
									<div id="bottomPage" style="padding: 20px;" v-cloak>
										<span>总记录数:<span id="currPage">{{page.totalCount}}</span></span>
										<span>当前页数:<span id="currPage">{{page.currPage}}</span></span>
										<span>共:<span id="totalPage">{{page.totalPage}}</span>页
										</span> <span class="pageOperation" @click="firstPage">首页</span> <span
											class="pageOperation" @click="prePage">上一页</span> <span
											class="pageOperation" @click="nextPage">下一页</span> <span
											class="pageOperation" @click="lastPage">末页</span> <span>
											<input type="text" id="toPageInput"
											style="text-align: center; width: 60px; height: 30px;"
											onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)"
											onblur="this.v();">
											<button class="btn btn-default" @click="toPage"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">跳转</button>
										</span> <span>
											<button class="btn btn-default"
												@click="reviewSituation($event,'requer')"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">回访信息</button>
											<button class="btn btn-default"
												@click="reviewSituation($event,'auquan')"
												style="height: 30px; vertical-align: middle; margin-bottom: 3px;">整改信息</button>
											<P></P>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<form id="exportForm"></form>
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
<script src="<%=basePath%>js/evaluationPolice/clientInformation.js"></script>
</html>