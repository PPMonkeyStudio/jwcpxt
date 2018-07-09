/**
 * 
 */

function viewRectification(event) {
	var jc = $
			.confirm({
				title : '分配测评员',
				type : 'blue',
				boxWidth : '800px',
				useBootstrap : false,
				content : '<textarea id="rectification_content"></textarea>',
				buttons : {
					pass : {
						text : '审核通过',
						btnClass : 'btn-green',
						action : function() {
							$
									.ajax({
										url : '/jwcpxt/DissatisfiedFeedback/update_feedbackRectificationState_byRectificationId',
										type : 'POST',
										data : {
											'feedbackRectification.jwcpxt_feedback_rectification_id' : event.id,
											'feedbackRectification.feedback_rectification_state' : 1
										},
										success : function(data) {
											if (data == '1') {
												toastr.success("操作成功");
												loadData();
											} else {
												toastr.error("操作失败");
											}
										}
									})
						}
					},
					nopass : {
						text : '禁止通过',
						btnClass : 'btn-warning',
						action : function() {
							$
									.ajax({
										url : '/jwcpxt/DissatisfiedFeedback/update_feedbackRectificationState_byRectificationId',
										type : 'POST',
										data : {
											'feedbackRectification.jwcpxt_feedback_rectification_id' : event.id,
											'feedbackRectification.feedback_rectification_state' : 2
										},
										success : function(data) {
											if (data == '1') {
												toastr.success("操作成功");
												loadData();
											} else {
												toastr.error("操作失败");
											}
										}
									})
						}
					},
					cancel : {
						text : '关闭',
						btnClass : 'btn-red',
						action : function() {

						}
					}
				},
				onContentReady : function() {
					$
							.ajax({
								url : '/jwcpxt/DissatisfiedFeedback/get_feedbackRectification_byRectificationId?feedbackRectification.jwcpxt_feedback_rectification_id='
										+ event.id,
								type : 'get',
								success : function(data) {
									var feedback_rectification = JSON
											.parse(data);
									if (feedback_rectification.feedback_rectification_state == '0') {

									} else {
										jc.buttons.pass.hide();
										jc.buttons.nopass.hide();
									}
									$('#rectification_content')
											.val(
													feedback_rectification.feedback_rectification_content);

								}
							})
				}
			})
}