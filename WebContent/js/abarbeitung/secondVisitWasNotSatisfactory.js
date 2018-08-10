
let myData = {
	ready : false,
	listSecondDistatisDTO : [],
	page : {
		currPage : 1,
		totalPage : 1,
		totalCount : 11,
		pageSize : 10,
		haveNextPage : false,
		havePrePage : false,
		isFirstPage : false,
		isLastPage : false
	},
};

let queryData = {
	"secondDistatisVO.currPage" : 1,
	"secondDistatisVO.search" : '',
	"secondDistatisVO.searchService" : '',
	"secondDistatisVO.searchTimeStart" : '',
	"secondDistatisVO.searchTimeEnd" : ''
}

let MyVm = new Vue({
	el : "#content",
	data : myData,
	methods : {
		getInfo (pramas) {
			$.post('/jwcpxt/DissatisfiedFeedback/get_sercondDisStatisExceedTimeVO', pramas, response => {
				myData.listSecondDistatisDTO = response.listSecondDistatisDTO;
				myData.page.currPage = response.currPage;
				myData.page.totalPage = response.totalPage;
				myData.page.totalCount = response.totalCount;
				myData.page.pageSize = response.pageSize;
				this.pageInit(response);
				myData.ready = true;
			}, 'json');
		},
		pageInit (response) {
			myData.page.haveNextPage = response.currPage < response.totalPage;
			myData.page.havePrePage = response.currPage > 1;
			myData.page.isFirstPage = response.currPage == 1;
			myData.page.isLastPage = response.currPage == response.totalPage;
		},
		searchRectification ($event) {
			queryData["secondDistatisVO.currPage"] = 1; //重置页码
			queryData[$event.target.name] = $($event.target).val();
			this.getInfo(queryData);
		},
		pageTo (definition_id, client_id) {
			window.location.href = `/jwcpxt/Skip/skipPoliceAssessmentPage?definitionId=${definition_id}&serviceClientId=${client_id}`;
		},
		firstPage () {
			if (myData.page.isFirstPage) {
				toastr.error("已经是在首页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] = 1;
			this.getInfo(queryData)
		},
		prePage () {
			if (!myData.page.havePrePage) {
				toastr.error("没有上一页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] -= 1;
			this.getInfo(queryData)
		},
		nextPage () {
			if (!myData.page.haveNextPage) {
				toastr.error("没有下一页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] += 1;
			this.getInfo(queryData)
		},
		lastPage () {
			if (myData.page.isLastPage) {
				toastr.error("已经是在尾页了哦~");
				return;
			}
			queryData["secondDistatisVO.currPage"] = myData.page.totalPage;
			this.getInfo(queryData)
		},
		toPage () {
			let pageIndex = $('#toPageInput').val();
			if (pageIndex < 1 || pageIndex > myData.page.totalPage) {
				toastr.error("输入的数字不在页数范围内,请检查页码");
				return;
			}
			queryData["secondDistatisVO.currPage"] = pageIndex;
			this.getInfo(queryData)
		},
	},
	mounted () {
		this.getInfo(queryData);


		//获取所有的业务
		$.post('/jwcpxt/Service/list_serviceDefinition_all', {}, response => {
			let str = `<option value="">全部</option>`;
			response.forEach(function(elt, i) {
				str += `<option value="${elt.jwcpxt_service_definition_id}">${elt.service_definition_describe}</option>`;
			})
			$('select[name="secondDistatisVO.searchService"]').html(str).selectpicker('refresh');
		}, 'json');
	},
})

function changeQuery(event) {
	queryData[$(event).attr('name')] = $(event).val();
	MyVm.getInfo(queryData);
}

$.datetimepicker.setLocale('ch');
$('.mydate').datetimepicker({
	yearStart : 1900, // 设置最小年份
	yearEnd : 2050, // 设置最大年份
	yearOffset : 0, // 年偏差
	timepicker : false, // 关闭时间选项
	format : 'Y-m-d', // 格式化日期年-月-日
	minDate : '1900/01/01', // 设置最小日期
	maxDate : '2050/01/01', // 设置最大日期
});