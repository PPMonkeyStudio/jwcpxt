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
			jwcpxt_question_id : '',
			question_describe : "",
			question_type : "",
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
			modifyQuestion (index) {
				$.post('/jwcpxt/Question/get_questionDTO_byQuestionId', {
					"question.jwcpxt_question_id" : myData.questionVO.questionList[index].jwcpxt_question_id
				}, response => {
					myData.checkQuestionModalData = response;
					$('#checkQuestionModal').modal('show');
				}, 'json');
			/*$.post('/jwcpxt/Question/get_questionVO', pramas, response => {
				myData.questionVO = response;
				myData.page.currPage = response.currPage;
				myData.page.totalPage = response.totalPage;
				myData.page.totalCount = response.totalCount;
				myData.page.pageSize = response.pageSize;
				this.pageInit(response);
				myData.ready = true;
			}, 'json');*/
			},
			modifyOpintion () {
				showOptions(index);
			},
			modifyDescribe () {},
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
							toastr.success("上移失败");
						} else if (type == 2) {
							toastr.success("下移失败");
						}
					}
				}, 'text');
			},
			deleteQuestion (index) {
				let questionId = myData.questionVO.questionList[index].jwcpxt_question_id;
			//删除接口
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
	function showOptions(index) {
		$.confirm({
			title : '修改选项',
			content : `
			<div id="modifyOption">
				<table style="witdh:100%">
					<thead>
						<tr>
							<th>追问</th>
							<th>序号</th>
							<th>选项描述</th>
							<th>分数</th>
							<th>选项操作</th>
						</tr>
					</thead>
					<tbody>
						<template v-for="">
						<tr>
							<td>追问</td>
							<td>序号</td>
							<td>选项描述</td>
							<td>分数</td>
							<td>选项操作</td>
						</tr>
						<tr>
							<td colspan="5">
								<div id="collapseOne2" class="panel-collapse collapse">
									<div class="panel-body">点击我进行展开，再次点击我进行折叠。第 1 部分</div>
								</div>
							</td>
						</tr>
						</template>
					</tbody>
				</table>
			</div>
			`,
			type : 'red',
			typeAnimated : true,
			onContentReady : function() {
				var self = this;
				let modifyVm = new Vue({
					el : "#modifyOption",
					data : {},
					mounted(){
						
					},
				});
			},
			buttons : {
				formSubmit : {
					text : 'Submit',
					btnClass : 'btn-blue',
					action : function() {}
				},
				cancel : function() {
					//close
				},
			},
			onContentReady : function() {
				// bind to events
				var jc = this;
				this.$content.find('form').on('submit', function(e) {
					e.preventDefault();
					jc.$$formSubmit.trigger('click'); // reference the button and click it
				});
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