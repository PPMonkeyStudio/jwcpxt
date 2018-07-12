/**
 * 
 */

var discontentVue;
var queryTemp ={
		'currPage':'1',
		'searchTimeStart':'',
		'searchTimeEnd':'',
		'searchStatus':'-1'
}

$(function(){
	discontentVue = new Vue({
		el:'#showDiscontent',
		data:{
			discontentVO:''
		}
	})
	loadData();
})

function loadData(){
	$('#discontentTable').hide();
	$('#loadingLayer').show(); 
	var queryCondition={
		'dissatisfiedQuestionVO.screenStartTime':queryTemp.searchTimeStart,
		'dissatisfiedQuestionVO.screenEndTime':queryTemp.searchTimeEnd,
		'dissatisfiedQuestionVO.screenState':queryTemp.searchStatus,
		'dissatisfiedQuestionVO.currPage':queryTemp.currPage
	}
	$.ajax({
		url:'/jwcpxt/DissatisfiedFeedback/get_dissatisfiedQuestionVO',
		type:'POST',
		data:queryCondition,
		success:function(data){
			discontentVue.discontentVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#discontentTable').show();
		}
	})
}

function changeQuery(){
	queryTemp.searchTimeStart = $('#startTime').val();
	queryTemp.searchTimeEnd = $('#endTime').val();
	queryTemp.searchStatus = $('#searchState').val();
	loadData();
}

//首页
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

