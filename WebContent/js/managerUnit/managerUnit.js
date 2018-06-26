/**
 * 
 */

function addUnit() {
	$.confirm({
		title : '新建单位',
		type : 'blue',
		boxWidth : '500px',
		useBootstrap : false,
		content : '<div><label>单位整改员：</label><select name="" id="add_user_name" title="请选择一个整改员" class="selectpicker form-control" data-live-search="true" ></select>'+
		'<br><br><br><label>单位名称：</label><input id="add_unit_name" name="" class="form-control"></div>',
		buttons : {
			cancel : {
				text : '关闭',
				btnClass : 'btn-red',
				action : function() {
				
				}
			},
			save : {
				text : '保存',
				btnClass : 'btn-blue',
				action : function() {
					var unit_reorganizer = $('#add_user_name').selectpicker('val');
					var unit_name=$('#add_unit_name').val();
					console.log(unit_name)
					if(add_unit_name!=''){
						$.ajax({
							url:'/jwcpxt/Unit/add_unit',
							type:'POST',
							data:{
								'unit.unit_reorganizer':unit_reorganizer,
								'unit.unit_name':unit_name
							},
							success:function(data){
								if(data==1){
									toastr.success("新建成功！");
									loadData();
								}else{
									toastr.error("新建失败！");
								}
							}
						})	
					}else{
						toastr.error("有空项无法提交");
						return false;
					}
				}
			}
		},
		onContentReady:function(){
			$.ajax({
				url:'/jwcpxt/User/list_user_all',
				type:'GET',
				success:function(data){
					var userList = JSON.parse(data);
					for(var i = 0;i<userList.length;i++){
						$('#add_user_name').append("<option value='"+userList[i].jwcpxt_user_id+"'>"+userList[i].user_name+"</option>");
					}
					$('.selectpicker').selectpicker('render');
					$('.selectpicker').selectpicker('refresh');		
				}
			})
			
		}
	})
}