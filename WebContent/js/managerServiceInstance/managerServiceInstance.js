/**
 * 
 */

function returnService() {
	window.location.href = "/jwcpxt/Skip/skipManagerService";
}

function allocationHuman(event) {
	$
			.confirm({
				title : '分配测评员',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div>'
						+ '<label>选择测评员：</label><select id="updateHuman" class="form-control">'
						+ '</select>' + '</div>',
				buttons : {
					cancel : {
						text : '关闭',
						btnClass : 'btn-red',
						action : function() {

						}
					},
					save : {
						text : '保存',
						btnClass : 'btn-blue',
						action : function() {
							$
									.ajax({
										url : '/jwcpxt/Service/distribution_judge',
										type : 'POST',
										data : {
											'serviceInstance.service_instance_judge' : $(
													'#updateHuman').val(),
											'serviceInstance.jwcpxt_service_instance_id' : event.id
										},
										success : function(data) {
											if (data == '1') {
												toastr.success("分配成功");
											} else {
												toastr.error("分配失败")
											}

											loadData();
										}
									})

						}
					}
				},
				onContentReady : function() {
					$
							.ajax({
								url : '/jwcpxt/User/list_user_byJurisdiction',
								type : 'POST',
								data : {
									'user.user_Jurisdiction_evaluate' : 'have',
									'user.user_Jurisdiction_statistics' : '',
									'user.user_Jurisdiction_review' : ''
								},
								success : function(data) {
									var humanList = JSON.parse(data);
									for (var i = 0; i < humanList.length; i++) {
										$('#updateHuman')
												.html(
														$('#updateHuman')
																.html()
																+ '<option style="text-align:center;" value="'
																+ humanList[i].jwcpxt_user_id
																+ '">'
																+ humanList[i].user_name
																+ '</option>');
									}

								}
							})
				}
			})
}
function viewParties(event) {

	$.confirm({
		title : '查看当事人',
		type : 'blue',
		boxWidth : '1000px',
		useBootstrap : false,
		content : '<table class="table table-striped" style="text-align:center;"><thead>'
				+ '<tr><td>姓名</td><td>性别</td><td>电话</td><td>是否回访</td></tr>'
				+ '</thead>' + '<tbody id="partiesTable">' + '</tbody>'
				+ '</table>',
		buttons : {
			cancel : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {

				}
			}
		},
		onContentReady : function() {
			$.ajax({
				url : '/jwcpxt/Service/list_client_byServiceInstanceID',
				type : 'POST',
				data : {
					'serviceInstance.jwcpxt_service_instance_id' : event.id
				},
				success : function(data) {
					var reponseText = JSON.parse(data);
					for (var i = 0; i < reponseText.length; i++) {
						if(reponseText[i].service_client_visit=='1'){
							reponseText[i].service_client_visit='是';
						}else{
							reponseText[i].service_client_visit='否';
						}
						$('#partiesTable').html($('#partiesTable').html()+'<tr><td>'+reponseText[i].service_client_name+'</td>'
								+'<td>'+reponseText[i].service_client_sex+'</td>'
								+'<td>'+reponseText[i].service_client_phone+'</td>'
								+'<td>'+reponseText[i].service_client_visit+'</td>'
								+'</tr>')
					}
				}
			})
		}
	})

}