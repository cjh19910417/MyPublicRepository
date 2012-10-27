<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户注册-南雄数码网</title>
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
	<link href="/css/global/reg.css" rel="stylesheet" type="text/css">
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
			function checkForm() {
				var form = document.forms[0];
				if (form.username.value == "" || form.username.value.length < 6) {
					alert("请输入长度6位以上的用户名");
					return false;
				}
				if (form.password.value == "" || form.password.value.length < 6) {
					alert("请输入长度6位以上的密码");
					return false;
				}
				if (form.password.value != form.repassword.value) {
					alert("两次输入的密码不正确");
					return false;
				}
				if (/[\W]/g.test(form.username.value)) {
					alert("用户名中不能含有中文");
					return false;
				}
				;
				return true;
			}
			function checkUsername() {
				
				var form = document.forms[1];
				var username = form.username.value;
				if (username == "") {
					window.alert("用户名不能为空!");
					return false;
				}
				window.alert("正在检测");
				var viewobj = document.getElementById("checkResult");
				send_request(function(value) {
					viewobj.innerHTML = value
				}, '<html:rewrite action="/user/checkuser"/>?username='
						+ username, true);
			}
		//-->
		</SCRIPT>
</head>

<body>
	<jsp:include page="/WEB-INF/page/share/Head.jsp" />
	<div id="main">
	<!-- 检证用户是否存在表单 end -->
	<TABLE cellSpacing=0 cellPadding=0 width=770 align=center border=0>
		<TBODY>
			
			<TR>
				<TD height=15>&nbsp;</TD>
			</TR>
			<TR>
				<TD height=30><img src="/images/login/zc.gif" width="128"
					height="31" /></TD>
			</TR>
		</TBODY>
	</TABLE>

	<!-- <c:if test="${!empty error}">
		<DIV id="errorinfo">
			<TABLE cellSpacing=1 cellPadding=8 width="78%" align="center"
				bgColor="#dd9988" border=0>
				<TBODY>
					<TR>
						<TD bgColor="#ffffd5" align="left"><IMG height="17"
							src="/images/buy/exclamation-error-red.gif" width="17" /> <FONT
							color="#990000"><STRONG><SPAN class="font14">错误提示<BR></SPAN></STRONG></FONT>
							<DIV id="errorMessage">${error }</DIV></TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
	</c:if> -->

	<div id="Content">
		<html:form action="/user/reg" method="post"  >
			<INPUT TYPE="hidden" NAME="method" value="reg"> <input
				type="hidden" name="directUrl" value="${param.directUrl}">
					<div id="Main">
						<table id="FormRegStep1_Account" class="RegForm" align="center"
							border="0" width="100%">
							<tr class="Title" valign="middle">
								<strong style="color: red;font-weight: bold;">以下均为必填项</strong>
							</tr>

							<tr valign="middle" style="height: 50px;">
								<td class="Hint" align="right"><em>用户名</em>：</td>
								<td class="Input"><html:text property="username" size="28"
										tabindex="1"  onkeyup="value=value.replace(/[\W]/g,'')"></html:text>
									<br /> 
									<input type="button" name="check_username" value="检测用户名是否可用" onclick="javascript:checkUsername()"
									onMouseOver="this.style.cursor='pointer';" /></td>
								<td class="Info">
									<div id="username_info">5-20个字符(包括小写字母、数字、下划线)，一旦注册成功会员名不能修改。</div>
									<div id="checkResult"></div> <strong style="color: red"><html:errors
											property="username" /></strong> <c:if test="<"></c:if> <strong
									style="color: red">${isExist }</strong>
								</td>
							</tr>

							<tr valign="middle" style="height: 50px;">
								<td class="Hint" align="right"><em>密码：</em></td>
								<td class="Input"><input name="password" value=""
									id="password"  type="password" size="28" maxlength="16"
									tabindex="2" /></td>
								<td class="Info">
									<div id="password_info">密码由6-16个字符组成，请使用英文字母加数字或符号的组合密码</div> <strong
									style="color: red"> <html:errors property="password" />
								</strong>
								</td>
							</tr>

							<tr valign="middle" style="height: 50px;">
								<td class="Hint" align="right" width="15%"><em>确认密码：</em></td>
								<td class="Input"><input name="password2" value=""
									id="repassword" type="password"  size="28" maxlength="16"
									tabindex="3" /></td>
								<td class="Info">
									<div id="confirm_password_info">请再输入一遍您上面输入的密码。</div> <strong
									style="color: red"> <html:errors property="password2" />
								</strong>
								</td>
							</tr>
							<tr valign="middle" style="height: 50px;">
								<td class="Hint" align="right"><em>电子邮件：</em></td>
								<td class="Input"><html:text property="email" size="28" 
										maxlength="45" tabindex="3" /></td>
								<td class="Info"><div id="email_info">
										没有电子邮件？推荐使用<a
											href="https://reg.cn.yahoo.com/cnreg/cnreg_first?id=78001"
											target="_blank">雅虎3.5G免费超大邮箱</a>、<a
											href="http://mail.sogou.com/register.jsp" target="_blank">搜狐邮箱</a>和<a
											href="http://reg.126.com/reg1.jsp" target="_blank">网易邮箱</a>。
									</div> <strong style="color: red"> <html:errors
											property="email" />
								</strong></td>
							</tr>
						</table>
						<div  align="center" style="width: 740px;">
							<input type="submit" class="r5px button_blue" value="注 册"/>
						</div>
						
					</div>
		</html:form>
	</div></div>
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</html>