/**
 * 
 */
var queryTemp = {
	'currPage' : 1,
	'screenSearch' : '',
	'screenUnit' : ''
}
var serviceVue;
$(function() {
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

	serviceVue = new Vue({
		'el' : '#managerContent',
		data : {
			serviceVO : ''
		}
	})
	loadData();
})
function loadData() {
	$('#serviceTable').hide();
	$('#loadingLayer').show();
	var queryObject = {
		'serviceDefinitionVO.currPage' : queryTemp.currPage,
		'serviceDefinitionVO.screenUnit' : queryTemp.screenUnit,
		'serviceDefinitionVO.screenSearch' : queryTemp.screenSearch
	}
	$.ajax({
		url : '/jwcpxt/Service/get_serviceDefinitionVO',
		type : 'POST',
		data : queryObject,
		success : function(data) {
			serviceVue.serviceVO = JSON.parse(data);
			$('#loadingLayer').hide();
			$('#serviceTable').show();
		}
	})
}

function changeQuery() {
	queryTemp.currPage = '1';
	queryTemp.screenSearch=$('#searchContent').val();
	queryTemp.screenUnit=$('#searchUnit').val();
	loadData();
}

// 首页
function skipToIndexPage() {
	if (serviceVue.serviceVOcurrPage == '1') {
		toastr.error("已经是首页");
	} else {
		queryTemp.currPage = '1'
		loadData();
	}
}
// 上一页
function skipToPrimaryPage() {
	if (serviceVue.serviceVO.currPage <= '1') {
		toastr.error("没有上一页了哦");
	} else {
		queryTemp.currPage = --serviceVue.serviceVO.currPage;
		loadData();
	}
}
// 下一页
function skipToNextPage() {
	if (serviceVue.serviceVO.currPage >= serviceVue.serviceVO.totalPage) {
		toastr.error("没有下一页了哦");
	} else {
		queryTemp.currPage = ++serviceVue.serviceVO.currPage;
		loadData()
	}
}
// 末页
function skipToLastPage() {
	if (serviceVue.serviceVO.currPage == serviceVue.serviceVO.totalPage) {
		toastr.error("已经是最后一页了哦");
	} else {
		queryTemp.currPage = serviceVue.serviceVO.totalPage;
		loadData();
	}
}
// 跳页
function skipToArbitrarilyPage() {
	if ($('#skipPage').val() < '1'
			|| $('#skipPage').val() > serviceVue.serviceVO.totalPage) {
		toastr.error("不存在此页");
	} else {
		queryTemp.currPage = $('#skipPage').val();
		loadData();
	}
}
