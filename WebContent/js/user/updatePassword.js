/**
 * 
 */

function updatePasswordUser(event) {
	$
			.confirm({
				title : '修改密码',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<form id="update_"><label>新密码</label><input name="user.user_password" class="form-control" type="password" id="one_password">'
						+ '<label>重复输入</label><input class="form-control" type="password" id="two_password"></form>',
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

							if ($('#one_password').val() == $('#two_password')
									.val()) {

								var formData = new FormData(document
										.getElementById("update_"));
								formData
										.append("user.jwcpxt_user_id", event.id);
								$.ajax({
									url : '/jwcpxt/User/update_userPassword',
									type : 'POST',
									data : formData,
									processData : false,
									contentType : false,
									success : function(data) {
										if (data == 1) {
											toastr.success("修改成功！");
											loadData();
										} else {
											toastr.error("修改失败！");
										}
									}
								})
							}
							else{
								toastr.error("两次密码输入不一致");
							}
						}
					}
				}

			})
}
function updatePasswordUnit(event) {
	$
	.confirm({
		title : '修改密码',
		type : 'blue',
		boxWidth : '500px',
		useBootstrap : false,
		content : '<form id="update_"><label>新密码</label><input name="unit.unit_password" class="form-control" type="password" id="one_password">'
				+ '<label>重复输入</label><input type="password" id="two_password" class="form-control"></form>',
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

					if ($('#one_password').val() == $('#two_password')
							.val()) {

						var formData = new FormData(document
								.getElementById("update_"));
						formData
								.append("unit.jwcpxt_unit_id", event.id);
						$.ajax({
							url : '/jwcpxt/Unit/update_unitPassword',
							type : 'POST',
							data : formData,
							processData : false,
							contentType : false,
							success : function(data) {
								if (data == 1) {
									toastr.success("修改成功！");
									loadData();
								} else {
									toastr.error("修改失败！");
								}
							}
						})
					}
					else{
						toastr.error("两次密码输入不一致");
					}
				}
			}
		}

	})
}