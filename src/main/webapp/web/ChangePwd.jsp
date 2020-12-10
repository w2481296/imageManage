<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css"
	href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/style.css" />



<meta name="keywords"
	content="H-ui.admin v3.0">
<meta name="description"
	content="H-ui.admin v3.0">
</head>
<body>
	<section class="Hui-article-box">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			系统管理 <span class="c-gray en">&gt;</span> 修改密码 <a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
	</section>
	<article class="cl pd-20">
		<form action="/" method="post" class="form form-horizontal"
			id="form-change-password">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>账户：</label>
				<div class="formControls col-xs-8 col-sm-9" id="username">${user.username}</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>输入旧密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" autocomplete="off"
						placeholder="输入旧密码" name="oldpassword" id="oldpassword">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>新密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" autocomplete="off"
						placeholder="不修改请留空" name="newpassword" id="newpassword">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>确认密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" autocomplete="off"
						placeholder="不修改请留空" name="newpassword2" id="new-password2">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="../static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="../static/h-ui.admin/js/H-ui.admin.page.js"></script>
	<!--/_footer /作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#form-change-password").validate({
				rules : {
					newpassword : {
						required : true,
						minlength : 6,
						maxlength : 16
					},
					newpassword2 : {
						required : true,
						minlength : 6,
						maxlength : 16,
						equalTo : "#newpassword"
					},
				},
				onkeyup : false,
				focusCleanup : true,
				success : "valid",
				submitHandler : function(form) {
					var username=localStorage.getItem("username"); 
					if(username==null){
						alert("请先登录");
						return false;
					}
					var username =$('#username').html(); 
					var oldPwd = $('#oldpassword').val();
					var newPwd = $('#newpassword').val();
					$.ajax({
						url :'../user/updatePassword.do',
						data : {
							username:username,
							oldPwd : oldPwd,
							newPwd : newPwd
						},
						type : 'post',
						success : function(data) {
							console.log(data);
							if (data=="success") {
								member_del();
							}else if(data=="fail"){
								alert("旧密码输入错误");
							}
						}
					});
				}
			});
		});
		
		//修改密码重新登陆
		function member_del(){
			layer.confirm('修改密码成功，请重新登陆',{
				btn: ['确定']
			  },function(){
					$.ajax({
						url :"../user/showLogin.do",
						type:"post",
						async : false,
						success : function(result){
							layer.closeAll('dialog'); 
							top.location.href='../user/showLogin.do';
						}
					});
			});
		}
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>