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
		'checkFeedbackRectificationVO.screenEndTime' : queryTemp.screenEndTime
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
