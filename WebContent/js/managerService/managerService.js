/**
 * 
 */

function intoQuestion(event) {
	window.location.href = "/jwcpxt/Skip/skipQuestionnaireDetails?definitionId="
			+ event.id;
}

var interfaceVue;

function showInterface(event) {
	$
			.confirm({
				title : '接口管理',
				type : 'blue',
				boxWidth : '1000px',
				useBootstrap : false,
				content : '<button id="'
						+ event.id
						+ '" onclick="addInterface(this)" class="btn btn-default"><i class="ti-plus"></i>增加接口</button><table id="showInterface" class="table table-striped" style="text-align: center;"><thead><tr>'
						+ '<td>服务编号</td>' + '<td>接口唯一标识</td>'
						+ '<td>资源用户名</td>' + '<td>机器ip</td>' + '<td>操作</td>'
						+ '<td></td>' + '</tr></thead>'
						+ '<tbody><template v-for="">' + '<tr></tr>'
						+ '</template></tbody>' + '</table>',
				buttons : {
					cancel : {
						text : '关闭',
						btnClass : 'btn-blue',
						action : function() {
							loadData();
						}
					}
				},
				onContentReady : function() {
					interfaceVue = new Vue({
						el : '#showInterface',
						data : {
							interfaceList : ''
						}
					})
					// 查询所有子单位
					loadDataInterface(event.id);
				}
			})
}

function loadDataInterface(id) {
	$.ajax({
		url:'/jwcpxt/Service/list_serviceGrab_byServiceDefinitionId?serviceDefinition.jwcpxt_service_definition_id='+id,
		type:'GET',
		success:function(data){
			interfaceVue.interfaceList = JSON.parse(data);
		}
	})
}

function addInterface(event) {
	$
			.confirm({
				title : '增加接口',
				type : 'orange',
				boxWidth : '900px',
				useBootstrap : false,
				content : '<form id="addInterface"><table style="margin:0 auto;" class="table table-bordered">'
						+ '<tr><td>服务编号</td><td><input type="text" name="serviceGrab.service_grab_service_num" class="form-control"></td>'
						+ '<td>接口唯一标识</td><td><input type="text" name="serviceGrab.service_grab_interface_identifying" class="form-control"></td>'
						+ '<td>使用机器ip</td><td><input type="text" name="serviceGrab.service_grab_machine_ip" class="form-control"></td></tr>'

						+ '<tr><td>资源用户名</td><td><input type="text" name="serviceGrab.service_grab_source_username" class="form-control"></td>'
						+ '<td>资源密码</td><td><input type="text" name="serviceGrab.service_grab_source_password" class="form-control"></td>'
						+ '<td>业务项目名</td><td><input type="text" name="serviceGrab.service_grab_project_name" class="form-control"></td></tr>'

						+ '<tr><td>是否单表</td>'
						+ '<td><select class="form-control" name="serviceGrab.service_grab_single_table"><option value="1">是</option><option value="2">否</option></select></td>'
						+ '<td>接口名一</td><td><input type="text" name="serviceGrab.service_grab_interface_one" class="form-control"></td>'
						+ '<td>接口名二</td><td><input type="text" name="serviceGrab.service_grab_interface_two" class="form-control"></td></tr>'

						+ '<tr><td>业务唯一识别编号</td><td><input type="text" name="serviceGrab.service_grab_field_name" class="form-control"></td>'
						+ '<td>当事人姓名</td><td><input type="text" name="serviceGrab.service_grab_name_field" class="form-control"></td>'
						+ '<td>当事人性别</td><td><input type="text" name="serviceGrab.service_grab_sex_field" class="form-control"></td></tr>'

						+ '<tr><td>当事人电话</td><td><input type="text" name="serviceGrab.service_grab_phone_field" class="form-control"></td>'
						+ '<td>业务办理时间</td><td><input type="text" name="serviceGrab.service_grab_handle_time_gield" class="form-control"></td>'
						+ '<td>连接1</td><td><input type="text" name="serviceGrab.service_grab_connect_one_field" class="form-control"></td></tr>'

						+ '<td>连接2</td><td><input type="text" name="serviceGrab.service_grab_connect_two_field" class="form-control"></td></tr>'
						+ '</table></form>',
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
							var formData = new FormData(document
									.getElementById("addInterface"));
							formData.append('serviceGrab.service_grab_service_definition',event.id);
							$.ajax({
								url : '/jwcpxt/Service/save_serviceGrab',
								type : 'POST',
								data : formData,
								processData : false,
								contentType : false,
								success : function(data) {
									if (data == 1) {
										toastr.success("保存成功");
										loadDataInterface(event.id);
									} else {
										toastr.error("保存失败");
									}
								}
							})

						}
					}
				}
			})
}

function addService() {
	$
			.confirm({
				title : '新建业务',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="add_service">'
						+ '<label>业务名：</label><input id="serviceDefinition_name_add" class="form-control" name="serviceDefinition.service_definition_describe">'
						+ '</form></div>',
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
							if ($('#serviceDefinition_name_add').val() != '') {
								var formData = new FormData(document
										.getElementById("add_service"));
								$
										.ajax({
											url : '/jwcpxt/Service/save_serviceDefinition',
											type : 'POST',
											data : formData,
											processData : false,
											contentType : false,
											success : function(data) {
												if (data == 1) {
													toastr.success("保存成功");
													loadData();
												} else {
													toastr.error("保存失败");
												}
											}
										})
							} else {
								toastr.error("不能有空项");
								return false;
							}

						}
					}
				},
				onContentReady : function() {
					$.ajax({
						url : '/jwcpxt/Unit/list_unit_all',
						type : 'GET',
						success : function(data) {
							var unitList = JSON.parse(data);
							for (var i = 0; i < unitList.length; i++) {
								$('#unit_').html(
										$('#unit_').html() + "<option value='"
												+ unitList[i].jwcpxt_unit_id
												+ "'>" + unitList[i].unit_name
												+ "</option>");
							}
						}
					})
				}
			})
}

function updateService(event) {
	$
			.confirm({
				title : '修改业务',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="update_service">'
						+ '<label>业务名：</label><input id="serviceDefinition_name_update" class="form-control" name="serviceDefinition.service_definition_describe">'
						+ '</form></div>',
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
							if ($('#serviceDefinition_name_add').val() != '') {
								var formData = new FormData(document
										.getElementById("update_service"));
								formData
										.append(
												"serviceDefinition.jwcpxt_service_definition_id",
												event.id);
								$
										.ajax({
											url : '/jwcpxt/Service/update_serviceDefinition',
											type : 'POST',
											data : formData,
											processData : false,
											contentType : false,
											success : function(data) {
												if (data == 1) {
													toastr.success("修改成功");
													loadData();
												} else {
													toastr.error("修改失败");
												}
											}
										})
							} else {
								toastr.error("不能有空项");
								return false;
							}

						}
					}
				},
				onContentReady : function() {
					$
							.ajax({
								url : '/jwcpxt/Service/get_serviceDefinition_byServiceDefinitionID?serviceDefinition.jwcpxt_service_definition_id='
										+ event.id,
								type : 'GET',
								success : function(data) {
									var serviceDO = JSON.parse(data);
									$('#serviceDefinition_name_update')
											.val(
													serviceDO.service_definition_describe);
								}
							})
				}
			})
}
