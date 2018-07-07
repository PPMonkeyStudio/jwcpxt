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