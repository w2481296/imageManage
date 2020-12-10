<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />

<meta name="keywords" content="H-ui.admin">
<meta name="description" content="H-ui.admin">
</head>
<body>
<article class="cl pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl" style="display:none">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="id" name="id">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3" style="margin-left: 80px;"><span class="c-red">*</span>镜像名字：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="repository" name="repository">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3" style="margin-left: 80px;"><span class="c-red">*</span>镜像版本：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="tag" name="tag">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3" style="margin-left: 80px;"><span class="c-red">*</span>镜像id：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="imageId" name="imageId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3" style="margin-left: 80px;"><span class="c-red">*</span>镜像大小：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="size" name="size">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;添加镜像&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.page.js"></script>
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			repository:{
				required:true,
			},
			tag:{
				required:true,
			},
			imageId:{
				required:true,
			},			
			size:{
				required:true,
			},			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
				var username=localStorage.getItem("username"); 
				if(username==null){
					alert("请先登录才可以添加");
				}else{
					console.log("我是登录用戶"+username);
					 $.ajax({
						url :"../image/addinstock.do",
						type:"post",
						async:false,
						data:$("#form-member-add").serialize()+ "&username=" +username,
						success : function(result) {
							if(result=="error"){
								alert("镜像已存在");
							}else if(result=="ok"){
								alert("添加成功！");
								parent.location.reload();
								layer_close();
							}
							
						},
					}); 
				}
				
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>