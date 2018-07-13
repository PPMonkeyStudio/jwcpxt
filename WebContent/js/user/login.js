/**
 * 
 */

function login(loginType) {
	$.ajax({
		url : '/jwcpxt/LoginAndLogout/login',
		type : 'POST',
		data : {
			'loginType' : loginType,
			'account' : $('#login_username').val(),
			'password' : $('#login_password').val()
		},
		success : function(data) {
			if (data == 1) {
				toastr.success('用户登录');
				window.location.href = "/jwcpxt/Skip/skipSystemIndex";
			} else if (data == 2) {
				toastr.success('单位登陆');
				window.location.href = "/jwcpxt/Skip/skipSystemIndex";
			} else {
				toastr.error('登陆失败')
			}
		}
	})
}

function logout() {
	$.ajax({
		url : '/jwcpxt/LoginAndLogout/logout',
		type : 'GET',
		success : function(data) {
			if (data == 1) {
				window.location.href = "/jwcpxt/login.jsp";
			} else if (data == 2) {
				window.location.href = "/jwcpxt/login.jsp";
			} else {
				toastr.error("登出操作执行失败！");
			}
		}
	})
}