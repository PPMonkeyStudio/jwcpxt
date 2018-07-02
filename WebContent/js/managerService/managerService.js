/**
 * 
 */
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
					$.ajax({
						url : '/jwcpxt/Service/get_serviceDefinition_byServiceDefinitionID?serviceDefinition.jwcpxt_service_definition_id='+event.id,
						type : 'GET',
						success : function(data) {
							var serviceDO = JSON.parse(data); 
							$('#serviceDefinition_name_update').val(serviceDO.service_definition_describe);
						}
					})
				}
			})
}
