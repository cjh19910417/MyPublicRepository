<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>员工登录-南雄数码网后台</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="/css/login.css" type="text/css"></link></head>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
<!--
function verifyForm(){
	var objForm = document.forms[0];
	if (trim(objForm.username.value)==""){
		alert("用户名不能为空!");
		objForm.username.focus();
		return false; 
	}
	if (trim(objForm.password.value)==""){
		alert("密码不能为空!");
		objForm.password.focus();
		return false; 
	}
	return true;
}
function locateparentwindow(){
	if (window.parent!=null && window.parent.document.URL!=document.URL){
		window.parent.location= document.URL; 
	}
}
//-->
</script>
<body id="login" onload="locateparentwindow()">

<div id="page">

<div id="login_form">
<html:form action="/employee/logon" method="post">
<c:if test="${!empty message }">
<div class="failure" id="summary">
<ul>
<li><i></i><span>${message }</span></li>
</ul>
</div>
</c:if>

<div id="logo"></div>
<ul class="clearfix">
<li>
<label for="email">帐号</label>
<input type="text" id="username" class="text r5px" name="username" spellcheck="false" placeholder="username" autofocus="">
</li>
<li>
<label for="password">密码</label>
<input type="password" id="password" class="text r5px" name="password" placeholder="password">
</li>
<li class="last">
<label for="">&nbsp;</label>
<input type="submit" id="button_login" class="r5px button_blue" value="登 录" onclick="verifyForm()">
</li>
</ul>
</html:form>
</div>
<div id="footer">
<p>© 2012 <a href='<html:rewrite action="/product/list/display"/>?typeId=3'>南雄数码网</a> · <a href="http://weibo.com/wulihua819" target="_blank" rel="nofollow">新浪微博</a>
<br>

</div>
</div>
<!--[if lt IE 7 ]>
<script src="http://static.zhihu.com/static/js/dd_fix.js"></script>
<script>DD_belatedPNG.fix('#login, #page, ##page h1');</script>
<![endif]-->

</body>
</html>