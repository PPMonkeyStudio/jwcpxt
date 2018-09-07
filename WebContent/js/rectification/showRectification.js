/**
 * 
 */
var MounthFristDay = function() {
	let date_ = new Date();
	let mounth = (date_.getMonth() + 1).length > 1 ? (date_.getMonth() + 1) : '0' + (date_.getMonth() + 1);
	let dataVa = date_.getFullYear() + '-' + mounth + '-01';
	return dataVa;
}();

var rectificationVue;

var queryTemp = {
	startTime : '',
	endTime : '',
	currPage : '1',
	searchHandleState : '-1',
	searchTitle : '',
	searchAuditState : '-1'
}

$(function() {
	rectificationVue = new Vue({
		el : '#showContent',
		data : {
			rectificationVO : ''
		}
	})
	$('#searchTimeStart').val('');
	$('#searchTimeEnd').val('');
	$('#searchTitle').val('');
	loadData();
})

function loadData() {
	$('#rectificationTable').hide();
	$('#loadingLayer').show();
	var queryCondition = {
		'feedbackRectificationVO.screenStartTime' : queryTemp.startTime,
		'feedbackRectificationVO.screenEndTime' : queryTemp.endTime,
		'feedbackRectificationVO.screenHandleState' : queryTemp.searchHandleState,
		'feedbackRectificationVO.screenSearch' : queryTemp.searchTitle,
		'feedbackRectificationVO.screenAuditState' : queryTemp.searchAuditState,
		'feedbackRectificationVO.currPage' : queryTemp.currPage
	}
	$.ajax({
		url : '/jwcpxt/DissatisfiedFeedback/get_feedbackRectificationVO',
		type : 'POST',
		data : queryCondition,
		success : function(data) {
			rectificationVue.rectificationVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#rectificationTable').show();
		}
	})
}

function changeQuery() {
	queryTemp.startTime = $('#searchTimeStart').val();
	queryTemp.endTime = $('#searchTimeEnd').val();
	queryTemp.searchHandleState = $('#searchHandleState').val();
	queryTemp.searchTitle = $('#searchTitle').val();
	queryTemp.searchAuditState = $('#searchAuditState').val();
	queryTemp.currPage = 1;
	loadData();
}

// 首页
function skipToIndexPage() {
	if (rectificationVue.rectificationVO.currPage == 1) {
		toastr.error("已经是首页");
	} else {
		queryTemp.currPage = '1'
		loadData();
	}
}
// 上一页
function skipToPrimaryPage() {
	if (rectificationVue.rectificationVO.currPage <= 1) {
		toastr.error("没有上一页了哦");
	} else {
		queryTemp.currPage = --rectificationVue.rectificationVO.currPage;
		loadData();
	}
}
// 下一页
function skipToNextPage() {
	if (rectificationVue.rectificationVO.currPage >= rectificationVue.rectificationVO.totalPage) {
		toastr.error("没有下一页了哦");
	} else {
		queryTemp.currPage = ++rectificationVue.rectificationVO.currPage;
		loadData()
	}
}
// 末页
function skipToLastPage() {
	if (rectificationVue.rectificationVO.currPage == rectificationVue.rectificationVO.totalPage) {
		toastr.error("已经是最后一页了哦");
	} else {
		queryTemp.currPage = rectificationVue.rectificationVO.totalPage;
		loadData();
	}
}
// 跳页
function skipToArbitrarilyPage() {
	if ($('#skipPage').val() < '1'
		|| $('#skipPage').val() > rectificationVue.rectificationVO.totalPage) {
		toastr.error("不存在此页");
	} else {
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}

//跳转到当事人见面
function skipToClientInfomation(that) {
	let definitionId = $(that).attr('definitionId');
	let phone = $(that).attr('phone');
	window.open('/jwcpxt/Skip/skipClientInformationPage?definitionId=' + definitionId + '&unitId=' + phone);
}