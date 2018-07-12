$(function() {
	let myData = {
		definitionId : getUrlParam('definitionId'), //业务定义ID
		serviceClientId : getUrlParam('serviceClientId'), //业务当事人ID
		serviceClien : {},
		serviceDefinition : {},
		questionData : [],
	};
	let listAnswerDTO = [];
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
					myData.serviceClien = response;
				}, 'json')
				//获取所有问题
				$.post('/jwcpxt/Question/list_questionDTO_byServiceDefinition', {
					"serviceDefinition.jwcpxt_service_definition_id" : myData.definitionId
				}, response => {
					myData.questionData = response;
				}, 'json')
			},
			checkOption ($event, index) {
				let answer = {
					"question.jwcpxt_question_id" : myData.questionData[index].question.jwcpxt_question_id, //所属问题id
					"option.jwcpxt_option_id" : $event.target.attributes.optionid.value, //所属选项id
				//"serviceClient.jwcpxt_service_client_id" : myData.serviceClientId, //当事人id
				}
				listAnswerDTO[index] = answer;
			},
			inputTextarea ($event, index) {
				let answer = {
					"question.jwcpxt_question_id" : myData.questionData[index].question.jwcpxt_question_id, //所属问题id
					//"serviceClient.jwcpxt_service_client_id" : myData.serviceClientId, //当事人id
					"answerOpen.answer_open_content" : $event.target.value //开放题回答的内容
				}
				listAnswerDTO[index] = answer;
			},
			finishReturned () {
				let falg = false;
				if (listAnswerDTO.length > 0) {
					let length = this.questionData.length;
					for (var int = 0; int < length; int++) {
						if (!listAnswerDTO[int]) {
							toastr.error('第' + (int + 1) + '条问题没有进行回访，请查看!');
							return;
						}
					}
					falg = true;
				} else {
					toastr.error('还未开始回访，请进行回访!');
				}
				if (falg)
					$.confirm({
						title : "确定结束?",
						icon : 'fa fa-warning',
						type : "red",
						autoClose : 'close|10000',
						smoothContent : false,
						content : false,
						buttons : {
							tryAgain : {
								text : '确认',
								btnClass : 'btn-red',
								action : function() {
									/* 数据格式转换,方便存入到后台的DTO中*/
									let params = {
										"serviceClient.jwcpxt_service_client_id" : myData.serviceClientId
									};
									listAnswerDTO.forEach(function(elt, i) {
										for (let item in elt) {
											params['listAnswerDTO[' + i + '].' + item] = elt[item];
										}
									})
									$.post('/jwcpxt/Question/save_answer', params, response => {
										if (response == "1") {
											toastr.success("回访结束");
										} else if (response == "-1") {
											toastr.error("结束失败");
										}
									}, 'text');
								}
							},
							close : {
								text : '取消',
								btnClass : 'btn-default',
								keys : [ 'esc' ],
								action : function() {}
							}
						}
					});
			}
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
					$('input').on('ifChecked', function(event) {
						vm.checkOption(event, event.target.name);
					});
				}, 1000);
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