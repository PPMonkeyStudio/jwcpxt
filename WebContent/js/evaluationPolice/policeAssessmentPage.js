$(function() {
	let myData = {
		definitionId : getUrlParam('definitionId'), //业务定义ID
		serviceClientId : getUrlParam('serviceClientId'), //业务当事人ID
		returnedParty : {},
		serviceDefinition : {},
		questionData : [],
	};

	let vm = new Vue({
		el : "#content",
		data : myData,
		methods : {
			getInfo () {
				//获取业务定义
				$.post('/jwcpxt/Service/get_serviceDefinitionDo_byId', {
					"serviceDefinition.jwcpxt_service_definition_id" : myData.definitionId
				}, response => {
					myData.serviceDefinition = response;
				}, 'json')
				//获取当事人ID
				$.post('/jwcpxt/Service/get_serviceClientDo_byId', {
					"serviceClient.jwcpxt_service_client_id" : myData.serviceClientId
				}, response => {
					myData.returnedParty = response;
				}, 'json')
				//获取所有问题
				$.post('/jwcpxt/Question/list_questionDTO_byServiceDefinition', {
					"serviceDefinition.jwcpxt_service_definition_id" : myData.definitionId
				}, response => {
					myData.questionData = response;
				}, 'json')
			},
		},
		mounted () {
			this.getInfo();
			this.$nextTick(function() {
				setTimeout(function() {
					$('input').iCheck({
						checkboxClass : 'icheckbox_square-blue',
						radioClass : 'iradio_square-blue',
						increaseArea : '20%' // optional
					});
				}, 400);
			})
		},
	});
})


function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}