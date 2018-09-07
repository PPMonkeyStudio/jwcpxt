$(function() {
	let myData = {
		ready : false,
		clientInfoVO : [],
		page : {
			currPage : 1,
			totalPage : 1,
			totalCount : 11,
			pageSize : 10,
			haveNextPage : false,
			havePrePage : false,
			isFirstPage : false,
			isLastPage : false
		},
		isUnit : false,
		allAppraisal : [],
		allService : [],
		allUnit : [],
		screenUser_chart : ''
	};

	var MounthFristDay = function() {
		let date_ = new Date();
		let mounth = (date_.getMonth() + 1).length > 1 ? (date_.getMonth() + 1) : '0' + (date_.getMonth() + 1);
		let dataVa = date_.getFullYear() + '-' + mounth + '-01';
		return dataVa;
	}();
	
	let queryData = {
		"clientInfoVO.currPage" : 1,
		"clientInfoVO.startTime" : '',
		"clientInfoVO.endTime" : '',
		"clientInfoVO.startHTime" : MounthFristDay,
		"clientInfoVO.endHTime" : '',
		"clientInfoVO.screenService" : '',
		"clientInfoVO.screenVisit" : '',
		"clientInfoVO.screenUser" : '',
		"clientInfoVO.screenClientState" : '',
		"clientInfoVO.search" : ''
	}

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			before () {
				// 业务id
				let definitionId = getUrlParam('definitionId');
				if (definitionId) {
					// 选中传过来的业务ID
					$('input[name="clientInfoVO.screenService"]').val(definitionId);
					queryData["clientInfoVO.screenService"] = definitionId;
				}
				// (各种)搜索情况
				let unitText = getUrlParam('unitId');
				if (unitText) {
					// 选中传过来的(各种)信息
					$('input[name="clientInfoVO.search"]').val(unitText);
					queryData["clientInfoVO.search"] = unitText;
				}

				$.post('/jwcpxt/LoginAndLogout/getCurrentUser', {}, response => {
					if (response.jwcpxt_unit_id) {
						myData.isUnit = true;
					// 获取所有测评员
					} else if (response.jwcpxt_user_id) {
						// queryData["clientInfoVO.screenUser"] =
						// response.jwcpxt_user_id;
						myData.screenUser_chart = response.jwcpxt_user_id;
					}
					// 查询所有的测评员
					$.post('/jwcpxt/Service/list_userDO', {}, response => {
						myData.allAppraisal = response;
					}, 'json')
					this.getInfo(queryData);
				}, 'json');
			},
			getInfo (pramas) {
				$.post('/jwcpxt/Service/get_clientInfoVO_byUserId', pramas, response => {
					myData.clientInfoVO = response.listClientInfoDTO;
					myData.page.currPage = response.currPage;
					myData.page.totalPage = response.totalPage;
					myData.page.totalCount = response.totalCount;
					myData.page.pageSize = response.pageSize;
					this.pageInit(response);
					myData.ready = true;

					//加快页面数据显示
					// 获取所有的业务
					$.post('/jwcpxt/Service/list_serviceDefinition_all', {}, response => {
						myData.allService = response;
					}, 'json');
					// 获取所有的单位
					$.post('/jwcpxt/Unit/list_unitDO_all', {}, response => {
						myData.allUnit = response;
					}, 'json');

				}, 'json');
			},
			pageInit (response) {
				myData.page.haveNextPage = response.currPage < response.totalPage;
				myData.page.havePrePage = response.currPage > 1;
				myData.page.isFirstPage = response.currPage == 1;
				myData.page.isLastPage = response.currPage == response.totalPage;
			},
			queryClient ($event) {
				queryData[$event.target.name] = $($event.target).val();
				queryData["clientInfoVO.currPage"] = 1;
				this.getInfo(queryData);
			},
			showClientInfomation (event) {
				showClientInformation($(event.target).parents('a').attr('id'));
			},
			previewChart () {
				previewChart();
			},
			pageTo (definition_id, client_id) {
				window.location.href = `/jwcpxt/Skip/skipPoliceAssessmentPage?type=specified&definitionId=${definition_id}&serviceClientId=${client_id}`;
			},
			reviewSituation ($event, type) {
				$.post('/jwcpxt/Statistics/downloadData', {
					"startTime" : queryData["clientInfoVO.startHTime"],
					"endTime" : queryData["clientInfoVO.endHTime"],
					"userId" : queryData["clientInfoVO.screenUser"],
					"flag" : type
				}, response => {
					function toPercent(point) {
						var str = Number(point * 100).toFixed(2);
						str += "%";
						return str;
					}
					let sucess = toPercent(response.totalSuccessCount / response.totalCount);
					let statis = toPercent(response.totalStatisCount / response.totalSuccessCount);
					if (type == "auquan") {
						$($event.target)
							.siblings('p')
							.html("整改总数:" + response.totalCount + ",成功总数:" + response.totalSuccessCount + ",满意总数:" + response.totalStatisCount + ",整改成功率为:" + sucess + ",满意率为:" + statis);
					} else {
						$($event.target)
							.siblings('p')
							.html("回访总数:" + response.totalCount + ",成功总数:" + response.totalSuccessCount + ",满意总数:" + response.totalStatisCount + ",回访成功率为:" + sucess + ",满意率为:" + statis);
					}
				}, 'json');
			},
			exportClient () {
				exportUnClient();
			},
			firstPage () {
				if (myData.page.isFirstPage) {
					toastr.error("已经是在首页了哦~");
					return;
				}
				queryData["clientInfoVO.currPage"] = 1;
				this.getInfo(queryData)
			},
			prePage () {
				if (!myData.page.havePrePage) {
					toastr.error("没有上一页了哦~");
					return;
				}
				queryData["clientInfoVO.currPage"] -= 1;
				this.getInfo(queryData)
			},
			nextPage () {
				if (!myData.page.haveNextPage) {
					toastr.error("没有下一页了哦~");
					return;
				}
				queryData["clientInfoVO.currPage"] += 1;
				this.getInfo(queryData)
			},
			lastPage () {
				if (myData.page.isLastPage) {
					toastr.error("已经是在尾页了哦~");
					return;
				}
				queryData["clientInfoVO.currPage"] = myData.page.totalPage;
				this.getInfo(queryData)
			},
			toPage () {
				let pageIndex = $('#toPageInput').val();
				if (pageIndex < 1 || pageIndex > myData.page.totalPage) {
					toastr.error("输入的数字不在页数范围内,请检查页码");
					return;
				}
				queryData["clientInfoVO.currPage"] = pageIndex;
				this.getInfo(queryData)
			},
		},
		mounted () {
			this.before();
			$('input[name="clientInfoVO.startHTime"]').val(MounthFristDay);
		},
	})

	function showClientInformation(clientId) {
		let showClientInformationConfirm = $.confirm({
			title : '当事人信息',
			type : 'dark',
			boxWidth : '1000px',
			useBootstrap : false,
			content : `
				<form id="showClientInformationConfirmForm">
				  <div class="form-group" v-cloak>
					  <table class="table table-bordered">
					  	<tbody>
					  		<tr>
					  			<th colspan="8">业务当事人信息</th>
					  		</tr>
					  		<tr>
					  			<td>名字</td>
					  			<td>{{DTO.client.service_client_name}}</td>
								<td>性别</td>
								<td>{{DTO.client.service_client_sex=='1'?'男':'女'}}</td>
								<td>电话号码</td>
								<td>{{DTO.client.service_client_phone}}</td>
								<td>测评员</td>
								<td>{{DTO.user.user_name}}</td>
					  		</tr>
					  		<tr>
					  			<th colspan="8">办理业务信息</th>
					  		</tr>
					  		<template v-if="DTO.instance.service_instance_service_definition == 'revisit'">
						  		<tr>
						  			<td>业务名称</td>
									<td>{{DTO.definition.service_definition_describe}}</td>
									<td>原整改编号</td>
									<td>{{DTO.instance.service_instance_nid}}</td>
									<td>原业务名称</td>
									<td>{{DTO.instance.service_instance_old_service_name}}</td>
									<td>原整改时间</td>
									<td>{{DTO.instance.service_instance_date}}</td>
						  		</tr>
					  		</template>
					  		<template v-else>
						  		<tr>
						  			<td>业务名称</td>
									<td colspan="3">{{DTO.definition.service_definition_describe}}</td>
									<td colspan="2">办理时间</td>
									<td colspan="2">{{DTO.instance.service_instance_date}}</td>
						  		</tr>
					  		</template>
					  		<tr>
					  			<th colspan="8">办理单位信息</th>
					  		</tr>
					  		<tr>
					  			<td>单位名称</td>
								<td>{{DTO.unit.unit_name}}</td>
								<td>单位机构代码</td>
								<td>{{DTO.unit.unit_num}}</td>
								<td>单位联系人</td>
								<td>{{DTO.unit.unit_contacts_name}}</td>
								<td>联系人电话</td>
								<td>{{DTO.unit.unit_phone}}</td>
					  		</tr>
					  		<tr>
					  			<th colspan="8">测评问卷信息</th>
					  		</tr>
					  		<template v-for="(QusetionAndOption,index) in DTO.QusetionAndOptionDTO">
						  		<tr>
						  			<td colspan="4">{{QusetionAndOption.question.question_describe}}</td>
									<td colspan="3">{{QusetionAndOption.answer}}</td>
									<td>
										<template v-if="QusetionAndOption.askQusetionAndOptionDTO">
										<a v-if="QusetionAndOption.askQusetionAndOptionDTO.length>0" href="javascript:;" @click="showAskQuestion(index)">查看追问</a>
										</template>
									</td>
						  		</tr>
					  		</template>
					  	</tbody>
					  </table>
				  </div>
				</form>
			`,
			onContentReady : function() {
				$.post('/jwcpxt/Service/get_AllInformation_ByClientId', {
					"serviceClient.jwcpxt_service_client_id" : clientId
				}, response => {
					new Vue({
						el : '#showClientInformationConfirmForm',
						data : {
							DTO : response
						},
						methods : {
							showAskQuestion (index) {
								showAskQuestion(this.DTO.QusetionAndOptionDTO[index]);
							}
						},
					});
				}, 'json');
			},
			buttons : {
				cancel : {
					text : '关闭',
					btnClass : 'btn-blue'
				}
			},
		})
	}

	function showAskQuestion(params) {
		let showAskQuestionConfirm = $.confirm({
			title : '追问问题信息',
			type : 'dark',
			boxWidth : '500px',
			useBootstrap : false,
			content : `
				<form id="showAskQuestionConfirmForm">
				  <div class="form-group" v-cloak>
					  <table class="table table-bordered">
					  	<tbody>
					  		<template v-for="(QusetionAndOption) in AskDTO.askQusetionAndOptionDTO">
						  		<tr>
						  			<td>{{QusetionAndOption.question.question_describe}}</td>
						  		</tr>
						  		<tr>
						  			<td>{{QusetionAndOption.answer}}</td>
						  		</tr>
					  		</template>
					  	</tbody>
					  </table>
				  </div>
				</form>
			`,
			onContentReady : function() {
				new Vue({
					el : '#showAskQuestionConfirmForm',
					data : {
						AskDTO : params
					},
				});
			},
			buttons : {
				cancel : {
					text : '关闭',
					btnClass : 'btn-blue'
				}
			},
		})
	}

	// 查看图表
	function previewChart() {
		// queryData
		let params = {
			"returnVisitVO.userId" : myData.screenUser_chart ? myData.screenUser_chart : "",
			"returnVisitVO.startHTime" : queryData["clientInfoVO.startHTime"] ? queryData["clientInfoVO.startHTime"] : getFormatDate(),
			"returnVisitVO.endHTime" : queryData["clientInfoVO.endHTime"] ? queryData["clientInfoVO.endHTime"] : getFormatDate()
		}
		// 如果测评员搜索不为空
		if (queryData["clientInfoVO.screenUser"]) {
			params["returnVisitVO.userId"] = queryData["clientInfoVO.screenUser"];
		}
		let previewChartConfirm = $.confirm({
			title : '回访数量统计',
			type : 'dark',
			boxWidth : '1000px',
			useBootstrap : false,
			content : `
			<form id="previewChartConfirmForm">
				  <div id="previewChartConfirmFormChart" style="width: 90%;height:400px;"></div>
			</form>
			`,
			onContentReady : function() {
				$.post('/jwcpxt/Statistics/getUserCountVO', params, response => {
					let dataText = [];
					let dataSeries = [];
					response.listReturnVisitDTO.forEach(function(elt, i) {
						let params = {
							value : elt.returnCount,
							name : typeReplace(elt.returnVisitType)
						}
						dataSeries.push(params);
						dataText.push(typeReplace(elt.returnVisitType));
					})
					new Vue({
						el : '#previewChartConfirmForm',
						data : {
							countDTO : response
						},
						mounted () {
							let option = {
								title : {
									text : '回访数据统计图表',
									subtext : '',
									x : 'center'
								},
								tooltip : {
									trigger : 'item',
									formatter : "{a} <br/>{b} : {c} ({d}%)"
								},
								legend : {
									orient : 'vertical',
									left : 'left',
									data : dataText
								},
								series : [
									{
										name : '访问来源',
										type : 'pie',
										radius : '55%',
										center : [ '50%', '60%' ],
										data : dataSeries,
										itemStyle : {
											emphasis : {
												shadowBlur : 10,
												shadowOffsetX : 0,
												shadowColor : 'rgba(0, 0, 0, 0.5)'
											}
										}
									}
								]
							};
							echarts.init(document.getElementById('previewChartConfirmFormChart'), 'light').setOption(option); // 群众不满意
						}
					});
				}, 'json');
			},
			buttons : {
				cancel : {
					text : '关闭',
					btnClass : 'btn-blue'
				}
			},
		})
	}

	//导出不满意当事人
	function exportUnClient() {
		//allAppraisal : [];
		//allService : [];
		let appraisalOption = function() {
			let str = "";
			myData.allAppraisal.forEach(function(elt, i) {
				str += `<option value="${elt.jwcpxt_user_id}">${elt.user_name}</option>`;
			})
			return str;
		}();

		let serviceOption = function() {
			let str = "";
			myData.allService.forEach(function(elt, i) {
				str += `<option value="${elt.jwcpxt_service_definition_id}">${elt.service_definition_describe}</option>`;
			})
			return str;
		}();

		let unitOption = function() {
			let str = "";
			myData.allUnit.forEach(function(elt, i) {
				str += `<option value="${elt.jwcpxt_unit_id}">${elt.unit_name}</option>`;
			})
			return str;
		}();
		
		let exportUnClientConfirm = $.confirm({
			title : '导出条件',
			type : 'dark',
			boxWidth : '500px',
			useBootstrap : false,
			smoothContent : false, //关闭动画
			content : `
			<form id="exportUnClientConfirm">
				<div class="form-group">
					<label>回访时间</label>
						<div class="form-group">
							<input type="text" class="form-control mydate" id="screenTimeStart" style="width:50%; float:left;" placeholder="起始时间">
							<input type="text" class="form-control mydate" id="screenTimeEnd" style="width:50%" placeholder="结束时间">
						</div>
				</div>
				<div class="form-group">
					<label>单位</label>
						<div class="form-group">
							<select class="form-control" id="screenUnit">
								<option value="">全部单位</option>
								${unitOption}
							</select>
						</div>
				</div>
				<div class="form-group">
					<label>业务</label>
						<div class="form-group">
							<select class="form-control" id="screenDefinitionId">
								<option value="">全部业务</option>
								${serviceOption}
							</select>
						</div>
				</div>
				<div class="form-group">
					<label>测评员</label>
						<div class="form-group">
							<select class="form-control" id="screenJudge">
								<option value="">全部测评员</option>
								${appraisalOption}
							</select>
						</div>
				</div>
			</form>
			`,
			onContentReady : function() {
				exportUnClientConfirm.$content.find('.mydate').datetimepicker({
					pickerPosition : "top-right",
					yearStart : 1900, // 设置最小年份
					yearEnd : 2050, // 设置最大年份
					yearOffset : 0, // 年偏差
					timepicker : false, // 关闭时间选项
					format : 'Y-m-d', // 格式化日期年-月-日
					minDate : '1900/01/01', // 设置最小日期
					maxDate : '2050/01/01', // 设置最大日期
				});
				setTimeout(function() {
					$('.xdsoft_datetimepicker').css('z-index', 99999999);
				}, 0);
			},
			buttons : {
				exp : {
					text : '导出',
					btnClass : 'btn-blue',
					action : function() {
						let form = $('#exportForm');
						//先清空表单
						form.empty();
						form.attr('action', '/jwcpxt/Statistics/exportDeductExcel');
						form.append($(`<input type="hidden" name="deductMarkInfoVO.screenTimeStart" value="${exportUnClientConfirm.$content.find("#screenTimeStart").val()}">`));
						form.append($(`<input type="hidden" name="deductMarkInfoVO.screenTimeEnd" value="${exportUnClientConfirm.$content.find("#screenTimeEnd").val()}">`));
						form.append($(`<input type="hidden" name="deductMarkInfoVO.screenUnit" value="${exportUnClientConfirm.$content.find("#screenUnit").val()}">`));
						form.append($(`<input type="hidden" name="deductMarkInfoVO.screenDefinitionId" value="${exportUnClientConfirm.$content.find("#screenDefinitionId").val()}">`));
						form.append($(`<input type="hidden" name="deductMarkInfoVO.screenJudge" value="${exportUnClientConfirm.$content.find("#screenJudge").val()}">`));
						form.submit(); //自动提交
					}
				},
				cancel : {
					text : '关闭',
					btnClass : 'btn-default'
				}
			},
		})
	}


	randerTimeUtil();
	function randerTimeUtil() {
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
	}

	function getFormatDate() {
		var nowDate = new Date();
		var year = nowDate.getFullYear();
		var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
		var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
		// var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() :
		// nowDate.getHours();
		// var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() :
		// nowDate.getMinutes();
		// var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() :
		// nowDate.getSeconds();
		return year + "-" + month + "-" + date;
	}

	function typeReplace(type) {
		switch (type) {
		case '1':
			return "成功";			break;
		case '2':
			return "未回访";			break;
		case '3':
			return "空号";			break;
		case '4':
			return "无人接听";			break;
		case '5':
			return "占线";			break;
		case '6':
			return "停机";			break;
		case '7':
			return "拒访 ";			break;
		case '8':
			return "其他";			break;
		case '9':
			return "关机";
		case '10':
			return "非本人";
		default:
			break;
		}
	}

	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return decodeURI(r[2]);
		return null;
	}
})