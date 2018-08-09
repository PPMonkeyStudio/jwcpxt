function uploadExcel() {
	let uploadExcelConfirm = $.confirm({
		title : '上传文件',
		type : 'dark',
		boxWidth : '500px',
		useBootstrap : false,
		content : `
		 <div id="uploadExcelConfirm">
			<button style="float:left;" type="button" class="btn btn-primary">选择文件</button>
			<p style="float:left; margin-top:5px;"></p>
			<input type="file" style="display:none;">
		</div>
		`,
		onContentReady : function() {
			uploadExcelConfirm.$content.find('#uploadExcelConfirm button').click(function() {
				$(this).siblings('input').click();
			});
			uploadExcelConfirm.$content.find('#uploadExcelConfirm input').change(function() {
				$(this).siblings('p').text('  文件名称：' + this.files[0].name);
			})
		},
		buttons : {
			save : {
				text : '上传',
				btnClass : 'btn-blue',
				action : function() {
					let flag;
					if (uploadExcelConfirm.$content.find('#uploadExcelConfirm input').prop('files')[0])
						flag = true;
					if (flag) {
						var formData = new FormData();
						formData.append("file", uploadExcelConfirm.$content.find('#uploadExcelConfirm input').prop('files')[0])
						$('#uploadIcon').show();
						$.ajax({
							url : '/jwcpxt/User/uploadExcel',
							type : 'POST',
							data : formData,
							processData : false,
							contentType : false,
							success : function(data) {
								if (data == "success") {
									toastr.success("上传成功！");
								} else {
									toastr.error("上传失败！");
								}
								$('#uploadIcon').hide();
							}
						})
					} else {
						toastr.error("不能有空项");
						return false;
					}
				}
			},
			cancel : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {}
			}
		}
	})

}