/**
 * 
 */
var queryTemp={
	'currPage':1,
	'screenSearch':'',
	'screenUnit':''
}
var userVue;
$(function(){
	//获得所有单位
	$.ajax({
		url:'/jwcpxt/Unit/list_unit_all',
		type:'GET',
		success:function(data){
			var unitList = JSON.parse(data);
			for(var i=0;i<unitList.length;i++){
				$('#searchUnit').html($('#searchUnit').html()+"<option value='"+unitList[i].jwcpxt_unit_id+"'>"+unitList[i].unit_name+"</option>");
			}
		}
	})
	userVue = new Vue({
		'el':'#managerContent',
		data:{
			userVO:''
		}
	})
	loadData();
})
function loadData(){
	$('#userTable').hide();
	$('#loadingLayer').show();
	var queryObject = {
			'userVO.currPage':queryTemp.currPage,
			'userVO.screenSearch':queryTemp.screenSearch,
			'userVO.screenUnit':queryTemp.screenUnit
	}
	$.ajax({
		url:'/jwcpxt/User/get_userVO',
		type:'POST',
		data:queryObject,
		success:function(data){
			userVue.userVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#userTable').show();
		}
	})
}

function changeQuery(){
	queryTemp.currPage='1';
	queryTemp.screenSearch=$('#searchContent').val();
	queryTemp.screenUnit=$('#searchUnit').val();
	loadData();
}

//首页
function skipToIndexPage(){
	if(userVue.userVO.currPage == '1'){
		toastr.error("已经是首页");
	}
	else{
		queryTemp.currPage='1'
		loadData();
	}
}
//上一页
function skipToPrimaryPage(){
	if(userVue.userVO.currPage <='1'){
		toastr.error("没有上一页了哦");
	}
	else{
		queryTemp.currPage = --userVue.userVO.currPage;
		loadData();
	}
}
//下一页
function skipToNextPage(){
	if(userVue.userVO.currPage>=userVue.userVO.totalPage){
		toastr.error("没有下一页了哦");
	}
	else{
		queryTemp.currPage = ++userVue.userVO.currPage;
		loadData()
	}
}
//末页
function skipToLastPage(){
	if(userVue.userVO.currPage==userVue.userVO.totalPage)
		{
			toastr.error("已经是最后一页了哦");
		}
	else{
		queryTemp.currPage = userVue.userVO.totalPage;
		loadData();
	}
}
//跳页
function skipToArbitrarilyPage(){
	if($('#skipPage').val()<'1'||$('#skipPage').val()>userVue.userVO.totalPage){
		toastr.error("不存在此页");
	}
	else{
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
