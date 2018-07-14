$(function() {
	let myData = {
		statisticalResultsData : [],
		ready : false,
		unit : {
			type : '单位',
			url : '/jwcpxt/Unit/list_unitDO_byDistributionService',
			optionText : 'unit_name',
			optionValue : 'jwcpxt_unit_id',
			buttonText : '下一步-选择业务',
			allData : [], //所有已有被分配业务的单位
		},
		service : {
			type : '业务',
			url : '/jwcpxt/Service/list_serviceDefinition_all',
			optionText : 'service_definition_describe',
			optionValue : 'jwcpxt_service_definition_id',
			buttonText : '下一步-打分',
			allData : [], //所有业务信息
		},
		unitSelect : [],
		unitSelectIdArr : [],
		serviceSelect : []
	}

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			beginStatistical () {
				selected(myData.unit);
			}
		},
		mounted () {
			//获取所有已有被分配业务的单位
			$.post(myData.unit.url, {}, response => {
				myData.unit.allData = response;
			}, 'json');
			//获取所有的业务
			$.post(myData.service.url, {}, response => {
				myData.service.allData = response;
			}, 'json');
		},
	})


	function selected(option) {
		//--------------------
		let selectedConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '50%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '选择' + option.type,
			content : `
			<div id="selected" style="width:97%;">
				<div class="form-group">
					<select id="statistical" multiple="multiple" size="10"></select>
				</div>
			</div>
			`,
			onContentReady : function() {
				var demo2 = selectedConfirm.$content.find('#statistical').doublebox({
					nonSelectedListLabel : '选择' + option.type,
					selectedListLabel : '统计' + option.type,
					preserveSelectionOnMove : 'moved',
					moveOnSelect : false,
					doubleMove : true,
					optionValue : option.optionValue,
					optionText : option.optionText,
					nonSelectedList : option.allData,
					selectedList : []
				});
			},
			buttons : {
				confirm : {
					text : option.buttonText,
					btnClass : 'btn-blue',
					action : function() {
						let selectedOption = selectedConfirm.$content.find('#statistical').val();
						if (option.type == '单位') {
							myData.unitSelectIdArr = selectedOption;
							selectedOption.forEach((select, i) => {
								myData.unit.allData.forEach((all, j) => {
									if (all.jwcpxt_service_definition_id == select) {
										myData.unitSelect.push(all);
									}
								})
							})
							selected(myData.service);
						} else if (option.type == '业务') {
							selectedOption.forEach((select, i) => {
								myData.service.allData.forEach((all, j) => {
									if (all.jwcpxt_service_definition_id == select) {
										myData.serviceSelect.push(all);
									}
								})
							})
							inputScore();
						}
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					keys : [ 'esc' ],
					action : function() {
						clean();
					}
				}
			},
		});
	}

	//输入分数
	function inputScore() {
		let inputScoreConfirmVue;
		let inputScoreConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '50%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '填写分数',
			content : `
			<div id="inputScoreConfirmSelected" style="width:100%;">
				<template v-for="(service,index) in serviceData">
					<div class="form-group">
						<label>{{service.service_definition_describe}}</label>
						<input type="text" placeholder="请输入业务分数..." class="form-control inputScore"  style="width:240px;"
								:index="index" :serviceid="service.jwcpxt_service_definition_id"
								onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
								onblur="this.v();">
					</div>
				</template>
				<div class="form-group">
					<label>起始时间</label>
					<input id="beginTime" placeholder="起始时间" class="mydate form-control" style="display: inline; width: 150px;">
					<label>结束时间</label>
					<input id="endTime" placeholder="结束时间" class="mydate form-control" style="display: inline; width: 150px;">
				</div>
			</div>
			`,
			onContentReady : function() {
				inputScoreConfirmVue = new Vue({
					el : '#inputScoreConfirmSelected',
					data : {
						serviceData : myData.serviceSelect,
					},
				});
				$.datetimepicker.setLocale('ch');
				$('.mydate').datetimepicker({
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
			onDestroy : function() {
				inputScoreConfirmVue = null;
			},
			buttons : {
				confirm : {
					text : '统计',
					btnClass : 'btn-blue',
					action : function() {
						let scoreData = [];
						let num = 0;
						let flag = false;
						let time = {
							beginTime : inputScoreConfirm.$content.find('#beginTime').val(),
							endTime : inputScoreConfirm.$content.find('#endTime').val()
						}
						inputScoreConfirm.$content.find('.inputScore').each((index, element) => {
							let score_serviceid = {
								serviceid : $(element).attr('serviceid'),
								score : $(element).val(),
							};
							num += Number(score_serviceid.score);
							scoreData[index] = score_serviceid;
						});
						if (num < 100) {
							toastr.error('总分不足100，请检查');
							console.log(num);
						} else if (num > 100) {
							toastr.error('总分超过100，请检查');
							console.log(num);
						} else if (num == 100) {
							flag = true;
						}
						if (flag) {
							let formData = new FormData();
							formData.append('searchTimeStart', time.beginTime); //筛选起始时间
							formData.append('searchTimeEnd', time.endTime); //筛选结束时间
							/*	let params = {
									searchTimeStart : time.beginTime,
									searchTimeEnd : time.endTime,
							};*/
							scoreData.forEach(function(element, i) {
								formData.append('serviceGradeDTOList[' + i + '].service_id', element.serviceid);
								formData.append('serviceGradeDTOList[' + i + '].grade', element.score);
							})
							myData.unitSelectIdArr.forEach(function(element, i) {
								formData.append('unitIds', element);
							})
							/*$.post('/jwcpxt/Statistics/getGradeByCondition', params, response => {
								console.log(response);
							}, 'json');*/
							$.ajax({
								url : '/jwcpxt/Statistics/getGradeByCondition',
								type : 'POST',
								dataType : 'json',
								cache : false,
								data : formData,
								processData : false,
								contentType : false,
								success : response => {
									myData.statisticalResultsData = response.unitHaveServiceGradeDTOList;
									myData.ready = true;
									clean();
								}
							});
						} else {
							return false;
						}
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					keys : [ 'esc' ],
					action : function() {
						clean();
					}
				}
			},
		});
	}

	//清空数据操作
	function clean() {
		//清空这次操作的的数据
		myData.unitSelect = [];
		myData.unitSelectIdArr = [];
		myData.serviceSelect = [];
	}
})