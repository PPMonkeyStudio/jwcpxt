/**
 * 
 */

function intoInstance(event) {
	window.location.href = "/jwcpxt/Skip/skipServiceInstance?serviceDefinition.jwcpxt_service_definition_id="
			+ event.id;
}

function updateInterface(event) {
	var interfaceId;
	$
			.confirm({
				title : '修改数据抓取接口',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="update_interface">'
						+ '<label>接口名：</label><input class="form-control" name="serviceGrab.service_grab_service_definition" id="interfaceName">'
						+ '<label>数据表名：</label><input class="form-control" name="serviceGrab.service_grab_table" id="tableName">'
						+ '<label>唯一编号：</label><input class="form-control" name="serviceGrab.service_grab_field_nid" id="nid">'
						+ '<label>当事人姓名：</label><input class="form-control" name="serviceGrab.service_grab_field_client_name" id="manName">'
						+ '<label>当事人性别：</label><input class="form-control" name="serviceGrab.service_grab_field_client_sex" id="manSex">'
						+ '<label>当事人电话：</label><input class="form-control" name="serviceGrab.service_grab_field_client_phone" id="manPhone">'
						+ '<label>业务办理时间：</label><input class="form-control" name="serviceGrab.service_grab_field_date" id="manTime">'
						+ '</div></form>',
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
									.getElementById("update_interface"));
							formData
									.append(
											'serviceGrab.jwcpxt_service_grab_id',
											interfaceId);
							$.ajax({
								url : '/jwcpxt/Service/update_serviceGrab',
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
						}
					}
				},
				onContentReady : function() {
					$.ajax({
						url:'/jwcpxt/Service/get_serviceGrabDO_byServiceDefinitionID',
						type:'POST',
						data:{
							'serviceGrab.service_grab_service_definition':event.id
						},
						success:function(data){
							var interfaceDo = JSON.parse(data);
							interfaceId = interfaceDo.jwcpxt_service_grab_id;
							$('#interfaceName').val(interfaceDo.service_grab_Interface);
							$('#tableName').val(interfaceDo.service_grab_table);
							$('#nid').val(interfaceDo.service_grab_field_nid);
							$('#manName').val(interfaceDo.service_grab_field_client_name);
							$('#manSex').val(interfaceDo.service_grab_field_client_sex);
							$('#manPhone').val(interfaceDo.service_grab_field_client_phone);
							$('#manTime').val(interfaceDo.service_grab_field_date);
						}
					})
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
						+ '<label>所属单位：</label><select id="unit_" class="form-control" name="serviceDefinition.service_definition_unit"></select>'
						+ '<label>抽样比例（%）：</label><select class="form-control" name="serviceDefinition.service_definition_sampling_proportion">'
						+ '<option value="0">0</option>'
						+ '<option value="10">10</option>'
						+ '<option value="20">20</option>'
						+ '<option value="30">30</option>'
						+ '<option value="40">40</option>'
						+ '<option value="50">50</option>'
						+ '<option value="60">60</option>'
						+ '<option value="70">70</option>'
						+ '<option value="80">80</option>'
						+ '<option value="90">90</option>'
						+ '<option value="100">100</option>' + '</select>'
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
						+ '<label>抽样比例（%）：</label><select class="form-control" name="serviceDefinition.service_definition_sampling_proportion">'
						+ '<option value="0">0</option>'
						+ '<option value="10">10</option>'
						+ '<option value="20">20</option>'
						+ '<option value="30">30</option>'
						+ '<option value="40">40</option>'
						+ '<option value="50">50</option>'
						+ '<option value="60">60</option>'
						+ '<option value="70">70</option>'
						+ '<option value="80">80</option>'
						+ '<option value="90">90</option>'
						+ '<option value="100">100</option>' + '</select>'
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
