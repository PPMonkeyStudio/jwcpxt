/**
 * 
 */

var discontentVue;
var queryTemp ={
		'currPage':'',
		'searchTimeStart':'',
		'searchTimeEnd':'',
		'searchStatus':''
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
	var queryCondition={
		'dissatisfiedQuestionVO.screenStartTime':queryTemp.searchTimeStart,
		'dissatisfiedQuestionVO.screenEndTime':queryTemp.searchTimeEnd,
		'dissatisfiedQuestionVO.screenState':queryTemp.searchStatus,
		'dissatisfiedQuestionVO.currPage':queryTemp.currPage
	}
	$.ajax({
		url:'',
		type:'POST',
		data:queryCondition,
		success:function(data){
			discontentVue.discontentVO = JSON.parse(data);
		}
	})
}
