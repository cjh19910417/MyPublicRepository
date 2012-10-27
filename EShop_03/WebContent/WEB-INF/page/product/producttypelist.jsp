<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
<head>
<title>产品类别管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">

<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript">
	//要跳到第几页,用于分页显示
	function topage(page){
		var form = document.forms[0];
		form.page.value = page;
		form.submit();
	}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
	<html:form action="/control/product/type/list" method="post">
	<html:hidden property="page"/>
	<html:hidden property="name"/>
	<html:hidden property="query"/>
	<html:hidden property="parentid" value="${parentid }"/>
		<table width="98%" border="0" cellspacing="1" cellpadding="2"
			align="center">
			<tr>
				<td colspan="6" bgcolor="9CD5C5" align="right">
					<%@ include file="/WEB-INF/page/share/fenye.jsp"%>
				</td>
			</tr>
			<tr>
				<td width="8%" bgcolor="9CD5C5">
					<div align="center">
						<font color="#FFFFFF">代号</font>
					</div>
				</td>
				<td width="5%" nowrap bgcolor="9CD5C5">
					<div align="center">
						<font color="#FFFFFF">修改</font>
					</div>
				</td>
				<td width="20%" bgcolor="9CD5C5">
					<div align="center">
						<font color="#FFFFFF">产品类别名称</font>
					</div>
				</td>
				<td width="10%" nowrap bgcolor="9CD5C5">
					<div align="center">
						<font color="#FFFFFF">创建下级类别</font>
					</div>
				</td>
				<td width="15%" bgcolor="9CD5C5"><div align="center">
						<font color="#FFFFFF">所属父类</font>
					</div></td>
				<td nowrap bgcolor="9CD5C5">
					<div align="center">
						<font color="#FFFFFF">备注</font>
					</div>
				</td>
			</tr>
			<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageViewData.records }" var="productType" >
			<tr>
				<td bgcolor="f5f5f5">
					<div align="center">${productType.typeId }</div>
				</td>
				
				<td bgcolor="f5f5f5">
					<div align="center">
						<a href="javascript:window.location.href='<html:rewrite action="/control/product/type/manage"/>?typeId=${productType.typeId }&method=editUI'">						
							<img src="/images/Modify.png" width="24" border="0">
						</a>
					</div>
				</td>
				<td bgcolor="f5f5f5">
					<div align="center">
						<!-- ${ fn:length(productType.childtype)} fn:length是jstl函数库里面的函数,这里要使用OpenEntityManageInView,不然会报延迟加载,因为session关闭的错误! -->
						<a	href='<html:rewrite action="/control/product/type/list"/>?parentid=${productType.typeId}'>${productType.name }&nbsp(<font color="red">${ fn:length(productType.childtype)}</font>)</a>
					</div>
				</td>
				<td bgcolor="f5f5f5">
					<div align="center">
						<a	href='<html:rewrite action="/control/product/type/manage"/>?method=addUI&parentid=${productType.typeId}'>添加子类别</a>
					</div>
				</td>
				<td bgcolor="f5f5f5" align="center">${productType.parent.name }</td>
				<td bgcolor="f5f5f5">${productType.note }</td>
			</tr>
</c:forEach>
			<!----------------------LOOP END------------------------------->
			<tr>
				<td bgcolor="f5f5f5" colspan="6" align="center"><table
						width="100%" border="0" cellspacing="1" cellpadding="3">
						<tr>
							<td width="5%"></td>
							<td width="85%"><input name="AddDic" type="button"
								class="frm_btn" id="AddDic"
								onClick="javascript:window.location.href='<html:rewrite action="/control/product/type/manage"/>?method=addUI&parentid=${parentid }'"
								value="添加类别"> &nbsp;&nbsp; <input name="query"
								type="button" class="frm_btn" id="query"
								onClick="javascript:window.location.href='<html:rewrite action="/control/product/type/manage"/>?method=queryUI'"
								value=" 查 询 "> &nbsp;&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</html:form>
</body>
</html>