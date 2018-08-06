/**
 * 
 */

function refuseDiscontent(event) {
	$.confirm({
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
				action : function() {}
			},
			save : {
				text : '驳回',
				btnClass : 'btn-blue',
				action : function() {
					if (!$('#refuseDiscontentForm textarea').val()) {
						toastr.error('意见不能为空');
						return false;
					}
					var formData = new FormData(document.getElementById("refuseDiscontentForm"));
					formData.append("dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id", event.id);
					$.ajax({
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
	$
		.confirm({
			title : '推送反馈',
			type : 'blue',
			boxWidth : '800px',
			useBootstrap : false,
			content : `<form id="pushDiscontentForm"><table class="table table-bordered">'
				<tr>
					<td>
						<span style="color:red;">*</span>问题标题
					</td>
					<td colspan="3">
						<select name="feedbackRectification.feedback_rectification_title" class="form-control mustWrite">
							<option value="">请选择问题标题</option>
							<option value="诉求">诉求</option>
							<option value="投诉">投诉</option>
							<option value="意见/建议">意见/建议</option>
							<option value="举报">举报</option>
							<option value="不满意问题">不满意问题</option>
							<option value="不太满意问题">不太满意问题</option>
							<option value="报警受理不及时">报警受理不及时</option>
							<option value="没有出现场">没有出现场</option>
							<option value="其他">其他</option>
						</select>
						<!-- <input type="text" name="feedbackRectification.feedback_rectification_title" class="form-control mustWrite"> -->
					<td>
				</tr>
				<tr>
					<td>
						<span style="color:red;">*</span>收集渠道
					</td>
					<td colspan="3">
						<select name="feedbackRectification.feedback_rectification_collect_channel" class="form-control mustWrite">
							<option value="">请选择收集渠道</option>
							<option value="电话回访">电话回访</option>
							<option value="短信回访">短信回访</option>
							<option value="群众来电">群众来电</option>
							<option value="其他">其他</option>
						</select>
						<!-- <input type="text" name="feedbackRectification.feedback_rectification_collect_channel" class="form-control mustWrite"> -->
					<td>
				</tr>
				<tr>
					<td><span style="color:red;">*</span>问题简述</td>
					<td colspan="3"><textarea type="text" name="feedbackRectification.feedback_rectification_question_describe" class="form-control mustWrite"></textarea><td>
				</tr>
				<tr>
					<td><span style="color:red;">*</span>审核意见</td>
					<td colspan="3"><textarea type="text" name="dissatisfiedFeedback.dissatisfied_feedback_audit_opinion" class="form-control mustWrite"></textarea><td>
				</tr>
				</table></form>`,
			buttons : {
				cancel : {
					text : '关闭',
					btnClass : 'btn-red',
					action : function() {}
				},
				save : {
					text : '推送',
					btnClass : 'btn-blue',
					action : function() {

						var flag;
						var mustWrite = document
							.getElementsByClassName("mustWrite");
						for (var i = 0; i < mustWrite.length; i++) {
							if (mustWrite[i].value == "") {
								flag = false;
							} else {
								flag = true;
							}
						}
						if (flag) {
							var formData = new FormData(document
								.getElementById("pushDiscontentForm"));
							formData
								.append(
									"dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id",
									event.id);
							$
								.ajax({
									url : '/jwcpxt/DissatisfiedFeedback/updade_dissatisfiedFeedbackState_toPush',
									type : 'POST',
									data : formData,
									processData : false,
									contentType : false,
									success : function(data) {
										if (data == 1) {
											toastr.success("推送成功！");
											loadData();
										} else {
											toastr.error("推送失败！");
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
		})
}

function showDiscontent(event) {
	$
		.confirm({
			title : '查看审核意见',
			type : 'blue',
			boxWidth : '500px',
			useBootstrap : false,
			content : '<label>审核意见：</label><textarea readonly="true" id="show_dissatisfied_feedback_audit_opinion" class="form-control"></textarea>',
			buttons : {
				cancel : {
					text : '关闭',
					btnClass : 'btn-blue',
					action : function() {}
				},
			},
			onContentReady : function() {
				$.ajax({
					url : '/jwcpxt/DissatisfiedFeedback/get_dissatisfiedFeedbackDO_byId?dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id='
						+ event.id,
					type : 'GET',
					success : function(data) {
						$(
							'#show_dissatisfied_feedback_audit_opinion')
							.val(
								JSON.parse(data).dissatisfied_feedback_audit_opinion);
					}
				})
			}
		})
}