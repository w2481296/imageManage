<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="cn">
    <meta charset="UTF-8">
    <title>镜像管理系统</title>
    <link href="../css/header.css" rel="stylesheet"/>
    
    <link rel="stylesheet" type="text/css" href="../css/nav.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/nav.js"></script>
</head>
<body>
<!-- 页面顶部-->
<%@ include file="header.jsp" %>
<!-- 导航栏 -->
<div class="nav">
	<div class="nav-top">
		<div id="mi" style="border-bottom:1px solid rgba(255,255,255,.1)">
			<img src="../images/header/zong.png"    onclick="javascript:location.href='showIndex.do'" style="margin-left: 72px;width: 37px;height: 41px;margin-top: 2px;">
		</div>
	</div>
	<ul id="aat">
		
	</ul>
</div>
<iframe id="mainContent"  src="showIndexx.do" scrolling="no" width="1350px" height="650px" style="margin-left: 179px;margin-top: 64px;overflow-x:hidden;"></iframe>
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
var id=${user.id};
var navData = ''; 
navData+="<li class='nav-item'><a href='javascript:;'><i class='my-icon nav-icon icon_1'></i><span>镜像管理</span><i class='my-icon nav-more'></i></a><ul>";
navData+="<li><a  href='javascript:;' _link='showIndex3.do'><span>镜像详情</span></a></li>";
navData+="</ul></li>";
navData+="<li class='nav-item'><a href='javascript:;'><i class='my-icon nav-icon icon_1'></i><span>系统管理</span><i class='my-icon nav-more'></i></a><ul>";
navData+="<li ><a  href='javascript:;' _link='showIndex12.do'><span>修改密码</span></a></li>";
navData+="</ul></li>";
$('#aat').html(navData);


</script>
</body>
</html>