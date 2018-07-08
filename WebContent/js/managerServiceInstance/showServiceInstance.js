/**
 * 
 */

var queryTemp = {
		'currPage':'1',
		'screenServiceInstanceJudge':'',
		'screenServiceInstanceStartDate':'',
		'screenServiceInstanceStopDate':''
}
var instanceVue;
var jwcpxt_service_definition_id;
$(function(){
	$('#screenServiceInstanceStartDate').val('');
	$('#screenServiceInstanceStopDate').val('');
	var url = window.location.href
	jwcpxt_service_definition_id = url.substring(url.indexOf("=")+1);
	console.log(jwcpxt_service_definition_id);
	instanceVue = new Vue({
		el:'#showContent',
		data:{
			instanceVO:''
		}
	})
	$.ajax({
		url:'/jwcpxt/User/list_user_byJurisdiction',
		type:'POST',
		data:{
			'user.user_Jurisdiction_evaluate':'have',
			'user.user_Jurisdiction_statistics':'',
			'user.user_Jurisdiction_review':''
		},
		success:function(data){
			var humanList =  JSON.parse(data);
			for(var i = 0 ; i <humanList.length;i++){
				$('#humanSelect').html($('#humanSelect').html()+'<option style="text-align:center;" value="'+humanList[i].jwcpxt_user_id+'">'+humanList[i].user_name+'</option>');	
			}
			
		}
	})
	loadData();
})

function loadData(){
	$('#serviceTable').hide();
	$('#loadingLayer').show();
	var queryCondition  = {
			'serviceInstanceVO.screenServiceInstanceStartDate':queryTemp.screenServiceInstanceStartDate,
			'serviceInstanceVO.screenServiceInstanceStopDate':queryTemp.screenServiceInstanceStopDate,
			'serviceInstanceVO.screenServiceInstanceJudge':queryTemp.screenServiceInstanceJudge,
			'serviceInstanceVO.currPage':queryTemp.currPage,
			'serviceInstanceVO.screenServiceDefinition':jwcpxt_service_definition_id
	}
	$.ajax({
		url:'/jwcpxt/Service/get_serviceInstanceVO',
		type:'POST',
		data:queryCondition,
		success:function(data){
			instanceVue.instanceVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#serviceTable').show();
		}
	})
}

function changeQuery(){
	queryTemp.screenSearch=$('#searchContent').val();
	queryTemp.currPage=1;
	queryTemp.screenServiceInstanceJudge=$('#humanSelect').val();
	queryTemp.screenServiceInstanceStartDate=$('#screenServiceInstanceStartDate').val();
	queryTemp.screenServiceInstanceStopDate=$('#screenServiceInstanceStopDate').val();
	loadData();
}

//首页
function skipToIndexPage() {
	if (instanceVue.instanceVO.currPage == 1) {
		toastr.error("已经是首页");
	} else {
		queryTemp.currPage = '1'
		loadData();
	}
}
// 上一页
function skipToPrimaryPage() {
	if (instanceVue.instanceVO.currPage <= 1) {
		toastr.error("没有上一页了哦");
	} else {
		queryTemp.currPage = --instanceVue.instanceVO.currPage;
		loadData();
	}
}
// 下一页
function skipToNextPage() {
	if (instanceVue.instanceVO.currPage >= instanceVue.instanceVO.totalPage) {
		toastr.error("没有下一页了哦");
	} else {
		queryTemp.currPage = ++instanceVue.instanceVO.currPage;
		loadData()
	}
}
// 末页
function skipToLastPage() {
	if (instanceVue.instanceVO.currPage == instanceVue.instanceVO.totalPage) {
		toastr.error("已经是最后一页了哦");
	} else {
		queryTemp.currPage = instanceVue.instanceVO.totalPage;
		loadData();
	}
}
// 跳页
function skipToArbitrarilyPage() {
	if ($('#skipPage').val() < '1'
			|| $('#skipPage').val() > instanceVue.instanceVO.totalPage) {
		toastr.error("不存在此页");
	} else {
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
