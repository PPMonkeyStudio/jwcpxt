$(function() {
	let myData = {
		definitionId : getUrlParam('definitionId'),
		ready : false,
		questionVO : {},
	};

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			getInfo () {}
		},
		mounted () {
			this.getInfo();
		},
	})
})