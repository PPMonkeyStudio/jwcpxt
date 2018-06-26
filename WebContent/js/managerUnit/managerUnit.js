/**
 * 
 */

function deleteUnit(event){
	$.confirm({
		title:'删除单位',
		type:'red',
		icon: 'fa fa-warning',
		content:'确定要删除吗？',
		autoClose: 'cancelAction|8000',
	    buttons: {
	        deleteUser: {
	            text: '删除',
	            btnClass:'btn-blue',
	            action: function () {
	               $.ajax({
	            	   url:'/jwcpxt/Unit/delete_unit?unit.delete_unit='+event.id,
	            	   type:'GET',
	            	   success:function(data){
	            		   if(data==1){
	            			   toastr.error("单位不能删除");
	            			   loadData();
	            		   }else{
	            			   toastr.error("删除失败");
	            		   }
	            	   }
	               })
	            }
	        },
	        cancelAction: {
	        	text: '关闭',
	        	btnClass:'btn-red',
	            action: function () {
	            
	            }
	        }
	    }
	})
}

function updateUnit(event){
	$.confirm({
		title : '修改单位',
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
				text : '修改',
				btnClass : 'btn-blue',
				action : function() {
					var unit_reorganizer = $('#add_user_name').selectpicker('val');
					var unit_name=$('#add_unit_name').val();
					console.log(unit_name)
					if(add_unit_name!=''){
						$.ajax({
							url:'/jwcpxt/Unit/update_unit',
							type:'POST',
							data:{
								'unit.jwcpxt_unit_id':event.id,
								'unit.unit_reorganizer':unit_reorganizer,
								'unit.unit_name':unit_name
							},
							success:function(data){
								if(data==1){
									toastr.success("修改成功！");
									loadData();
								}else{
									toastr.error("修改失败！");
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
					$.ajax({
						url:'/wlmtxt/Unit/Unit_get_unit_byID?unit.jwcpxt_unit_id='+event.id,
						type:'GET',
						success:function(data){
							var unit = JSON.parse(data);
							$('#add_user_name').selectpicker('val',unit.unit_reorganizer);
							$('#add_unit_name').val(unit.unit_name);
							$('.selectpicker').selectpicker('render');
							$('.selectpicker').selectpicker('refresh');		
						}
					})

				}
			})
			
		}
	})
}

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