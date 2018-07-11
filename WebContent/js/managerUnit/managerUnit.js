/**
 * 
 */

function updateUnit(event) {
	$
			.confirm({
				title : '修改单位',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><label>单位整改员：</label><select name="" id="add_user_name" title="请选择一个整改员" class="selectpicker form-control" data-live-search="true" ></select>'
						+ '<br><br><br><label>单位名称：</label><input id="add_unit_name" name="" class="form-control"></div>',
				buttons : {
					cancel : {
						text : '关闭',
						btnClass : 'btn-red',
						action : function() {

						}
					},
					save : {
						text : '修改',
						btnClass : 'btn-blue',
						action : function() {
							var unit_reorganizer = $('#add_user_name')
									.selectpicker('val');
							var unit_name = $('#add_unit_name').val();
							console.log(unit_name)
							if (add_unit_name != '') {
								$
										.ajax({
											url : '/jwcpxt/Unit/update_unit',
											type : 'POST',
											data : {
												'unit.jwcpxt_unit_id' : event.id,
												'unit.unit_reorganizer' : unit_reorganizer,
												'unit.unit_name' : unit_name
											},
											success : function(data) {
												if (data == 1) {
													toastr.success("修改成功！");
													loadData();
												} else {
													toastr.error("修改失败！");
												}
											}
										})
							} else {
								toastr.error("有空项无法提交");
								return false;
							}
						}
					}
				},
				onContentReady : function() {

				}
			})
}

function addUnit() {
	$
			.confirm({
				title : '新建二级单位',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="unitForm">'
						+ '<label>单位名称：</label><input name="unit.unit_name" class="form-control">'
						+ '<label>机构代码：</label><input name="unit.unit_num" class="form-control">'
						+ '<label>单位账号：</label><input name="unit.unit_account" class="form-control">'
						+ '<label>联系电话：</label><input name="unit.unit_phone" class="form-control">'
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
							var formData = new FormData(document.getElementById("unitForm"));
							formData.append("unit.unit_password",);
							formData.append("unit.unit_password",);
							$.ajax({
								url : '/jwcpxt/Unit/save_unit',
								type : 'POST',
								data : formData,
								processData : false,
								contentType : false,
								success : function(data) {
									if (data == 1) {
										toastr.success("新建成功！");
										loadData();
									} else {
										toastr.error("新建失败！");
									}
								}
							})
						}
					}
				},
				onContentReady : function() {

				}
			})
}