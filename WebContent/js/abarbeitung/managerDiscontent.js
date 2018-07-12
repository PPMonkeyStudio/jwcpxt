/**
 * 
 */

function refuseDiscontent(event) {
	$
			.confirm({
				title : '驳回反馈',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<div><form id="refuseDiscontentForm">'
						+ '<label>审核意见：</label><textarea name="dissatisfiedFeedback.dissatisfied_feedback_audit_opinion" class="form-control"></textarea>'
						+ '</form></div>',
				buttons : {
					cancel : {
						text : '关闭',
						btnClass : 'btn-red',
						action : function() {

						}
					},
					save : {
						text : '驳回',
						btnClass : 'btn-blue',
						action : function() {
							var formData = new FormData(document
									.getElementById("refuseDiscontentForm"));
							formData
									.append(
											"dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id",
											event.id);
							$
									.ajax({
										url : '/jwcpxt/DissatisfiedFeedback/update_dissatisfiedFeedbackState_toReject',
										type : 'POST',
										data : formData,
										processData : false,
										contentType : false,
										success : function(data) {
											if (data == 1) {
												toastr.success("驳回成功！");
												loadData();
											} else {
												toastr.error("驳回失败！");
											}
										}
									})
						}
					}
				},
			})
}

function pushDiscontent(event) {

}

function showDiscontent(event) {

}