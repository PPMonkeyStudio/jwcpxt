/**
 * 
 */

var checkVue;
var queryTemp = {
	screenStartTime : '',
	screenEndTime : '',
	currPage : '1',
	screenSearch : '',
	screenCheckState : '-1',
}

$(function() {
	checkVue = new Vue({
		el : '#showContent',
		data : {
			checkVO : ''
		}
	})
	$('#searchTimeStart').val('');
	$('#searchTimeEnd').val('');
	$('#searchTitle').val('');
	loadData();
})

function loadData() {
	$('#checkTable').hide();
	$('#loadingLayer').show();
	var queryCondition = {
		'checkFeedbackRectificationVO.screenCheckState' : queryTemp.screenCheckState,
		'checkFeedbackRectificationVO.screenSearch' : queryTemp.screenSearch,
		'checkFeedbackRectificationVO.screenStartTime' : queryTemp.screenStartTime,
		'checkFeedbackRectificationVO.screenEndTime' : queryTemp.screenEndTime,
		'checkFeedbackRectificationVO.currPage':queryTemp.currPage
	}
	$.ajax({
		url : '/jwcpxt/DissatisfiedFeedback/get_checkFeedbackRectificationVO',
		type : 'POST',
		data : queryCondition,
		success : function(data) {
			checkVue.checkVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#checkTable').show();
		}
	})
}

function changeQuery() {
	queryTemp.screenStartTime = $('#searchTimeStart').val();
	queryTemp.screenEndTime = $('#searchTimeEnd').val();
	queryTemp.screenSearch = $('#searchTitle').val();
	queryTemp.screenCheckState = $('#searchAuditState').val();
	console.log(queryTemp);
	loadData();
}

// 首页
function skipToIndexPage() {
	if (checkVue.checkVO.currPage == 1) {
		toastr.error("已经是首页");
	} else {
		queryTemp.currPage = '1'
		loadData();
	}
}
// 上一页
function skipToPrimaryPage() {
	if (checkVue.checkVO.currPage <= 1) {
		toastr.error("没有上一页了哦");
	} else {
		queryTemp.currPage = --checkVue.checkVO.currPage;
		loadData();
	}
}
// 下一页
function skipToNextPage() {
	if (checkVue.checkVO.currPage >= checkVue.checkVO.totalPage) {
		toastr.error("没有下一页了哦");
	} else {
		queryTemp.currPage = ++checkVue.checkVO.currPage;
		loadData()
	}
}
// 末页
function skipToLastPage() {
	if (checkVue.checkVO.currPage == checkVue.checkVO.totalPage) {
		toastr.error("已经是最后一页了哦");
	} else {
		queryTemp.currPage = checkVue.checkVO.totalPage;
		loadData();
	}
}
// 跳页
function skipToArbitrarilyPage() {
	if ($('#skipPage').val() < '1'
			|| $('#skipPage').val() > checkVue.checkVO.totalPage) {
		toastr.error("不存在此页");
	} else {
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}


function checkRectification(event,state){
	$
	.confirm({
		title : '审核',
		type : 'blue',
		boxWidth : '500px',
		useBootstrap : false,
		content : '<form id="checkRectification"><label>反馈意见</label><textarea name="feedbackRectification.feedback_rectification_cpzx_opinion" class="form-control"></textarea></form>',
		buttons : {
			cancel : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {

				}
			},
			save : {
				text : '确定',
				btnClass : 'btn-blue',
				action : function() {
					var formData = new FormData(document
							.getElementById("checkRectification"));
					formData
							.append(
									"feedbackRectification.jwcpxt_feedback_rectification_id",
									event.id);
					formData.append("feedbackRectification.feedback_rectification_audit_state",state);
					$
							.ajax({
								url : '/jwcpxt/DissatisfiedFeedback/checkFeedbackRectification',
								type : 'POST',
								data : formData,
								processData : false,
								contentType : false,
								success : function(data) {
									if (data == 1) {
										toastr.success("审核成功！");
										loadData();
									} else {
										toastr.error("审核失败！");
									}
								}
							})
				}
			}
		},
	})
}

var transferListVue;

function preview(event) {
	$
			.confirm({
				title : '预览流转单',
				type : 'blue',
				boxWidth : '1000px',
				useBootstrap : false,
				content : '<div id="transferList">编号：{{ transfer.feedback_rectification_no }}'
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
					buttons:{
						cancel:{
							text : '关闭',
							btnClass : 'btn-blue',
							action : function() {

							}
						}
					},
				onContentReady : function() {
					transferListVue = new Vue({
						el : '#transferList',
						data : {
							transfer : ''
						}
					})

					$
							.ajax({
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