$(function() {
	let myData = {
		definitionId : getUrlParam('definitionId'),
		questionVO : [],
		page : {
			haveNextPage : false,
			havePrePage : false,
			isFirstPage : false,
			isLastPage : false,
		},
		addQuestionModalData : {},
		checkQuestionModalData : {}
	};

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			getInfo (pramas) {
				$.post('/jwcpxt/Question/get_questionVO', pramas, response => {
					myData.questionVO = response;
				}, 'json');
			},
			pageInit () {
				this.haveNextPage = currentPage < this.totalPage;
				this.havePrePage = currentPage > 1;
				this.isFirstPage = currentPage == 1;
				this.isLastPage = currentPage >= this.totalPage;
			},
			nextPage (pageIndex) {},
			prePage (pageIndex) {},
			firstPage (pageIndex) {},
			lastPage (pageIndex) {},
			toPage (pageIndex) {},
		},
		mounted () {
			this.getInfo({
				"questionVO.currPage" : 1,
				"questionVO.serviceDefinitionDTO.serviceDefinition.jwcpxt_service_definition_id" : myData.definitionId
			});
			console.log('sss');
		},
	})
})

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}