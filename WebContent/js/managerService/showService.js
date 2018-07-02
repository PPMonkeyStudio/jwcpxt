/**
 * 
 */
var queryTemp={
	'currPage':1,
}
var serviceVue;
$(function(){
	//获得所有单位
	serviceVue = new Vue({
		'el':'#managerContent',
		data:{
			serviceVO:''
		}
	})
	loadData();
})
function loadData(){
	$('#serviceTable').hide();
	$('#loadingLayer').show();
	var queryObject = {
			'userVO.currPage':queryTemp.currPage
	}
	$.ajax({
		url:'/jwcpxt/Service/get_serviceDefinitionVO',
		type:'POST',
		data:queryObject,
		success:function(data){
			serviceVue.serviceVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#serviceTable').show();
		}
	})
}

function changeQuery(){
	queryTemp.currPage='1';
	loadData();
}

//首页
function skipToIndexPage(){
	if(serviceVue.serviceVOcurrPage == '1'){
		toastr.error("已经是首页");
	}
	else{
		queryTemp.currPage='1'
		loadData();
	}
}
//上一页
function skipToPrimaryPage(){
	if(serviceVue.serviceVO.currPage <='1'){
		toastr.error("没有上一页了哦");
	}
	else{
		queryTemp.currPage = --serviceVue.serviceVO.currPage;
		loadData();
	}
}
//下一页
function skipToNextPage(){
	if(serviceVue.serviceVO.currPage>=serviceVue.serviceVO.totalPage){
		toastr.error("没有下一页了哦");
	}
	else{
		queryTemp.currPage = ++serviceVue.serviceVO.currPage;
		loadData()
	}
}
//末页
function skipToLastPage(){
	if(serviceVue.serviceVO.currPage==serviceVue.serviceVO.totalPage)
		{
			toastr.error("已经是最后一页了哦");
		}
	else{
		queryTemp.currPage = serviceVue.serviceVO.totalPage;
		loadData();
	}
}
//跳页
function skipToArbitrarilyPage(){
	if($('#skipPage').val()<'1'||$('#skipPage').val()>serviceVue.serviceVO.totalPage){
		toastr.error("不存在此页");
	}
	else{
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
