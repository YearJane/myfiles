$(function(){
	//左侧标题与右侧对应内容切换
	toggleContent();
	//首页登录框的表单验证
	userLogin();
	//对我的文件进行操作
	fileOperate();
	//对回收站文件进行操作
	recycleOperate();
	
	//获取当前用户名
	login_username=$('.login_username').html();
	//如果用户未登录，显示登录框	
	if(login_username!=""){
		$('.login').hide();
	}
	
	//获取用户文件类型
	$('.file_up').click(function(){
		//判断用户是否登录
		if(login_username==""){
			$('#messageTip').html("您尚未登录，请登录后再试！").show();
			hideMessage();
			return false;
		}
		filetypeSelect();	
	})

//---------------------	上传文件中编辑文件类型----------------------
	//上传文件中编辑文件类型
	$('.edit_type').click(function(){
		editFileType();	
	})
	//点击新增文件类型，出现input框让用户新增
	$('.to_addtype').click(function(){
		$('.addtype').show();
	})
	//确认新增文件类型
	$('.confirm_addtype').click(function(){
		var addtypeval=$('.addtype input').val();
		$.ajax({
			type:"post",
			url:"http://localhost:8080/myfiles/file/addFiletype",
			data:{typename:addtypeval},
			success:function(res){
				if(res.addMessage=="添加成功"){
					$('.addtype input').val("");
					$('.addtype').hide();
					editFileType();
				}
				else{
					alert("添加失败，该类型已存在！")
				}
			}
		});	
	})
	//编辑文件类型
	$('#alltypes').on('click',$('.editicon'),function(e){
		var ele=e.target;
		var $ele=$(ele);
		//判断用户点击的是否为编辑图标
		if(ele.className.indexOf('editicon')<0){
			return false;
		}
		//获取当前从后台返回的文件类型
		var $input=$ele.parent().prev().prev();
		$input.attr('readonly',false);
		var typeval=$input.val();
		var typeid=$input.parent().attr('typeid');
		$input.blur(function(){
			$(this).attr('readonly',true);
			var newtypename=$(this).val();
			$.ajax({
				type:"post",
				url:"http://localhost:8080/myfiles/file/updateFiletype",
				data:{typeid:typeid,typename:newtypename},
				success:function(res){
					if(res.updateMessage!="修改成功"){
						alert(res.updateMessage);
					}
				}
			});	
		})
	})
	//删除文件类型
	$('#alltypes').on('click',$('.delicon'),function(e){
		var ele=e.target;
		var $ele=$(ele);
		//判断用户点击的是否为删除图标
		if(ele.className.indexOf('delicon')<0){
			return false;
		}
		//获取当前一条文件类型的数据
		var $types=$ele.parent().parent();
		var typeid=$types.attr('typeid');
		var msg=confirm("您确定要删除吗？该类型下的文件将变为默认类型文件！");
		if(msg){
			$.ajax({
				type:"post",
				url:"http://localhost:8080/myfiles/file/deleteFiletype",
				data:{typeid:typeid},
				success:function(res){
					editFileType();
				}
			});	
		}		
	})
	//编辑文件类型弹窗关闭之后重新渲染类型select框
	$('#editType').on('hidden.bs.modal', function (e) {
		filetypeSelect();	
	})
//--------------------------------------------------------------
	
	//退出
	$('.exit').click(function(){
		//如果用户未登录，不做任何操作
		if(login_username==""){
			return false;
		}
		$.ajax({
			type:'post',
			url:'http://localhost:8080/myfiles/user/logout',
			dataType:'jsonp',
			success:function(res){
				if(res.logoutMessage=="注销成功"){
					location.reload();
					$('#messageTip').html("您已退出登录！").show();
					$('.usericon').attr('src','images/userPic.jpg');
					$('.login input[name=username]').val("");
					$('.login input[name=password]').val("");
					hideMessage();
				}
			}			
		});
	})

//--------------------------我的文件------------------------------
	//点击我的文件
	$('.myfile').click(function(){
		if(login_username==""){
			$('#messageTip').html("您尚未登录，请登录后再试！").show();
			hideMessage();	
			return false;
		}
		$('.chosetype option:not(:first)').remove();
		$.ajax({
			type:"post",
			data:{currentpage:1},
			url:"http://localhost:8080/myfiles/file/selectFiles",
			//强制转换后台返回的数据为json对象
			dataType:'json',
			success:function(res){
				var filelist=res.page.list;
				var typelist=res.filetypes;
				for(var i=0;i<typelist.length;i++){
					var types=$('<option>'+typelist[i].typename+'</option>');				
					$('.chosetype').append(types);
				}
				fileList($('.file_tbody'),filelist);
			}
		});
	})
	//输入搜索我的文件
	$('.search_btn').click(function(){
		var search_val=$('.search_file input').val();
		$.ajax({
			type:'post',
			url:"http://localhost:8080/myfiles/file/selectFileByVague",
			data:{vague:search_val,currentpage:1},
			success:function(res){
				var list=res.page.list;
				//动态渲染list里面的数据
				fileList($('.file_tbody'),list);
			}			
		})
	})
	//通过选择类型搜索我的文件
	$('.chosetype').change(function(){
		var search_type=$('.chosetype option:checked').val();
		$('.search_file input').val("");
		if(search_type==""){
			return false;
		}
		$.ajax({
			type:'post',
			url:"http://localhost:8080/myfiles/file/selectFilesBytype",
			data:{typename:search_type,currentpage:1},
			success:function(res){
				var list=res.page.list;
				//动态渲染list里面的数据
				fileList($('.file_tbody'),list);
			}			
		})
	})
//-----------------------------------------------------------
	
	//点击回收站
	$('.my_recycle').click(function(){
		if(login_username==""){
			$('#messageTip').html("您尚未登录，请登录后再试！").show();
			hideMessage();	
			return false;
		}
		$('.recycle_tbody').find('tr').remove();
		$.ajax({
			type:"post",
			data:{currentpage:1},
			url:"http://localhost:8080/myfiles/file/selectRecycleFiles",
			//强制转换后台返回的数据为json对象
			dataType:'json',
			success:function(data){
				var recyclelist=data.page.list;
				for(var i=0;i<recyclelist.length;i++){
					var tr=$('<tr fid='+recyclelist[i].fid+'></tr>');
					var checkbox_td=$('<td style="vertical-align: middle;"><input type="checkbox"></td>');
					var filenumb_td=$('<td style="vertical-align: middle;"></td>').html(i+1);
					var filename_td=$('<td style="word-wrap:break-word;vertical-align: middle;"></td>').html(recyclelist[i].reallyname);
					var filetype_td=$('<td style="vertical-align: middle;"></td>').html(recyclelist[i].filetype.typename);
					var fileinfo_td=$('<td style="word-wrap:break-word;vertical-align: middle;"></td>').html(recyclelist[i].introduce);
					var filetime_td=$('<td style="vertical-align: middle;"></td>').html(recyclelist[i].intime);
					var fileweight_td=$('<td style="vertical-align: middle;"></td>').html(recyclelist[i].weight);
					var res_str='<button class="btn btn-default btn-xs resume" data-toggle="modal" data-target="#resume">恢复</button>&nbsp;';
					var clear_str='<button class="btn btn-default btn-xs" data-toggle="modal" data-target="#clear">删除</button></td>';
					var btn_td=$('<td style="white-space:nowrap;vertical-align: middle;padding:0;"></td>').html(res_str+clear_str);
					tr.appendTo('.recycle_tbody').append(checkbox_td).append(filenumb_td).append(filename_td).append(filetype_td).append(fileinfo_td).append(filetime_td).append(fileweight_td).append(fileweight_td).append(btn_td);
				}
			}
		});
	})

		
})

//顶部消息提示2s后隐藏
function hideMessage(){
	setTimeout(function () {
		console.log("message div")
		$("#messageTip").stop().fadeOut(1000);	
	}, 2000);
}

//动态渲染编辑文件类型中的现有文件类型
function editFileType(){
	$('#alltypes').find($('.types')).remove();
	$.ajax({
		type:"post",
		url:"http://localhost:8080/myfiles/file/selectFileTypes",
		success:function(res){
			var typeList=res.filetypeList;
			for(var i=0;i<typeList.length;i++){
				if(typeList[i].typename=="默认"){
					continue;
				}
				var types_div=$("<div class='types' typeid="+typeList[i].typeid+"></div>");
				var input=$('<input type="text" class="form-control type_input" readonly="true">').val(typeList[i].typename);
				var delspan=$('<span class="deltype"><img class="delicon" title="删除" src="images/deltype.png"/></span>');
				var editspan=$('<span class="edittype""><img class="editicon" title="编辑" src="images/edittype.png"/></span>');
				var line=$('<div class="line"></div>');
				types_div.appendTo($('#alltypes')).append(input).append(delspan).append(editspan).append(line);
			}
		}
	});	
}
//动态渲染我的文件
function fileList(selector,filelist){
	selector.find('tr').remove();
	for(var i=0;i<filelist.length;i++){
		var tr=$('<tr fid='+filelist[i].fid+'></tr>');
		var checkbox_td=$('<td style="vertical-align: middle;"><input type="checkbox"></td>');
		var filenumb_td=$('<td style="vertical-align: middle;"></td>').html(i+1);
		var filename_td=$('<td style="word-wrap:break-word;vertical-align: middle;"></td>').html(filelist[i].reallyname);
		var filetype_td=$('<td style="vertical-align: middle;"></td>').html(filelist[i].filetype.typename);
		var fileinfo_td=$('<td style="word-wrap:break-word;vertical-align: middle;"></td>').html(filelist[i].introduce);
		var filetime_td=$('<td style="vertical-align: middle;"></td>').html(filelist[i].intime);
		var fileweight_td=$('<td style="vertical-align: middle;"></td>').html(filelist[i].weight);
		var edit_str='<button class="btn btn-default btn-xs" data-toggle="modal" data-target="#edit">编辑</button>';
		var down_sr="<a class='btn btn-default btn-xs download_btn' href=http://localhost:8080/myfiles/file/download?fakename="+filelist[i].fakename+">下载</a>";
		var dele_str='<button class="btn btn-default btn-xs delete_btn" data-toggle="modal" data-target="#delete">删除</button>';
		var btn_td=$('<td style="white-space:nowrap;vertical-align: middle;padding:0;"></td>').html(edit_str+down_sr+dele_str);
		tr.appendTo('.file_tbody').append(checkbox_td).append(filenumb_td).append(filename_td).append(filetype_td).append(fileinfo_td).append(filetime_td).append(fileweight_td).append(fileweight_td).append(btn_td);
	}
}

//文件上传中动态渲染文件类型下拉框
function filetypeSelect(){
	$('#select_type').empty();
	$.ajax({
		type:"post",
		url:"http://localhost:8080/myfiles/file/selectFileTypes",
		success:function(res){
			var filetypeList=res.filetypeList;
			for(var i=0;i<filetypeList.length;i++){
				var file_types=$('<option>'+filetypeList[i].typename+'</option>');
				$('#select_type').append(file_types);
			}
		}
	});
}
//左侧标题与右侧对应内容切换
function toggleContent(){
	var title=document.querySelector('.con_title');
	title.onclick=function(e){
		//判断用户是否登录
		if(login_username==""){
			$('#messageTip').html("您尚未登录，请登录后再试！").show();
			hideMessage();	
			return false;
		}
		var title_li=e.target;
		//如果用户点击到li之间的间隙，则获取到的e.target为父元素，不做任何操作
		if(title_li.nodeName=="UL"){
			return false;
		}
		if(title_li.classList.contains('active')){
			return false;
		}
		document.querySelector('.con_title li.active').classList.remove('active');
		title_li.classList.add('active');
		//获取标题对应内容的id属性
		var contentId=title_li.dataset.contentId;
		document.querySelector('.con_body li.show').classList.remove('show');
		document.querySelector('#'+contentId).classList.add('show');
	}
}

//首页登录框的表单验证
function userLogin(){
	$('.login_s').data({'s':0});
	$('.login input[name=username]').blur(function(){
		val=this.value;
		if(val.length<1){
			$(this).data({'s':0});
			$('.login .error').html("用户名不能为空！");
		}
		else{
			$(this).data({'s':1});
			$('.login .error').html("");
		}
	});
	$('.login input[name=password]').blur(function(){
		val=this.value;
		if(val.length<5){
			$(this).data({'s':0});
			$('.login .error').html("请输入不少于6位密码！");
		}
		else{
			$(this).data({'s':1});
			$('.login .error').html("");
		}
	});
	$('.login form').submit(function(e){
		var name=$('.login input[name=username]').val();
		var password=$('.login input[name=password]').val();
		if(name.length<1){
			$('.login input[name=username]').blur();
		}
		if(password.length<5){
			$('.login input[name=password]').blur();
		}
		var count=0;
		$('.login_s').each(function(){//遍历data中s的值
			count+=$(this).data('s');
		})
		if (count!=2) {
			return false;
		}
		e.preventDefault();//组织表单的默认的submit行为
		//调接口验证登录是否成功
		$.ajax({
			type:"post",
			url:"http://localhost:8080/myfiles/user/login",
			dataType:'jsonp',
			data:{username:name,password:password},
			success:function(res){
				var imagePath=res.imagpath;
				if(res.loginMessage=="登录成功"){
					location.reload();
					$('.usericon').attr('src',imagePath);
					$('#messageTip').html("恭喜您，登录成功！").show();
					hideMessage();
				}
				else{
					$('#messageTip').html("用户名或密码错误，请重新登录！").show();
					hideMessage();
				}
			}
		});
	});
}

//对我的文件进行操作
function fileOperate(){
	var check_all=document.querySelector('#fileTable .check-all');
	var tr=$('#fileTable')[0].children[1].rows;//获取tbody中的每一行
	//文件全选与取消,为动态创建的check绑定事件
	$('#fileTable').on('click',$('#fileTable input[type=checkbox]'),function(e){
		var checks=$('#fileTable input[type=checkbox]');
		if(e.target.className.indexOf('check-all')>=0){
			for(var i=0;i<checks.length;i++){
				checks[i].checked=e.target.checked;
			}
		}
		if(!e.target.checked){
			check_all.checked=false;
		}
	})
	//删除多个文件
	$('.deleteAll').click(function(){
//		判断用户是否选中了要删除的文件
		var isselect=false;
		for(var i=0;i<tr.length;i++){
			if(tr[i].getElementsByTagName('input')[0].checked){
				isselect=true;
			}
		}
		if(!isselect){
			return false;
		}
		var msg=confirm("您确定要删除吗？");
		if(msg){
			var fids=[];
			for(var i=0;i<tr.length;i++){
				if(tr[i].getElementsByTagName('input')[0].checked){
					var fid=tr[i].getAttribute('fid');
					fids.push(fid);
					tr[i].remove();//当删除一行信息后后一行信息的索引向前移动一位
					i--;//当前索引减一定位到第二个需要删除的元素上
				}
			}
			$.ajax({
				type:'post',
				url:'http://localhost:8080/myfiles/file/fileDelete',
				data:JSON.stringify(fids),
				contentType:"application/json",
				success:function(res){
					if(res.deleteMessage=="删除成功"){
						$('#messageTip').html("删除成功，可在回收站中恢复您的文件！").show();
						hideMessage();
					}
				}			
			})
			
		}	
	})
	//删除单个文件
	$('#delete').on('show.bs.modal', function (e) {
		var v=$(e.relatedTarget);
		delete_flie=v.parent().parent();
		var title=v.parent().prev().prev().prev().prev().prev().html();
		$('#filetitle').val(title);
	});
	//删除单个文件点击确定	
	$('.modal_delete').click(function(){
		var fid=delete_flie.attr('fid');
		var fid=parseInt(fid);
		var fids=[fid];
		console.log("file"+fids)
		$.ajax({
			type:'post',
			url:'http://localhost:8080/myfiles/file/fileDelete',
			data:JSON.stringify(fids),
			contentType:"application/json",
			success:function(res){
				if(res.deleteMessage=="删除成功"){
					$('#messageTip').html("删除成功，可在回收站中恢复您的文件！").show();
					hideMessage();
					delete_flie.remove();
				}
			}		
		})

	})
	//编辑文件信息
	$('#edit').on('show.bs.modal', function (e) {
		var v=$(e.relatedTarget);
		var editname=v.parent().prev().prev().prev().prev().prev().html();
		var editillustrate=v.parent().prev().prev().prev().html();
		var editweight=v.parent().prev().html();
		edit_tr=v.parent().parent();
		$('#editname').val(editname);
		$('#editillustrate').val(editillustrate);
		$('#editweight').val(editweight);
		$('.editchosetype option').remove();
		$.ajax({
			type:"post",
			data:{currentpage:1},
			url:"http://localhost:8080/myfiles/file/selectFiles",
			dataType:'json',
			success:function(data){
				var typelist=data.filetypes;
				for(var i=0;i<typelist.length;i++){
					var types=$('<option>'+typelist[i].typename+'</option>');				
					$('.editchosetype').append(types);
				}
			}
		});
	});
	//编辑文件中验证权重和描述	
	$('#editweight').blur(function(){
		var weight=this.value;
		if(!/^\d+$/.test(weight)){
			alert("权重必须为整数！");
			return false;
		}
	})
	$('#editillustrate').blur(function(){
		var introduce=this.value;
		if(introduce.length>20){
			alert("文件描述不能超过20个字，简要描述即可！");
			return false;
		}
	})
	$('.modal_edit').click(function(){			
		var typename=$('.editchosetype option:selected').val();
		var introduce=$('#editillustrate').val();
		var weight=$('#editweight').val();
		var fid=edit_tr.attr('fid');
		if(introduce.length>20){
			alert("文件描述不能超过20个字，简要描述即可！");
			return false;
		}
		if(!/^\d+$/.test(weight)){
			alert("权重必须为整数！");
			return false;
		}
		$.ajax({
			type:"post",
			data:{fid:fid,typename:typename,introduce:introduce,weight:weight},
			url:"http://localhost:8080/myfiles/file/updateFile",
			dataType:'json',
			success:function(res){
				$("#edit").hide();
				$('.chosetype option:not(:first)').remove();
				$.ajax({
					type:"post",
					data:{currentpage:1},
					url:"http://localhost:8080/myfiles/file/selectFiles",
					//强制转换后台返回的数据为json对象
					dataType:'json',
					success:function(res){
						var filelist=res.page.list;
						var typelist=res.filetypes;
						for(var i=0;i<typelist.length;i++){
							var types=$('<option>'+typelist[i].typename+'</option>');				
							$('.chosetype').append(types);
						}
						fileList($('.file_tbody'),filelist);
					}
				});
				$('#messageTip').html("恭喜您，编辑文件成功！").show();
				hideMessage();
			}
		});
	})
}
//对回收站文件进行操作
function recycleOperate(){
	var check_all=document.querySelector('#recycleTable .check-all');
	var tr=$('#recycleTable')[0].children[1].rows;//获取tbody中的每一行
	//文件全选与取消,为动态创建的check绑定事件
	$('#recycleTable').on('click',$('#recycleTable input[type=checkbox]'),function(e){
		var checks=$('#recycleTable input[type=checkbox]');
		if(e.target.className.indexOf('check-all')>=0){
			for(var i=0;i<checks.length;i++){
				checks[i].checked=e.target.checked;
			}
		}
		if(!e.target.checked){
			check_all.checked=false;
		}
	})
	//回收站中删除单个文件
	$('#clear').on('show.bs.modal', function (e) {
		var v=$(e.relatedTarget);
		clear_flie=v.parent().parent();
		var title=v.parent().prev().prev().prev().prev().prev().html();
		$('#cleartitle').val(title);
	});
	$('.modal_clear').click(function(){
		var fid=clear_flie.attr('fid');
		var fid=parseInt(fid);		
		var fids=[fid];
		$.ajax({
			type:'post',
			url:'http://localhost:8080/myfiles/file/fileDeleteReally',
			data:JSON.stringify(fids),
			contentType:"application/json",
			success:function(res){
				if(res.deleteMessage=="删除成功"){
					$('#messageTip').html("删除成功！").show();
					hideMessage();
					clear_flie.remove();
				}
			}		
		})
	})
	//回收站中恢复数据
	$('#recycleTable').on('click',$('.resume'),function(e){
		var resume_btn=e.target;
		if(resume_btn.innerHTML=="恢复"){
			var msg=confirm("您确定要恢复吗？");
			if(msg){
				var fids=[];
				fid=resume_btn.parentElement.parentElement.getAttribute('fid');
				fids.push(fid);
				$.ajax({
					type:'post',
					url:'http://localhost:8080/myfiles/file/resumeRecyle',
					data:JSON.stringify(fids),
					contentType:"application/json",
					success:function(res){
						if(res.message=="恢复成功,可在我的文件中查看"){
							$('#messageTip').html("此文件已恢复成功,可在我的文件中查看！").show();
							hideMessage();
							//恢复成功，在页面上删除此条记录
							resume_btn.parentElement.parentElement.remove();
						}
					}			
				})
			}	
		}		
	})
	//回收站中删除多个文件
	$('.del_select').click(function(){
//		判断用户是否选中了要删除的文件
		var isselect=false;
		for(var i=0;i<tr.length;i++){
			if(tr[i].getElementsByTagName('input')[0].checked){
				isselect=true;
			}
		}
		if(!isselect){
			return false;
		}
		var msg=confirm("您确定要删除吗？");
		if(msg){
			var fids=[];
			for(var i=0;i<tr.length;i++){
				if(tr[i].getElementsByTagName('input')[0].checked){
					var fid=tr[i].getAttribute('fid');
					fids.push(fid);
				}
			}
			$.ajax({
				type:'post',
				url:'http://localhost:8080/myfiles/file/fileDeleteReally',
				data:JSON.stringify(fids),
				contentType:"application/json",
				success:function(res){
					if(res.deleteMessage=="删除成功"){
						$('#messageTip').html("删除成功！").show();
						hideMessage();
						//页面上删除用户选中的文件
						for(var i=0;i<tr.length;i++){
							if(tr[i].getElementsByTagName('input')[0].checked){
								var fid=tr[i].parentElement.parentElement.getAttribute('fid');
								fids.push(fid);
								tr[i].remove();
								i--;
							}
						}
					}
				}		
			})
		}	
	})
}


//-------------文件上传页面中的事件函数-----------------
//验证文件的描述
function introduceBlur(){
	var introduce=$('#fileup input[name=introduce]').val();
	if(introduce.length>20){
		$('#messageTip').html("文件描述不能超过20个字，简要描述即可！").show();
		hideMessage();
		return false;
	}
}
//验证文件的权重
function weightBlur(){
	var weight=$('#fileup input[name=weight]').val();
	if(!/^\d+$/.test(weight)){
		$('#messageTip').html("权重必须为整数！").show();
		hideMessage();
		return false;
	}
}
//点击上传文件提交表单
function submitForm(){
	var file=$('#fileup input[name=file]').attr("name");
	var typename=$('#select_type option:selected').val();
	var introduce=$('#fileup input[name=introduce]').val();
	var weight=$('#fileup input[name=weight]').val();
	if($('#fileup input[name=file]').val()==""){
		$('#messageTip').html("请选择文件！").show();
		hideMessage();
		return false;
	}
	introduceBlur();
	weightBlur();
	$('#messageTip').html("文件正在上传中！").show();
	$('.fileup_btn').attr('disabled',true);
	var form=new FormData(document.getElementById("fileup_form"));	
	$.ajax({
		type:"post",
		url:"http://localhost:8080/myfiles/file/fileUpload",
		data:form,
        processData:false,
        contentType:false,
		success:function(res){
			$('#messageTip').html("恭喜您，文件上传成功，可在 我的文件 中查看！").show();
			$('#fileup input[name=file]').val("");
			$('#select_type  option:eq(0)').attr('selected','selected');
			$('#fileup input[name=introduce]').val("");
			var weight=$('#fileup input[name=weight]').val("");
			$('.fileup_btn').attr('disabled',false);
			hideMessage();
		}
	});
}
//------------------------------------------------------