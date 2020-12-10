<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="../static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="../static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="../lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

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
			<label class="form-label col-xs-4 col-sm-3">镜像名字：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" readonly="true" style="width: 80%;margin-left: -72px;" id="repository" name="repository">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">镜像版本：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" readonly="true" style="width: 80%;margin-left: -72px;" id="tag" name="tag">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"></span>镜像ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" readonly="true" style="width: 80%;margin-left: -72px;" name="imageId" id="imageId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"></span>下载次数：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" readonly="true" style="width: 80%;margin-left: -72px;" name="download" id="download">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>镜像大小：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="size" name="size">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>下载命令：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 80%;margin-left: -72px;" id="downorder" name="downorder">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 修改并提交</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.page.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/messages_zh.js"></script> 	
<script type="text/javascript">
$(function(){
	//清空表单
	if(localStorage.getItem("MainaddeditId")==null){
		layer_close();
	}
	var vipeditId=localStorage.getItem("MainaddeditId");
	var params={
			id:vipeditId
	};
	$.ajax({
		url :"../image/queryinstockById.do",
		type:"post",
		data:params,
		async : false,
		success : function(result) {
			//localStorage.clear();
			document.getElementById("id").value=result[0].id!=null?result[0].id:"";
			document.getElementById("repository").value=result[0].repository!=null?result[0].repository:"";
			document.getElementById("tag").value=result[0].tag!=null?result[0].tag:"";
			document.getElementById("imageId").value=result[0].imageId!=null?result[0].imageId:"";
			document.getElementById("download").value=result[0].download!=null?result[0].download:"";
			document.getElementById("size").value=result[0].size!=null?result[0].size:"";
			document.getElementById("downorder").value=result[0].downorder!=null?result[0].downorder:"";
		}
	});

});
$("#form-member-add").validate({
	rules:{
		size:{
			required:true,
		},			
		downorder:{
			required:true,
		},					
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var username=localStorage.getItem("username"); 
		if(username==null){
			alert("请先登录才可以修改");
			return false;
		}else{
			$.ajax({
				url :"../image/updateimage.do",
				type:"post",
				data:$("#form-member-add").serializeArray(),
				success : function(result) {
					alert("修改成功！");
					parent.location.reload();
					layer_close();
				},
			});
		}
		parent.location.reload();
	}
});

</script>
</body>
</html>