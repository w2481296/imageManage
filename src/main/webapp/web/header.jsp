<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
     uri="http://java.sun.com/jsp/jstl/core" %>

<header id="top" class="fixed_nav">
    <div id="logo" class="lf">
        <img class="animated jello" src="images/header/imag.png" style="width: 593px;height: 71px;margin-top: -20px;margin-left: 31px;" alt="logo"/>
    </div>
    <div class="rt" style="margin-top: 15px;">
        <ul class="lf">
        	<li>您好    ${user.username}</li>
           <!-- 如果user==null，说明没有登录 -->
           <c:if test="${user==null}">
            <li><a href="web/login.jsp">登录</a></li>
           </c:if>
           <!-- 如果user!=null，说明已经登录 -->
           <c:if test="${user!=null}">
            <li><a href="web/login.jsp"><img src="images/header/exit.jpg" style= "   width: 36px;
    height: 83%;
    margin-top: -21px;
    alt=: ;" /></a></li>
           </c:if>
       
        </ul>
    </div>
</header>