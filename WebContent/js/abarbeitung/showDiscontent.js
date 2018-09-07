/**
 * 
 */

var MounthFristDay = function() {
	let date_ = new Date();
	let mounth = (date_.getMonth() + 1).length > 1 ? (date_.getMonth() + 1) : '0' + (date_.getMonth() + 1);
	let dataVa = date_.getFullYear() + '-' + mounth + '-01';
	return dataVa;
}();

var discontentVue;
var queryTemp = {
	'currPage' : '1',
	'searchTimeStart' : MounthFristDay,
	'searchTimeEnd' : '',
	'searchStatus' : '-1',
	'searchTitle' : '',
	'searchService' : ''
}

$(function() {
	discontentVue = new Vue({
		el : '#showDiscontent',
		data : {
			discontentVO : ''
		}
	})
	$('#startTime').val(MounthFristDay);
	$('#endTime').val('');
	loadData();
	//获取所有的业务
	$.post('/jwcpxt/Service/list_serviceDefinition_all', {}, response => {
		let str = `<option value="">全部</option>`;
		response.forEach(function(elt, i) {
			str += `<option value="${elt.jwcpxt_service_definition_id}">${elt.service_definition_describe}</option>`;
		})
		$('#searchService').html(str).selectpicker('refresh');
	}, 'json');
})

function loadData() {
	$('#discontentTable').hide();
	$('#loadingLayer').show();
	var queryCondition = {
		'dissatisfiedQuestionVO.screenStartTime' : queryTemp.searchTimeStart,
		'dissatisfiedQuestionVO.screenEndTime' : queryTemp.searchTimeEnd,
		'dissatisfiedQuestionVO.screenState' : queryTemp.searchStatus,
		'dissatisfiedQuestionVO.currPage' : queryTemp.currPage,
		'dissatisfiedQuestionVO.searchTitle' : queryTemp.searchTitle,
		'dissatisfiedQuestionVO.searchService' : queryTemp.searchService
	}
	$.ajax({
		url : '/jwcpxt/DissatisfiedFeedback/get_dissatisfiedQuestionVO',
		type : 'POST',
		data : queryCondition,
		success : function(data) {
			discontentVue.discontentVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#discontentTable').show();
		}
	})
}

function changeQuery() {
	queryTemp.searchTimeStart = $('#startTime').val();
	queryTemp.searchTimeEnd = $('#endTime').val();
	queryTemp.searchStatus = $('#searchState').val();
	queryTemp.searchTitle = $('#searchTitle').val();
	queryTemp.searchService = $('#searchService').val();
	loadData();
}

// 首页
function skipToIndexPage() {
	if (discontentVue.discontentVO.currPage == 1) {
		toastr.error("已经是首页");
	} else {
		queryTemp.currPage = '1'
		loadData();
	}
}
// 上一页
function skipToPrimaryPage() {
	if (discontentVue.discontentVO.currPage <= 1) {
		toastr.error("没有上一页了哦");
	} else {
		queryTemp.currPage = --discontentVue.discontentVO.currPage;
		loadData();
	}
}
// 下一页
function skipToNextPage() {
	if (discontentVue.discontentVO.currPage >= discontentVue.discontentVO.totalPage) {
		toastr.error("没有下一页了哦");
	} else {
		queryTemp.currPage = ++discontentVue.discontentVO.currPage;
		loadData()
	}
}
// 末页
function skipToLastPage() {
	if (discontentVue.discontentVO.currPage == discontentVue.discontentVO.totalPage) {
		toastr.error("已经是最后一页了哦");
	} else {
		queryTemp.currPage = discontentVue.discontentVO.totalPage;
		loadData();
	}
}
// 跳页
function skipToArbitrarilyPage() {
	if ($('#skipPage').val() < '1'
		|| $('#skipPage').val() > discontentVue.discontentVO.totalPage) {
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