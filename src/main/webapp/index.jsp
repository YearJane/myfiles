<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<!--默认在ie浏览器下以当前最高的ie版本打开-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, user-scalable=no, 
			initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<title>my file</title>
		<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/pagination.css" />
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="css/index.css" />
	</head>
	<body>
		<div class="container">
		<div style="display:none;">${userInfo.imagpath}</div>
			<!--顶部导航栏-->
			<div class="topBar">
				<img class="fl usericon" src="<c:if test="${empty userInfo.imagpath}">images/userPic.jpg</c:if>${userInfo.imagpath}" />
				<ul>
					<li><a href="#" class="exit">退出</a></li>
					<li><a href="#" class="login_username" style="color:#337ab7;">${userInfo.username}</a></li>
				</ul>
			</div>
			<!--内容区块-->
			<div class="content">
				<!--左侧导航栏-->
				<div class="lfcon col-sm-2">
					<ul class="con_title">
						<li data-content-id="home" class="active">首页</li>
						<li data-content-id="userinfo">个人资料</li>
						<li data-content-id="file" class="myfile">我的文件</li>
						<li data-content-id="fileup" class="file_up">文件上传</li>						
						<li data-content-id="recycle" class="my_recycle">回收站</li>
					</ul>
				</div>
				<!--右侧内容区-->
				<div class="frcon col-sm-10">
					<!--消息提示-->
					<div id="messageTip"></div>
					<ul class="con_body">
						<!--首页-->
						<li id="home" class="show home_con">
							<div class="login">
								<div class="login_hd">请登录</div>
								<div class="error"></div>
								<div class="login_bd">
									<form>
									  <div class="form-group">
									    <label>用户名：</label>
									    <input type="text" name="username" class="form-control login_s" placeholder="请输入用户名">
									  </div>
									  <div class="form-group">
									    <label>登录密码:</label>
									    <input type="password" name="password" class="form-control login_s" placeholder="请输入密码">
									  </div>									  
									  <button type="submit" class="btn login_btn btn-primary">确认登录</button>
									</form>
								</div>
							</div>
						</li>
						<!--个人资料-->
						<li id="userinfo">没错，你是最美的！</li>
						<!--我的文件-->
						<li id="file" class="file_con">
							<div class="search_file">
								<div class="search_img"></div>
								<input class="form-control" type="search" placeholder="请输入搜索内容" />
								<button class="search_btn btn btn-default">立即搜索</button>
							</div>
							<div class="select_file">
								选择文件类型：
								<select class="form-control chosetype">
									<option></option>							
								</select>
							</div>
							<div class="file_table">
							<div style="height:330px;overflow-y: auto;">
								<table style="table-layout:fixed;width:110%;" class="table table-striped table-bordered table-hover table-responsive" id="fileTable">
									<thead>
									<tr class="info">
										<th><label><input type="checkbox" class="check-all" />&nbsp;全选</label></th>
										<th>序号</th>
										<th>文件名</th>
										<th>类型</th>
										<th>描述</th>
										<th>上传时间</th>
										<th>权重</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody class="file_tbody">
									
									</tbody>
								</table>
							</div>	
								<button class="deleteAll btn btn-primary" style="margin-top: 15px;">删除选中文件</button>
							</div>
						</li>
						<!--文件上传-->
						<li id="fileup">
							<form id="fileup_form" class="form-horizontal" enctype="multipart/form-data">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">请选择文件：</label>
							    <div class="col-sm-10">
							      <input type="file" name="file">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">文件类型：</label>
							    <div class="col-sm-10">
							      <select class="form-control" name="typename" id="select_type" style="width: 60%;display: inline-block;margin-right: 10px;">
							      	<!--<option>music</option>
							      	<option>document</option>-->
							      </select>
							      <button type="button" class="btn btn-default edit_type" data-toggle="modal" data-target="#editType">编辑类型</button>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">文件描述：</label>
							    <div class="col-sm-10">
							      <input type="text" name="introduce" onBlur="introduceBlur()" class="form-control" placeholder="请用不超过20个字符简要描述此文件,方便日后查找">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">文件权重：</label>
							    <div class="col-sm-10">
							      <input type="text" name="weight" onBlur="weightBlur()" class="form-control" placeholder="请为此文件设置权重值">
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="col-sm-offset-2 col-sm-10">
							      <button type="button" class="btn btn-primary fileup_btn" onclick="submitForm()">立即上传</button>
							    </div>
							  </div>
							</form>
						</li>
						<!--回收站-->
						<li id="recycle">
							<div style="height:380px;overflow-y: auto;">
							<table style="table-layout:fixed;width:110%;" class="table table-striped table-bordered table-hover table-responsive" id="recycleTable" >
									<thead>
									<tr class="info">
										<th><label><input type="checkbox" class="check-all" />&nbsp;全选</label></th>
										<th>序号</th>
										<th>文件名</th>
										<th>类型</th>
										<th>描述</th>
										<th>上传时间</th>
										<th>权重</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody class="recycle_tbody">
									
									</tbody>
								</table>
								</div>
								<button class="btn btn-primary del_select" style="margin-top: 15px;">删除选中文件</button>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 删除文件弹窗 -->
		<div class="modal fade" id="delete" tabindex="-1" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content" id="deleteCss">
						<div class="modal-header">
							<img src="images/waring.png" width="40" height="40">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button><!-- 右上角关闭按钮 -->
						</div>
						<div class="modal-body">
								<form action="#" method="get" >
									<span style="display: block;">是否删除此条文件记录:</span><br>
									<div class="input-group">
					      			<div class="input-group-addon">文件名称</div>
					      			<input name="filetitle" id="filetitle" type="text" class="form-control" readonly="true">
		    						</div>
									<div class="modal-footer">
									<input class="btn btn-default" type="button" data-dismiss="modal" name="no" value="取消" >
									<input class="btn btn-primary modal_delete" type="button" data-dismiss="modal" name="yes" value="确定" >
									</div>
								</form>
						</div>
					</div>
				</div>
			</div>	
		</div>
		<!-- 编辑文件弹窗 -->
		<div class="modal fade" id="edit" tabindex="-1" aria-hidden="true" data-backdrop="static">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" style="color: #337ab7;"><img src="images/edit.png" width="40" height="40" style="margin-right: 5px;">编辑文件信息</h4>
		      </div>
		      <div class="modal-body" style="margin: 0 15px;">
		        <form class="form-horizontal" action="">
		        	<div class="form-group">
		    			<div class="input-group">
		      			<div class="input-group-addon">文件名</div>
		      			<input name="username" type="text" class="form-control auth" id="editname" readonly="true">
		    			</div>
					</div>
					<div class="form-group">
		    			<div class="input-group">
		      			<div class="input-group-addon">类&nbsp;&nbsp;&nbsp;&nbsp;型</div>
		      			<select class="form-control editchosetype">
					      	<!-- <option>music</option> -->
					    </select>
		    			</div>
					</div>
					<div class="form-group">
		    			<div class="input-group">
		      			<div class="input-group-addon">描&nbsp;&nbsp;&nbsp;&nbsp;述</div>
		      			<input name="titlename" type="text" class="form-control" id="editillustrate" placeholder="请输入您对此文件的描述">
		    			</div>
					</div>
					<div class="form-group">
		    			<div class="input-group">
		      			<div class="input-group-addon">权&nbsp;&nbsp;&nbsp;&nbsp;重</div>
		      			<input name="linkname" type="text" class="form-control" id="editweight" placeholder="请重新定义此文件权重">
		    			</div>
					</div>				
					<div class="form-group">
						<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        			<button type="submit" class="btn btn-primary modal_edit" data-dismiss="modal">确定</button>
		      			</div>
					</div>
				</form>
		      </div>
		
		    </div>
		  </div>
		</div>
		<!-- 清除文件弹窗 -->
		<div class="modal fade" id="clear" tabindex="-1" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content" id="deleteCss">
						<div class="modal-header">
							<img src="images/waring.png" width="40" height="40">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button><!-- 右上角关闭按钮 -->
						</div>
						<div class="modal-body">
								<form action="#" method="get" >
									<span class="delete_span">是否彻底清除此项文件:</span><br>
									<div class="input-group">
					      			<div class="input-group-addon">文件名称</div>
					      			<input name="filetitle" id="cleartitle" type="text" class="form-control" readonly="true">
		    						</div>
									<div class="modal-footer">
									<input class="btn btn-default" type="button" data-dismiss="modal" name="no" value="取消" >
									<input class="btn btn-primary modal_clear" type="button" data-dismiss="modal" name="yes" value="确定" >
									</div>
								</form>
						</div>
					</div>
				</div>
			</div>	
		</div>
		<!-- 编辑文件类型弹窗 -->
		<div class="modal fade" id="editType" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content" style="width: 450px;margin: 60px auto;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" style="color: #337ab7;">请选择类型进行编辑或删除</h4>
		      </div>
		      <div class="modal-body" id="alltypes">
		      <button class="btn btn-primary to_addtype">新增类型</button>
			    <div class="addtype">
			      	<input type="text" class="form-control">
			      	<button class="btn btn-primary confirm_addtype">确认新增</button>	
			    </div>
		       <!--  <div class="types">
		        	<input type="text" class="form-control type_input" readonly="true">	
		        	<span class="deltype"><img src="images/deltype.png" data-toggle="tooltip" data-placement="top" title="删除" /></span>
		        	<span class="edittype"><img src="images/edittype.png" data-toggle="tooltip" data-placement="top" title="编辑"/></span>
		        	<div class="line"></div>
		        </div> -->
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
	<script src="lib/jquery/jquery.min.js"></script>
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="lib/artTemplate/template-native.js"></script>
	<script src="js/jquery.pagination.js"></script>
	<script src="js/index.js"></script>

	</body>
</html>
