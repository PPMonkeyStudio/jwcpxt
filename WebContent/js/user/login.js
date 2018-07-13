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
			if(data==1){
				toastr.success('用户登录');
			}else if(data==2){
				toastr.success('单位登陆');
			}else{
				toastr.error('登陆失败')
			}
		}
	})
}