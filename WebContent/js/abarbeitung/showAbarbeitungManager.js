/**
 * 
 */
$(function(){
	// 获得所有单位
	$.ajax({
		url : '/jwcpxt/Unit/list_unit_all',
		type : 'GET',
		success : function(data) {
			var unitList = JSON.parse(data);
			for (var i = 0; i < unitList.length; i++) {
				$('#searchUnit').html(
						$('#searchUnit').html() + "<option value='"
								+ unitList[i].jwcpxt_unit_id + "'>"
								+ unitList[i].unit_name + "</option>");
			}
		}
	})
})