/**
 * 
 */
var queryTemp={
	
}
var userVue;
$(function(){
	userVue = new Vue({
		'el':'#managerContent',
		data:{
			userVO:''
		}
	})
	loadData();
})
function loadData(){
	
}

//首页
function skipToIndexPage(){
	if(alarmVue.alarmVO.currPage == '1'){
		toastr.error("已经是首页");
	}
	else{
		queryTemp.currPage='1'
		loadData();
	}
}
//上一页
function skipToPrimaryPage(){
	if(alarmVue.alarmVO.currPage <='1'){
		toastr.error("没有上一页了哦");
	}
	else{
		queryTemp.currPage = --alarmVue.alarmVO.currPage;
		loadData();
	}
}
//下一页
function skipToNextPage(){
	if(alarmVue.alarmVO.currPage>=alarmVue.alarmVO.totalPage){
		toastr.error("没有下一页了哦");
	}
	else{
		queryTemp.currPage = ++alarmVue.alarmVO.currPage;
		loadData()
	}
}
//末页
function skipToLastPage(){
	if(alarmVue.alarmVO.currPage==alarmVue.alarmVO.totalPage)
		{
			toastr.error("已经是第一页了哦");
		}
	else{
		queryTemp.currPage = alarmVue.alarmVO.totalPage;
		loadData();
	}
}
//跳页
function skipToArbitrarilyPage(){
	if($('#skipPage').val()<'1'||$('#skipPage').val()>alarmVue.alarmVO.totalPage){
		toastr.error("不存在此页");
	}
	else{
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
