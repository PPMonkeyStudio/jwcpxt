/**
 * 
 */

function addUser() {
	$
			.confirm({
				title : '新建用户',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="add_user">'
						+ '<label>账号：</label><input id="user_account_add" class="form-control" name="user.user_account">'
						+ '<label>姓名：</label><input id="user_name_add" class="form-control" name="user.user_name">'
						+ '<label>所属单位：</label><select id="unit_" class="form-control" name="user.user_unit"></select>'
						+ '<label>测评权限：</label><select class="form-control" name="user.user_Jurisdiction_evaluate"><option value="none">无权限</option><option value="have">有权限</option></select>'
						+ '<label>统计权限：</label><select class="form-control" name="user.user_Jurisdiction_statistics"><option value="none">无权限</option><option value="have">有权限</option></select>'
						+ '<label>整改权限：</label><select class="form-control" name="user.user_Jurisdiction_review"><option value="none">无权限</option><option value="have">有权限</option></select>'
						+ '<label>状态：</label><select class="form-control" name="user.user_state"><option value="1">正常</option><option value="2">禁用</option></select>'
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
							if ($('#user_account_add').val() != ''
									&& $('#user_name_add').val()) {
								var formData = new FormData(document
										.getElementById("add_user"));
								$.ajax({
									url : '/jwcpxt/User/save_user',
									type : 'POST',
									data : formData,
									processData : false,
									contentType : false,
									success : function(data) {
										if (data == 1) {
											toastr.success("保存成功");
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