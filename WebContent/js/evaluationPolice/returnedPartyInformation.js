$(function() {
	let myData = {
		returnedPartyInfomation : {},
	};

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			getInfo () {
				$.post('/jwcpxt/Service/get_notServiceClient_byServiceClientId', '', response => {
					returnedPartyInfomation = response;
				}, 'json')
			}
		},
		mounted () {
			this.getInfo();
		},
	})
})