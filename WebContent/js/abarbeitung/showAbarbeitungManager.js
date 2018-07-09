/**
 * 
 */

var abarbeitungVue;
var queryTemp ={
		currPage:'',
		timeStart:'',
		timeEnd:'',
		searchUnit:''
}

$(function(){
	// 获得所有单位
	$.ajax({
		url : '/jwcpxt/Unit/list_unit_all',
		type : 'GET',
		success : function(data) {
			var unitList = JSON.parse(data);
			for (var i = 0; i < unitList.length; i++) {
				$('#searchUnit').html(
						$('#searchUnit').html() + "<option value='"
								+ unitList[i].jwcpxt_unit_id + "'>"
								+ unitList[i].unit_name + "</option>");
			}
		}
	})
	
	abarbeitungVue  = new Vue({
		el:'#showContent',
		data:{
			abarbeitungVO:''
		}
	})
	
	loadData();
})

function loadData(){
	$('#serviceTable').hide();
	$('#loadingLayer').show();
	var queryCondition = {
			
	}
	$.ajax({
		url:'',
		type:'POST',
		data:queryCondition,
		success:function(data){
			abarbeitungVue.abarbeitungVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#serviceTable').show();
		}
	})
}

//首页
function skipToIndexPage(){
	if(abarbeitungVue.abarbeitungVO.currPage == '1'){
		toastr.error("已经是首页");
	}
	else{
		queryTemp.currPage='1'
		loadData();
	}
}
//上一页
function skipToPrimaryPage(){
	if(abarbeitungVue.abarbeitungVO.currPage <='1'){
		toastr.error("没有上一页了哦");
	}
	else{
		queryTemp.currPage = --abarbeitungVue.abarbeitungVO.currPage;
		loadData();
	}
}
//下一页
function skipToNextPage(){
	if(abarbeitungVue.abarbeitungVO.currPage>=abarbeitungVue.abarbeitungVO.totalPage){
		toastr.error("没有下一页了哦");
	}
	else{
		queryTemp.currPage = ++abarbeitungVue.abarbeitungVO.currPage;
		loadData()
	}
}
//末页
function skipToLastPage(){
	if(abarbeitungVue.abarbeitungVO.currPage==abarbeitungVue.abarbeitungVO.totalPage)
		{
			toastr.error("已经是最后一页了哦");
		}
	else{
		queryTemp.currPage = abarbeitungVue.abarbeitungVO.totalPage;
		loadData();
	}
}
//跳页
function skipToArbitrarilyPage(){
	if($('#skipPage').val()<'1'||$('#skipPage').val()>abarbeitungVue.abarbeitungVO.totalPage){
		toastr.error("不存在此页");
	}
	else{
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
