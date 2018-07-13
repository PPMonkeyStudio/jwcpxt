$(function() {
	let myData = {}

	let vm = new Vue({
		el : "#content",
		data : {
			step : 1,
			stemName : '下一步',
		},
		methods : {},
		mounted () {
			var demo2 = $('.statistical-unit').doublebox({
				nonSelectedListLabel : '选择单位',
				selectedListLabel : '统计单位',
				//preserveSelectionOnMove : 'moved',
				//moveOnSelect : false,
				nonSelectedList : [ {
					"roleId" : "1",
					"roleName" : "zhangsan"
				}, {
					"roleId" : "2",
					"roleName" : "lisi"
				}, {
					"roleId" : "3",
					"roleName" : "wangwu"
				} ],
				selectedList : [ {
					"roleId" : "4",
					"roleName" : "zhangsan1"
				}, {
					"roleId" : "5",
					"roleName" : "lisi1"
				}, {
					"roleId" : "6",
					"roleName" : "wangwu1"
				} ],
				optionValue : "roleId",
				optionText : "roleName",
				doubleMove : false,
			});
		},
	})


	function selectedUnit() {
		let selectedUnitConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '40%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '添加一个追问',
			content : `
			<div id="selectedUnit">
				<div class="form-group">
					<select class="statistical-unit" multiple="multiple" size="10"></select>
				</div>
			</div>
			`,
			onContentReady : function() {
				$.post('/jwcpxt/Unit/list_unitDO_byDistributionService', {}, response => {
				}, 'json');
				var demo2 = selectedUnitConfirm.$content.find('.statistical-unit').doublebox({
					nonSelectedListLabel : '选择单位',
					selectedListLabel : '统计单位',
					//preserveSelectionOnMove : 'moved',
					//moveOnSelect : false,
					nonSelectedList : [ {
						"roleId" : "1",
						"roleName" : "zhangsan"
					}, {
						"roleId" : "2",
						"roleName" : "lisi"
					}, {
						"roleId" : "3",
						"roleName" : "wangwu"
					} ],
					selectedList : [ {
						"roleId" : "4",
						"roleName" : "zhangsan1"
					}, {
						"roleId" : "5",
						"roleName" : "lisi1"
					}, {
						"roleId" : "6",
						"roleName" : "wangwu1"
					} ],
					optionValue : "roleId",
					optionText : "roleName",
					doubleMove : false,
				});
			},
			buttons : {
				confirm : {
					text : '确认',
					btnClass : 'btn-info',
					keys : [ 'enter' ],
					action : function() {
						let describe = addOptionConfirm.$content.find('#addInquiriesConfirm_describe').val();
						if (!describe) {
							toastr.error("描述不能为空");
							return false;
						}
						let type = addOptionConfirm.$content.find('#addInquiriesConfirm_type').val();
						if (!describe) {
							toastr.error("描述不能为空");
							return false;
						}
						let params = {
							"question.question_service_definition" : modifyVm.optionData[index].option.jwcpxt_option_id,
							"question.question_describe" : describe,
							"question.question_type" : type
						};
						$.post('/jwcpxt/Question/save_question', params, response => {
							if (response == "1") {
								toastr.success("添加成功");
								//更新信息
								vm.updateOptionInfo(() => {
									modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
								});
							} else if (response == "-1") {
								toastr.error("添加失败");
							}
						}, 'text');
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-blue',
					keys : [ 'esc' ],
					action : function() {}
				}
			},
		});
	}
})