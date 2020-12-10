<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>镜像管理系统注册页面</title>
  <link href="../css/header.css" rel="stylesheet"/>
  <link href="../css/animate.css" rel="stylesheet"/>
  <link href="../css/login.css" rel="stylesheet" />
</head>
<body style="width: 100%;
    box-sizing: border-box;
    margin: 0px auto;
    overflow-x: hidden;
    height: 560px;
    background-image: url(../images/regist.jpeg);
    background-repeat: no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    background-position: center 0;"> 
<div class="parent">
  <div class="container1">
    <div class="panel rt">
      <form id="form-register" method="post" action="">
        <div class="txt">
          <p style="font-size: 23px;">找回密码</p>
        </div>
        <div class="form-group">
          <label for="uname">用户名：</label>
          <input type="text" placeholder="请输入用户名" autofocus name="uname" id="uname"/>
        </div>
        <div class="form-group">
          <label for="upwd">邮箱：</label>
          <input type="email" name="email" id="email"  placeholder="请输入您的邮箱地址">
        </div>
        <div id="showResult"  style="margin-top: -54px;"></div>
        <div class="form-group">
           <input type="button" value="找回密码" onclick="querypass()" style="margin-left: 86px;width: 250px;"/>
        </div>
        <div class="form-group">
          <input type="button" value="返回登陆页面" onclick="backLogin()" style="margin-left: 86px; width: 250px;"/>
        </div>
      </form>
    </div>
  </div>
</div>

<!--弹出的小广告-->
<script src="../js/jquery-3.1.1.min.js"></script>
<script>

	function backLogin() {
		window.location.href = "../user/showLogin.do"
	}
	$("#uname").blur(function() {
		$("#showResult").text("");
		var data = $("#uname").val();
		if (data == null || data == "") {
			$("#showResult").text("用户名不能为空！");
			$("#showResult").css("color", "red");
			return false;
		}

	});
	$("#email").blur(function() {
		$("#showResult").text("");
		  var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
          var obj = document.getElementById("email"); //要验证的对象
          if(obj.value.trim() === ""){ //输入不能为空
        	  $("#showResult").text("邮箱不能为空！");
  			  $("#showResult").css("color", "red");
         	 return false;
          }else if(!reg.test(obj.value.trim())){ //正则验证不通过，格式不对
        	  $("#showResult").text("请输入正确邮箱！");
  			  $("#showResult").css("color", "red");
         	 return false;
          }
	});
	 function querypass() {
		 var uname = $("#uname").val();
		 var email = $("#email").val();
		 var a=$("#showResult").text();
		 if(uname.length>0&&email.length>0&&a.length==0){
			 var params={
						uname:uname,
						email:email
			 };
			 $.ajax({
				  url: "../user/RetrievePassword.do",
				  data : params,
				  async : false,
				  success: function(result){
					  if(result=="1"){
						  alert('恭喜，找回密码成功,请前往邮箱查看密码');
						  document.getElementById("form-register").reset();
					  }else if(result=="2"){
						  alert('该邮箱尚未注册！请重新输入');
						  var email = $("#email").val("");
					  }else if(result=="3"){
						  alert('请输入正确账号邮箱');
						  var email = $("#email").val("");
					  }
				  },
			});
		 }else{
			 $("#showResult").text("");
			 $("#showResult").text("请输入必填项");
 			 $("#showResult").css("color", "red");
		 }
        	  
        	
		
	}
	
</script>
</body>
</html>