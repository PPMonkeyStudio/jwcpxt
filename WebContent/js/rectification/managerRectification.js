/**
 * 
 */

function handleEnd(event) {
	$
			.confirm({
				title : '整改',
				type : 'blue',
				boxWidth : '500px',
				useBootstrap : false,
				content : '<form id="handleEnd"><label> 反馈情况简述</label><textarea name="feedbackRectification.feedback_rectification_content" class="form-control"></textarea></form>',
				buttons : {
					cancel : {
						text : '关闭',
						btnClass : 'btn-red',
						action : function() {

						}
					},
					save : {
						text : '整改',
						btnClass : 'btn-blue',
						action : function() {
							var formData = new FormData(document
									.getElementById("handleEnd"));
							formData
									.append(
											"feedbackRectification.jwcpxt_feedback_rectification_id",
											event.id);
							$
									.ajax({
										url : '/jwcpxt/DissatisfiedFeedback/update_dissatisfiedFeedbackState_toEnd',
										type : 'POST',
										data : formData,
										processData : false,
										contentType : false,
										success : function(data) {
											if (data == 1) {
												toastr.success("整改成功！");
												loadData();
											} else {
												toastr.error("整改失败！");
											}
										}
									})
						}
					}
				},
			})
}

var transferListVue;

function preview(event) {
	$
			.confirm({
				title : '预览流转单',
				type : 'blue',
				boxWidth : '1000px',
				useBootstrap : false,
				content : '<div id="transferList">编号：{{ transfer.feedback_rectification_no }}'
						+ '<table class="table table-bordered">'
						+ '<tr><td style="width:150px">问题标题</td><td colspan="3">{{ transfer.feedback_rectification_title }}</td></tr>'
						+ '<tr><td>收集渠道</td><td style="width:300px">{{ transfer.feedback_rectification_collect_channel }}</td><td style="width:150px">收集时间</td><td>{{ transfer.feedback_rectification_collect_time }}</td></tr>'
						+ '<tr><td>当事人</td><td>{{ transfer.feedback_rectification_client_name }}</td><td>联系电话</td><td>{{ transfer.feedback_rectification_client_phone }}</td></tr>'
						+ '<tr><td>问题简述</td><td colspan="3">{{ transfer.feedback_rectification_question_describe }}</td></tr>'
						+ '<tr><td>协调情况</td><td colspan="3">转交{{ transfer.feedback_rectification_unit_name }}办理</td></tr>'
						+ '<tr><td>单位联系人</td><td>{{ transfer.feedback_rectification_unit_people }}</td><td>联系电话</td><td>{{ transfer.feedback_rectification_unit_people_phone }}</td></tr>'
						+ '<tr><td>办理情况</td><td><template v-if="transfer.feedback_rectification_handle_state == 1">'
						+ '未办</template> <template v-else>办结'
						+ '</template></td><td>反馈日期</td><td>{{ transfer.feedback_rectification_date }}</td></tr>'
						+ '<tr><td>反馈情况简述</td><td colspan="3">{{ transfer.feedback_rectification_content }}</td></tr>'
						+ '<tr><td>上级主管部门意见</td><td colspan="3">{{ transfer.feedback_rectification_sjzgbm_opinion }}</td></tr>'
						+ '<tr><td>测评中心意见</td><td colspan="3">{{ transfer.feedback_rectification_cpzx_opinion }}</td></tr>'
						+ '</table></div>',
					buttons:{
						cancel:{
							text : '关闭',
							btnClass : 'btn-blue',
							action : function() {

							}
						}
					},
				onContentReady : function() {
					transferListVue = new Vue({
						el : '#transferList',
						data : {
							transfer : ''
						}
					})

					$
							.ajax({
								url : '/jwcpxt/DissatisfiedFeedback/get_feedbackRectficationDO_byId?feedbackRectification.jwcpxt_feedback_rectification_id='
										+ event.id,
								type : 'GET',
								success : function(data) {
									transferListVue.transfer = JSON.parse(data);
								}
							})
				}
			})
}