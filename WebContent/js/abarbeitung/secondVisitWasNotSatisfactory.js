
let myData = {
	ready : false,
	listSecondDistatisDTO : [],
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
};

let queryData = {
	"secondDistatisVO.currPage" : 1,
	"secondDistatisVO.search" : '',
	"secondDistatisVO.searchService" : '',
	"secondDistatisVO.searchTimeStart" : '',
	"secondDistatisVO.searchTimeEnd" : '',
	"secondDistatisVO.feedbackState" : ''
}

let MyVm = new Vue({
	el : "#content",
	data : myData,
	methods : {
		getInfo (pramas) {
			$.post('/jwcpxt/DissatisfiedFeedback/get_sercondDisStatisExceedTimeVO', pramas, response => {
				myData.listSecondDistatisDTO = response.listSecondDistatisDTO;
				myData.page.currPage = response.currPage;
				myData.page.totalPage = response.totalPage;
				myData.page.totalCount = response.totalCount;
				myData.page.pageSize = response.pageSize;
				this.pageInit(response);
				myData.ready = true;
			}, 'json');
		},
		pageInit (response) {
			myData.page.haveNextPage = response.currPage < response.totalPage;
			myData.page.havePrePage = response.currPage > 1;
			myData.page.isFirstPage = response.currPage == 1;
			myData.page.isLastPage = response.currPage == response.totalPage;
		},
		searchRectification ($event) {
			queryData["secondDistatisVO.currPage"] = 1; //重置页码
			queryData[$event.target.name] = $($event.target).val();
			this.getInfo(queryData);
		},
		pageTo (definition_id, client_id) {
			window.location.href = `/jwcpxt/Skip/skipPoliceAssessmentPage?definitionId=${definition_id}&serviceClientId=${client_id}`;
		},
		firstPage () {
			if (myData.page.isFirstPage) {
				toastr.error("已经是在首页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] = 1;
			this.getInfo(queryData)
		},
		prePage () {
			if (!myData.page.havePrePage) {
				toastr.error("没有上一页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] -= 1;
			this.getInfo(queryData)
		},
		nextPage () {
			if (!myData.page.haveNextPage) {
				toastr.error("没有下一页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] += 1;
			this.getInfo(queryData)
		},
		lastPage () {
			if (myData.page.isLastPage) {
				toastr.error("已经是在尾页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] = myData.page.totalPage;
			this.getInfo(queryData)
		},
		toPage () {
			let pageIndex = $('#toPageInput').val();
			if (pageIndex < 1 || pageIndex > myData.page.totalPage) {
				toastr.error("输入的数字不在页数范围内,请检查页码");
				return;
			}
			queryData["secondDistatisVO.currPage"] = pageIndex;
			this.getInfo(queryData)
		},
	},
	mounted () {
		this.getInfo(queryData);

		//获取所有的业务
		$.post('/jwcpxt/Service/list_serviceDefinition_all', {}, response => {
			let str = `<option value="">全部</option>`;
			response.forEach(function(elt, i) {
				str += `<option value="${elt.jwcpxt_service_definition_id}">${elt.service_definition_describe}</option>`;
			})
			$('select[name="secondDistatisVO.searchService"]').html(str).selectpicker('refresh');
		}, 'json');
	},
})

function changeQuery(event) {
	queryData[$(event).attr('name')] = $(event).val();
	MyVm.getInfo(queryData);
}

function changeState(that) {
	let changeStateConfirm = $.confirm({
		title : '修改',
		type : 'dark',
		boxWidth : '500px',
		useBootstrap : false,
		content : '<label>原因</label><textarea class="form-control"></textarea>',
		buttons : {
			update : {
				text : '确定',
				btnClass : 'btn-blue',
				action : function() {
					if (!changeStateConfirm.$content.find('textarea').val()) {
						toastr.error('原因不能为空');
						return false;
					}
					$.post('/jwcpxt/DissatisfiedFeedback/update_ServiceInstance_State', {
						"serviceInstance.jwcpxt_service_instance_id" : $(that).attr('id'),
						"serviceInstance.service_instance_feedback_state" : 2,
						"serviceInstance.service_instance_feedback_reason" : changeStateConfirm.$content.find('textarea').val()
					}, response => {
						if (response == "success") {
							toastr.success('修改成功！');
							MyVm.getInfo(queryData);
						} else {
							toastr.success('修改失败！');
						}
					}, 'text');
				}
			},
			cancel : {
				text : '关闭',
				btnClass : 'btn-default',
				action : function() {}
			}
		},
	})
}


function viewReason(that) {
	let viewReasonConfirm = $.confirm({
		title : '查看原因',
		type : 'dark',
		boxWidth : '500px',
		useBootstrap : false,
		content : '<label>原因</label><textarea disabled class="form-control"></textarea>',
		onContentReady : function() {
			$.post('/jwcpxt/Service/get_serviceInstanceDo_byId', {
				"serviceInstance.jwcpxt_service_instance_id" : $(that).attr('id')
			}, response => {
				viewReasonConfirm.$content.find('textarea').val(response.service_instance_feedback_reason);
			}, 'json');
		},
		buttons : {
			update : {
				text : '确定',
				btnClass : 'btn-blue',
				action : function() {}
			}
		},
	})
}


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



var transferListVue;

function preview(event) {
	$.confirm({
		title : '预览流转单',
		type : 'blue',
		boxWidth : '1000px',
		useBootstrap : false,
		content : '<div v-cloak id="transferList">编号：{{ transfer.feedback_rectification_no }}'
			+ '<table class="table table-bordered">'
			+ '<tr><td style="width:150px">问题标题</td><td colspan="3">{{ transfer.feedback_rectification_title }}</td></tr>'
			+ '<tr><td>收集渠道</td><td style="width:300px">{{ transfer.feedback_rectification_collect_channel }}</td><td style="width:150px">收集时间</td><td>{{ transfer.feedback_rectification_collect_time }}</td></tr>'
			+ '<tr><td>当事人</td><td>{{ transfer.feedback_rectification_client_name }}</td><td>联系电话</td><td>{{ transfer.feedback_rectification_client_phone }}</td></tr>'
			+ '<tr><td>问题简述</td><td colspan="3">{{ transfer.feedback_rectification_question_describe }}</td></tr>'
			+ '<tr><td>协调情况</td><td colspan="3">转交{{ transfer.feedback_rectification_unit_name }}办理</td></tr>'
			+ '<tr><td>单位联系人</td><td>{{ transfer.feedback_rectification_unit_people }}</td><td>联系电话</td><td>{{ transfer.feedback_rectification_unit_people_phone }}</td></tr>'
			+ '<tr><td>办理情况</td><td><template v-if="transfer.feedback_rectification_handle_state == 1">'
			+ '未办</template> <template v-else>办结'
			+ '</template></td><td>反馈日期</td><td>{{ transfer.feedback_rectification_date }}</td></tr>'
			+ '<tr><td>反馈情况简述</td><td colspan="3">{{ transfer.feedback_rectification_content }}</td></tr>'
			+ '<tr><td>上级主管部门意见</td><td colspan="3">{{ transfer.feedback_rectification_sjzgbm_opinion }}</td></tr>'
			+ '<tr><td>测评中心意见</td><td colspan="3">{{ transfer.feedback_rectification_cpzx_opinion }}</td></tr>'
			+ '</table></div>',
		buttons : {
			cancel : {
				text : '关闭',
				btnClass : 'btn-blue',
				action : function() {}
			}
		},
		onContentReady : function() {
			transferListVue = new Vue({
				el : '#transferList',
				data : {
					transfer : ''
				}
			})
			$.ajax({
				url : '/jwcpxt/DissatisfiedFeedback/get_feedbackRectficationDO_byId?feedbackRectification.jwcpxt_feedback_rectification_id='
					+ event.id,
				type : 'GET',
				success : function(data) {
					transferListVue.transfer = JSON.parse(data);
				}
			})
		}
	})
}


//跳转到当事人见面
function skipToClientInfomation(that) {
	let definitionId = $(that).attr('definitionId');
	let phone = $(that).attr('phone');
	window.open('/jwcpxt/Skip/skipClientInformationPage?definitionId=' + definitionId + '&unitId=' + phone);
}