/**
 * 
 */
var serviceVue;
$(function() {
	// 获得所有单位
	serviceVue = new Vue({
		'el' : '#managerContent',
		data : {
			serviceList : ''
		}
	})
	loadData();
})
function loadData() {
	$('#serviceTable').hide();
	$('#loadingLayer').show();
	$.ajax({
		url : '/jwcpxt/Service/list_serviceDefinition',
		type : 'GET',
		success : function(data) {
			serviceVue.serviceList = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#serviceTable').show();
		}
	})
}
