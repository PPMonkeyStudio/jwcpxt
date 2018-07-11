/**
 * 
 */
var unitVue;
$(function(){
	unitVue = new Vue({
		'el':'#managerContent',
		data:{
			unitVO:''
		}
	})
	loadData();
})
function loadData(){
	$('#unitTable').hide();
	$('#loadingLayer').show();
	$.ajax({
		url:'/jwcpxt/Unit/get_unitVO',
		type:'GET',
		success:function(data){
			unitVue.unitVO=JSON.parse(data);
			$('#unitTable').show();
			$('#loadingLayer').hide();					
		}
	})
}