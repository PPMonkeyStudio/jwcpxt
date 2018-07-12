/**
 * hy
 */
$(function() {
	let myData = {
		userInfo : []
	}
	let myVue = new Vue({
		el : "#managerContent",
		data : myData,
		methods : {
			getInfo () {
				$.post('/jwcpxt/User/get_userVO', {}, response => {
					myData.userInfo = response.userList;
				}, 'json');
			},
			addUser () {
				addUser();
			},
			banUser (index) {
				banUser(myData.userInfo[index]);
			},
			resetPassword (index) {
				resetPassword(myData.userInfo[index]);
			}
		},
		mounted () {
			this.getInfo();
		},
	});

	//增加用户
	function addUser() {
		let addUserConfirm = $.confirm({
			title : '新建用户',
			type : 'dark',
			boxWidth : '500px',
			useBootstrap : false,
			smoothContent : false,
			content : `
			 <div class="form-group">
			    <label for="account">帐号</label>
			    <input type="text" class="form-control" id="account" placeholder="帐号">
			  </div>
			 <div class="form-group">
			    <label for="password">姓名</label>
			    <input type="text" class="form-control" id="password" placeholder="姓名">
			 </div>
			 <div class="form-group">
			    <label for="type">类型</label>
			    <select class="form-control" id="type" placeholder="类型">
			    	<option vlaue=""></option>
					<option value="1">测评员</option>
			    	<option value="2">统计员</option>
			    </select>
			 </div>
			`,
			buttons : {
				save : {
					text : '保存',
					btnClass : 'btn-blue',
					action : function() {
						let account = addUserConfirm.$content.find('#account').val();
						if (!account) {
							toastr.error('帐号不能为空');return false;
						}
						let password = addUserConfirm.$content.find('#password').val();
						if (!password) {
							toastr.error('密码不能为空');return false;
						}
						let type = addUserConfirm.$content.find('#type').val();
						if (!type) {
							toastr.error('请选择类型');return false;
						}
						let params = {
							"user.user_account" : account,
							"user.user_name" : password,
							"user.user_type" : type
						}
						$.post('/jwcpxt/User/save_user', params, response => {
							if (response == 1) {
								toastr.success("保存成功");
								myVue.getInfo();
							} else {
								toastr.error("保存失败");
							}
						}, 'text');
					}
				},
				cancel : {
					text : '关闭',
					btnClass : 'btn-default',
					action : function() {}
				}
			},
		})
	}

	function banUser(user) {
		let type = user.user_state == 1;
		$.confirm({
			title : type ? '确定禁用?' : '确定解禁?',
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
						$.post('/jwcpxt/User/ban_user_byUserID', {
							'user.jwcpxt_user_id' : user.jwcpxt_user_id
						}, response => {
							if (response == "1") {
								if (type) toastr.success("禁用成功");
								else toastr.success("解禁成功");
								myVue.getInfo();
							} else if (response == "-1") {
								if (type) toastr.success("禁用失败");
								else toastr.success("解禁失败");
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

	//重置密码
	function resetPassword(user) {
		$.confirm({
			title : '重置密码?',
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
						$.post('/jwcpxt/User/reset_userPassword_byUserID', {
							'user.jwcpxt_user_id' : user.jwcpxt_user_id
						}, response => {
							if (response == "1") {
								toastr.success("重置成功");
								myVue.getInfo();
							} else if (response == "-1") {
								toastr.success("重置失败");
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

})

/*
//更新信息
function updateUser(user) {
	$.confirm({
		title : '修改用户',
		type : 'dark',
		boxWidth : '500px',
		useBootstrap : false,
		content : `
			<div class="form-group">
			   <label for="type"></label>
			   <select type="email" class="form-control" id="type" value="${user.user_type}">
				<option value="1">测评员</option>
				<option value="2">统计员</option>
			   </select>
			</div>
			<div class="form-group">
			   <label for="state"></label>
			   <select type="password" class="form-control" id="state" value="${user.user_state}">
				<option value="1">活动</option>
				<option value="2">封禁</option>
			   </select>
			</div>
			`,
		buttons : {
			cancel : {
				text : '重置密码',
				btnClass : 'btn-danger',
				action : function() {}
			},
			save : {
				text : '保存修改',
				btnClass : 'btn-blue',
				action : function() {

					$.post('', '', '', '');

					$.ajax({
						url : '/jwcpxt/User/update_user',
						type : 'POST',
						data : formData,
						processData : false,
						contentType : false,
						success : function(data) {
							if (data == '1') {
								toastr.success("保存成功");
								loadData();
							} else {
								toastr.error("保存失败");
							}
						}
					})
				}
			},
			cancel : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {}
			}
		},
	})
}

function deleteUser(event) {
	$.confirm({
		title : '删除用户',
		type : 'red',
		icon : 'fa fa-warning',
		content : '确定要删除吗？',
		autoClose : 'cancelAction|8000',
		buttons : {
			deleteUser : {
				text : '删除',
				btnClass : 'btn-blue',
				action : function() {
					$.ajax({
						url : '/jwcpxt/User/delete_user?user.jwcpxt_user_id='
							+ event.id,
						type : 'GET',
						success : function(data) {
							if (data == '1') {
								toastr.success("删除成功");
								loadData();
							} else {
								toastr.error("删除失败");
							}
						}
					})
				}
			},
			cancelAction : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {}
			}
		}
	})
}*/