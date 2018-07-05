
let myData = {
	Vo : [],
	page : {
		haveNextPage : false,
		havePrePage : false,
		isFirstPage : false,
		isLastPage : false,
	},
	addQuestionModalData : {},
	checkQuestionModalData : {},
};

let vm = new Vue({
	el : "#content",
	data : myData,
	methods : {
		getInfo (pramas) {
			$.post('', pramas, response => {

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
	mounted () {},
})