$(function() {
	let myData = {
		definitionId : getUrlParam('definitionId'),
		ready : false,
		questionVO : {},
		page : {
			currPage : 1,
			totalPage : 1,
			totalCount : 11,
			pageSize : 10,
			haveNextPage : false,
			havePrePage : false,
			isFirstPage : false,
			isLastPage : false,
		},
		addQuestionModalData : {
			question_describe : "",
			question_type : "",
		},
		checkQuestionModalData : {
			question : {
				jwcpxt_question_id : '',
				question_describe : "",
				question_type : ""
			}
		}
	};

	let queryData = {
		"questionVO.currPage" : 1,
		"questionVO.serviceDefinitionDTO.serviceDefinition.jwcpxt_service_definition_id" : myData.definitionId,
		"questionVO.screenType" : '',
		"questionVO.screenSearch" : '',
	}

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			getInfo (pramas) {
				$.post('/jwcpxt/Question/get_questionVO', pramas, response => {
					myData.questionVO = response;
					myData.page.currPage = response.currPage;
					myData.page.totalPage = response.totalPage;
					myData.page.totalCount = response.totalCount;
					myData.page.pageSize = response.pageSize;
					this.pageInit(response);
					myData.ready = true;
				}, 'json');
			},
			pageInit (response) {
				this.page.haveNextPage = response.currentPage < response.totalPage;
				this.page.havePrePage = response.currentPage > 1;
				this.page.isFirstPage = response.currentPage != 1;
				this.page.isLastPage = response.currentPage != response.totalPage;
			},
			addQuestion () {
				if (!myData.addQuestionModalData.question_describe) {
					toastr.error('未填写问题描述,请填写完整');
					return;
				}
				if (!myData.addQuestionModalData.question_type) {
					toastr.error('未选中问题类型,请选择类型');
					return;
				}
				$.post('/jwcpxt/Question/save_question', {
					"question.question_describe" : myData.addQuestionModalData.question_describe,
					"question.question_type" : myData.addQuestionModalData.question_type,
					"question.question_service_definition" : myData.definitionId,
				}, response => {
					if (response == "1") {
						toastr.success('添加成功');
						/*刷新页面信息,重信息获取第一页信息*/
						queryData["questionVO.currPage"] = 1;
						this.getInfo(queryData);
						$('#addQuestionModal').modal('hide');
					} else if (response == "-1") {
						toastr.error('添加失败');
					}
				}, 'json');
			},
			updateOptionInfo (after) {
				/*更新数据*/
				$.post('/jwcpxt/Question/get_questionDTO_byQuestionId', {
					"question.jwcpxt_question_id" : myData.checkQuestionModalData.question.jwcpxt_question_id
				}, response => {
					myData.checkQuestionModalData = response;
					//重新渲染页面的作用，必须放在异步回调函数里
					after(); //作为参数传入
				}, 'json');
			},
			modifyQuestion (index) {
				/*修改数据*/
				$.post('/jwcpxt/Question/get_questionDTO_byQuestionId', {
					"question.jwcpxt_question_id" : myData.questionVO.questionList[index].jwcpxt_question_id
				}, response => {
					myData.checkQuestionModalData = response;
					$('#checkQuestionModal').modal('show');
				}, 'json');
			},
			modifyOpintion () {
				showOptions(); //显示所有的选项
			},
			modifyDescribe () {
				$.post('/jwcpxt/Question/update_question', {
					"question.jwcpxt_question_id" : myData.checkQuestionModalData.question.jwcpxt_question_id,
					"question.question_describe" : myData.checkQuestionModalData.question.question_describe
				}, response => {
					if (response == "1") {
						toastr.success("修改成功");
						$('#checkQuestionModal').modal('hide');
						/*留在本页面*/
						queryData["questionVO.currPage"] = myData.page.currPage;
						this.getInfo(queryData)
					} else if (response == "-1") {
						toastr.error("修改失败");
					}
				}, 'text');
			},
			moveQuestion (index, type) {
				let questionId = myData.questionVO.questionList[index].jwcpxt_question_id;
				$.post('/jwcpxt/Question/move_question_sort', {
					"question.jwcpxt_question_id" : questionId,
					"moveQuestionType" : type
				}, response => {
					if (response == "1") {
						if (type == 1) {
							toastr.success("上移成功");
						} else if (type == 2) {
							toastr.success("下移成功");
						}
						/*留在本页面*/
						queryData["questionVO.currPage"] = myData.page.currPage;
						this.getInfo(queryData)
					} else if (response == "-1") {
						if (type == 1) {
							toastr.error("上移失败");
						} else if (type == 2) {
							toastr.error("下移失败");
						}
					}
				}, 'text');
			},
			deleteQuestion (index) {
				//使用删除接口
				deleteInterface('/jwcpxt/Question/delete_question', {
					"question.jwcpxt_question_id" : myData.questionVO.questionList[index].jwcpxt_question_id
				});
			},
			queryQuestion ($event) {
				queryData[$event.target.name] = $event.target.value;
				this.getInfo(queryData);
			},
			firstPage () {
				if (myData.page.isFirstPage) {
					toastr.error("已经是在首页了哦~");
					return;
				}
				queryData["questionVO.currPage"] = 1;
				this.getInfo(queryData)
			},
			prePage () {
				if (!myData.page.havePrePage) {
					toastr.error("没有上一页了哦~");
					return;
				}
				queryData["questionVO.currPage"] -= 1;
				this.getInfo(queryData)
			},
			nextPage () {
				if (!myData.page.haveNextPage) {
					toastr.error("没有下一页了哦~");
					return;
				}
				queryData["questionVO.currPage"] += 1;
				this.getInfo(queryData)
			},
			lastPage () {
				console.log(myData.page.isLastPage);
				if (myData.page.isLastPage) {
					toastr.error("已经是在尾页了哦~");
					return;
				}
				queryData["questionVO.currPage"] = myData.page.totalPage;
				this.getInfo(queryData)
			},
			toPage () {
				let pageIndex = $('#toPageInput').val();
				if (pageIndex < 1 || pageIndex > myData.page.totalPage) {
					toastr.error("输入的数字不在页数范围内,请检查页码");
					return;
				}
				queryData["questionVO.currPage"] = pageIndex;
				this.getInfo(queryData)
			},
		},
		mounted () {
			this.getInfo(queryData);
			$('#addQuestionModal').on('hidden.bs.modal', function(e) {
				myData.addQuestionModalData.question_describe = '';
				myData.addQuestionModalData.question_type = '';
			})
		},
	})

	/* 查看选择题的选项和追问 */
	function showOptions() {
		let questionId = myData.checkQuestionModalData.question.jwcpxt_question_id;
		let modifyVm; //必须作为全局变量，否则在button里无法获取;
		let con = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			typeAnimated : true, //未知。。。。
			boxWidth : '50%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			offsetTop : 10, //设置距离浏览器高度
			title : '修改选项',
			content : `
			<div id="modifyOption">
				<table style="width:100%; text-align: center;">
					<thead>
						<tr style="border-bottom: 2px solid #ddd">
							<th style="width:40px;">追问</th>
							<th style="width:40px;">序号</th>
							<th>选项描述</th>
							<th style="width:40px;">分数</th>
							<th style="width:160px;">选项操作</th>
						</tr>
					</thead>
					<tbody>
						<template v-for="(optionDTO,index) in optionData">
							<tr style="border-top: 1px solid #ddd;">
								<td><a data-toggle="collapse" :href="'#collapse'+index" @click="transform"><i class="fa fa-caret-right"></i></a></td>
								<th>{{optionDTO.option.option_sort}}</th>
								<td>{{optionDTO.option.option_describe}}</td>
								<td>{{optionDTO.option.option_grade}}</td>
								<td>
									<i title="修改选项" class="ti-pencil-alt" @click="modifyOption(index)"></i>&nbsp; 
									<i title="上移" class="fa fa-arrow-up" @click="moveOption(index,1)"></i>&nbsp;
									<i title="下移" class="fa fa-arrow-down" @click="moveOption(index,2)"></i>&nbsp;
									<i title="删除选项" class="ti-trash" @click="deleteOption(index)"></i>&nbsp;
									<i title="添加追问" class="fa fa-plus-square-o" @click="addInquiries(index)"></i>&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div :id="'collapse'+index" class="panel-collapse collapse">
										<div class="panel-body">
											<table style="width:100%; text-align: center;" >
												<thead>
													<tr style="border-bottom: 2px solid #ddd">
														<th style="width:40px;"></th>
														<th style="width:40px;">序号</th>
														<th>问题描述</th>
														<th style="width:120px;">问题类别</th>
														<th style="width:160px;">追问操作</th>
													</tr>
												</thead>
												<tbody>
													<template v-for="(inquiriesOptionDTO,index1) in optionDTO.listInquiriesOptionDTO">
														<tr style="border-top: 1px solid #ddd;">
															<td></td>
															<th>{{inquiriesOptionDTO.inquiriesQuestion.question_sort}}</th>
															<td>{{inquiriesOptionDTO.inquiriesQuestion.question_describe}}</td>
															<td>
																<span v-if="inquiriesOptionDTO.inquiriesQuestion.question_type==4"class="label label-primary">选择题</span> 
																<span v-else-if="inquiriesOptionDTO.inquiriesQuestion.question_type==3" class="label label-info">主观题</span>
																<span v-else class="label label-warning">未定义</span>
															<td>
																<i class="ti-pencil-alt" @click="modifyInquiries(index,index1)"></i>&nbsp; 
																<i class="fa fa-arrow-up" @click="moveInquiries(index,index1,1)"></i>&nbsp;
																<i class="fa fa-arrow-down" @click="moveInquiries(index,index1,2)"></i>&nbsp;
																<i class="ti-trash" @click="deleteInquiries(index,index1)"></i>&nbsp;
															</td>
														</tr>
													</template>
												</tbody>
										</div>
									</div>
								</td>
							</tr>
						</template>
					</tbody>
				</table>
			</div>
			`,
			onContentReady : function() {
				modifyVm = new Vue({
					el : '#modifyOption',
					data : {
						optionData : myData.checkQuestionModalData.listOptionDTO
					},
					methods : {
						transform ($event) {
							let i_class = $event.target.className;
							$event.target.className = i_class == "fa fa-caret-right" ? "fa fa-caret-down" : "fa fa-caret-right";
						},
						//-----------------------------------------------------------------------------------------------------
						addInquiries (index) {
							addInquiries(index, modifyVm);
						},
						//选项的事件start
						modifyOption (index) {
							modifyOptionDescribeOrGrade(this.optionData[index], modifyVm);
						},
						moveOption (index, type) {
							let optionId = this.optionData[index].option.jwcpxt_option_id;
							$.post('/jwcpxt/Question/move_option', {
								"option.jwcpxt_option_id" : optionId,
								"moveOptionType" : type
							}, response => {
								if (response == "1") {
									if (type == 1) {
										toastr.success("上移成功");
									} else if (type == 2) {
										toastr.success("下移成功");
									}
									//更新信息///////----将方法作为参数传递过去
									vm.updateOptionInfo(() => {
										modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
									});
								} else if (response == "-1") {
									if (type == 1) {
										toastr.error("上移失败");
									} else if (type == 2) {
										toastr.error("下移失败");
									}
								}
							}, 'text');
						},
						deleteOption (index) {
							//使用删除的接口工具
							// url后台接口
							// params删除的信息的id
							// function数据更新，重新渲染页面
							deleteInterface('/jwcpxt/Question/delete_option', {
								"option.jwcpxt_option_id" : this.optionData[index].option.jwcpxt_option_id
							}, () => {
								this.optionData = myData.checkQuestionModalData.listOptionDTO;
							});
						}, //选项的事件end



						//---------------------------------------------------------------------------------------------------------
						//追问的事件start
						//修改追问
						modifyInquiries (index, index1) {
							modifyInquiriesDescribe(this.optionData, index, index1, modifyVm);
						},
						moveInquiries (index, index1, type) {
							let optionId = this.optionData[index].listInquiriesOptionDTO[index1].inquiriesQuestion.jwcpxt_question_id;
							$.post('/jwcpxt/Question/move_question_sort', {
								"question.jwcpxt_question_id" : optionId,
								"moveQuestionType" : type
							}, response => {
								if (response == "1") {
									if (type == 1) {
										toastr.success("上移成功");
									} else if (type == 2) {
										toastr.success("下移成功");
									}
									//更新信息
									vm.updateOptionInfo(() => {
										this.optionData = myData.checkQuestionModalData.listOptionDTO;
									});
								} else if (response == "-1") {
									if (type == 1) {
										toastr.error("上移失败");
									} else if (type == 2) {
										toastr.error("下移失败");
									}
								}
							}, 'text');
						},
						deleteInquiries (index, index1) {
							deleteInterface('/jwcpxt/Question/delete_questionInquiries', {
								"question.jwcpxt_question_id" : this.optionData[index].listInquiriesOptionDTO[index1].inquiriesQuestion.jwcpxt_question_id
							}, () => {
								modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
							});
						}, //追问的事件end
					},
				});
			},
			onDestroy : function() {
				modifyVm = null;
			},
			buttons : {
				addOption : {
					text : '添加选项',
					btnClass : 'btn-success',
					action : function() {
						addOption(modifyVm);
						return false;
					}
				},
				cancel : {
					text : '确认',
					btnClass : 'btn-blue',
					keys : [ 'esc', 'enter' ],
					action : function() {}
				}
			},
		});
	}

	/*添加一个选项*/
	function addOption(modifyVm) {
		let addOptionConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '30%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '添加一个选项',
			content : `
			<form novalidate>
				<div class="form-group">
					<label>问题描述</label>
					<textarea class="form-control" placeholder="请输入描述..." id="addOptionConfirm_describe"></textarea>
				</div>
				<div class="form-group">
					<label>问题分数</label>
					<input type="text" placeholder="请输入问题分数..." class="form-control" id="addOptionConfirm_grade"
						onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
						onblur="this.v();">
				</div>
			</form>
			`,
			buttons : {
				confirm : {
					text : '确认',
					btnClass : 'btn-info',
					keys : [ 'enter' ],
					action : function() {
						let describe = addOptionConfirm.$content.find('#addOptionConfirm_describe').val();
						if (!describe) {
							toastr.error("描述不能为空");
							return false;
						}
						let grade = addOptionConfirm.$content.find('#addOptionConfirm_grade').val();
						if (!addOptionConfirm.$content.find('#addOptionConfirm_grade').val()) {
							toastr.error("分数不能为空");
							return false;
						}
						let params = {
							"option.option_question" : vm.checkQuestionModalData.question.jwcpxt_question_id,
							"option.option_describe" : describe,
							"option.option_grade" : grade
						};
						$.post('/jwcpxt/Question/save_option', params, response => {
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

	/*选项描述和分数的修改*/
	function modifyOptionDescribeOrGrade(option, modifyVm) {
		let modifyConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '30%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '修改描述和分数',
			content : `
			<form novalidate>
				<div class="form-group">
					<label>问题描述</label>
					<textarea class="form-control" placeholder="请输入描述..." id="modifyConfirm_describe">${option.option.option_describe}</textarea>
				</div>
				<div class="form-group">
					<label>问题分数</label>
					<input type="text" placeholder="请输入问题分数..." class="form-control" id="modifyConfirm_grade" value="${option.option.option_grade}"
						onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
						onblur="this.v();">
				</div>
			</form>
			`,
			buttons : {
				confirm : {
					text : '确认',
					btnClass : 'btn-danger',
					keys : [ 'enter' ],
					action : function() {
						if (!modifyConfirm.$content.find('#modifyConfirm_describe').val()) {
							toastr.error("描述不能为空");
							return false;
						}
						if (!modifyConfirm.$content.find('#modifyConfirm_grade').val()) {
							toastr.error("分数不能为空");
							return false;
						}
						let params = {
							"option.jwcpxt_option_id" : option.option.jwcpxt_option_id,
							"option.option_describe" : modifyConfirm.$content.find('#modifyConfirm_describe').val(),
							"option.option_grade" : modifyConfirm.$content.find('#modifyConfirm_grade').val()
						};
						$.post('/jwcpxt/Question/update_option', params, response => {
							if (response == "1") {
								toastr.success("修改成功");
								//更新信息
								vm.updateOptionInfo(() => {
									modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
								});
							} else if (response == "-1") {
								toastr.error("修改失败");
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
		return false;
	}

	//添加一个追问
	function addInquiries(index, modifyVm) {
		let addInquiriesConfirm = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			boxWidth : '30%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			title : '添加一个追问',
			content : `
				<form novalidate>
					<div class="form-group">
						<label>问题描述</label>
						<textarea class="form-control" placeholder="请输入描述..." id="addInquiriesConfirm_describe"></textarea>
					</div>
					<div class="form-group">
					<label>问题类型</label>
					<select class="form-control" id="addInquiriesConfirm_type">
						<option value="">请选择问题类型</option>
						<option value="1">选择题</option>
						<option value="2">主观题</option>
					</select>
					</div>
				</form>
				`,
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


	//修改追问的描述
	function modifyInquiriesDescribe(modifyVm_OptionData, index, index1, modifyVm) {
		//InquiriesObj包含
		//追问对象 inquiriesQuestion
		//追问的选项数组(如果是追问选择题) listInquiriesOption
		let InquiriesObj = modifyVm_OptionData[index].listInquiriesOptionDTO[index1];
		let modifyInquiriesDescribe = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			typeAnimated : true, //未知。。。。
			boxWidth : '30%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			offsetTop : 10, //设置距离浏览器高度
			title : '修改选项',
			content : `
			<div id="modifyInquiriesDescribe">
				<div class="form-group">
					<label>问题描述</label>
					<textarea class="form-control" placeholder="请输入描述..." id="addInquiriesConfirm_describe">${InquiriesObj.inquiriesQuestion.question_describe}</textarea>
				</div>
			</div>
			`,
			buttons : {
				addOption : {
					text : '修改选项',
					btnClass : 'btn-success',
					isHidden : InquiriesObj.inquiriesQuestion.question_type == 3,
					action : function() {
						modifyInquiriesOption(modifyVm_OptionData, index, index1, modifyVm);
						return false;
					}
				},
				modifyDescribe : {
					text : '修改描述',
					btnClass : 'btn-blue',
					action : function() {
						let describe = modifyInquiriesDescribe.$content.find('#addInquiriesConfirm_describe').val();
						if (!describe) {
							torstr.error('描述不能为空');
							return false;
						}
						$.post('/jwcpxt/Question/update_question', {
							"question.jwcpxt_question_id" : InquiriesObj.inquiriesQuestion.jwcpxt_question_id,
							"question.question_describe" : describe
						}, response => {
							if (response == "1") {
								toastr.success("修改成功");
								//更新信息
								vm.updateOptionInfo(() => {
									modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
								});
							} else if (response == "-1") {
								toastr.error("修改失败");
							}
						}, 'text');
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					keys : [ 'esc' ],
					action : function() {}
				}
			},
		});
	}

	//修改一个追问的选项
	function modifyInquiriesOption(modifyVm_OptionData, _index, _index1, modifyVm) {
		//InquiriesObj包含
		//追问对象 inquiriesQuestion
		//追问的选项数组(如果是追问选择题) listInquiriesOption
		let InquiriesObj = modifyVm_OptionData[index].listInquiriesOptionDTO[index1];
		let inquiriesObjOption = InquiriesObj.listInquiriesOption;
		let modifyInquiriesVue;
		let modifyInquiriesOption = $.confirm({
			smoothContent : false, //关闭动画
			closeIcon : true, //关闭图标
			closeIconClass : 'fa fa-close', //图标样式
			type : 'dark', //弹出框类型
			typeAnimated : true, //未知。。。。
			boxWidth : '50%', //设置宽度
			useBootstrap : false, //设置是否使用bootstropt样式
			offsetTop : 10, //设置距离浏览器高度
			title : '修改选项',
			content : `
			<div id="inquiriesOption">
				<table style="width:100%; text-align: center;">
					<thead>
						<tr style="border-bottom: 2px solid #ddd">
							<th style="width:40px;">序号</th>
							<th>选项描述</th>
							<th style="width:160px;">选项操作</th>
						</tr>
					</thead>
					<tbody>
						<template v-for="(option,index) in optionData">
							<tr style="border-top: 1px solid #ddd;">
								<th>{{option.option_sort}}</th>
								<td>{{option.option_describe}}</td>
								<td>
									<i title="修改选项" class="ti-pencil-alt" @click="modifyOption(index)"></i>&nbsp;
									<i title="上移" class="fa fa-arrow-up" @click="moveOption(index,1)"></i>&nbsp;
									<i title="下移" class="fa fa-arrow-down" @click="moveOption(index,2)"></i>&nbsp;
									<i title="删除选项" class="ti-trash" @click="deleteOption(index)"></i>&nbsp;
								</td>
							</tr>
						</template>
					</tbody>
				</table>
			</div>
			`,
			onContentReady : function() {
				modifyInquiriesVue = new Vue({
					el : '#inquiriesOption',
					data : {
						optionData : inquiriesObjOption
					},
					methods : {
						//-----------------------------------------------------------------------------------------------------
						//选项的事件start
						modifyOption (index) {
							//modifyOptionDescribeOrGrade(this.optionData[index], modifyVm);
						},
						moveOption (index, type) {
							let optionId = this.optionData[index].jwcpxt_option_id;
							$.post('/jwcpxt/Question/move_option', {
								"option.jwcpxt_option_id" : optionId,
								"moveOptionType" : type
							}, response => {
								if (response == "1") {
									if (type == 1) {
										toastr.success("上移成功");
									} else if (type == 2) {
										toastr.success("下移成功");
									}
									//更新信息///////----将方法作为参数传递过去
									vm.updateOptionInfo(() => {
										modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
										this.optionData = myData.optionData[index].listInquiriesOptionDTO[index1].listInquiriesOption;
									});
								} else if (response == "-1") {
									if (type == 1) {
										toastr.error("上移失败");
									} else if (type == 2) {
										toastr.error("下移失败");
									}
								}
							}, 'text');
						},
						deleteOption (index) {
							//使用删除的接口工具
							// url后台接口
							// params删除的信息的id
							// function数据更新，重新渲染页面
							deleteInterface('/jwcpxt/Question/delete_option', {
								"option.jwcpxt_option_id" : inquiriesObjOption[index].jwcpxt_option_id
							}, () => {
								modifyVm.optionData = myData.checkQuestionModalData.listOptionDTO;
								this.optionData = myData.optionData[index].listInquiriesOptionDTO[index1].listInquiriesOption;
							});
						}, //选项的事件end
					//---------------------------------------------------------------------------------------------------------
					},
				});
			},
			onDestroy : function() {},
			buttons : {
				addOption : {
					text : '添加选项',
					btnClass : 'btn-success',
					action : function() {
						addOption(modifyVm);
						return false;
					}
				},
				cancel : {
					text : '确认',
					btnClass : 'btn-blue',
					keys : [ 'esc', 'enter' ],
					action : function() {}
				}
			},
		});
	}

	//删除接口工具
	function deleteInterface(url, params, rander) {
		$.confirm({
			title : "确定删除？",
			icon : 'fa fa-warning',
			type : "red",
			autoClose : 'close|10000',
			smoothContent : false,
			content : false,
			buttons : {
				tryAgain : {
					text : '确认',
					btnClass : 'btn-red',
					action : function() {
						$.post(url, params, response => {
							if (response == "1") {
								toastr.success("删除成功");
								vm.updateOptionInfo(rander); //更新option（选项）信息
								/*本页面列表数据更新，（删除了的信息需要刷新页面信息）*/
								queryData["questionVO.currPage"] = myData.page.currPage;
								vm.getInfo(queryData);
							} else if (response == "-1") {
								toastr.error("删除失败");
							}
						}, 'text');
					}
				},
				close : {
					text : '取消',
					btnClass : 'btn-default',
					action : function() {}
				}
			}
		});
	}

})

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}