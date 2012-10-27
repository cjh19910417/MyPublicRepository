<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${producttype.name} 南雄数码购物网</title>
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
	<link href="/css/product/list.css" rel="stylesheet" type="text/css" />
	<link href="/css/global/topsell.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="${producttype.name}">
			<META name="description" content="${producttype.note}">
				<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
				<SCRIPT LANGUAGE="JavaScript">
				<!--
					function getTopSell(typeId) {
						var salespromotion = document
								.getElementById('salespromotion');
						if (salespromotion && typeId != "") {
							salespromotion.innerHTML = "数据正在加载...";
							send_request(function(value) {
								salespromotion.innerHTML = value
								},"<html:rewrite action='/product/switch'/>?method=topsell&typeId=" + typeId, true);
						}
					}
					function getViewHistory() {
						var viewHistoryUI = document
								.getElementById('viewHistory');
						if (viewHistoryUI) {
							viewHistoryUI.innerHTML = "数据正在加载...";
							send_request(
									function(value) {
										viewHistoryUI.innerHTML = value
									},
									"<html:rewrite action="/product/switch"/>?method=getViewHistory",
									true);
						}
					}
					function pageInit() {
						getTopSell("${producttype.typeId}");
						getViewHistory();
					}
				//-->
				</SCRIPT>
</head>

<body class="ProducTypeHome2" onload="javascript:pageInit()">
	<jsp:include page="/WEB-INF/page/share/Head.jsp" />
	<div id="main">
	<c:set var="out" value="" />
	<c:forEach items="${typeTrace}" var="type" varStatus="status">
		<c:if test="${status.count==1}">
			<c:set var="out" value=" &gt;&gt; <strong> <em>${type.name}</em></strong> ${out}" />
		</c:if>
		<c:if test="${status.count>1}">
			<c:set var="out"
				value=" &gt;&gt; <a href='/product/list/display.do?typeId=${type.typeId}'>${type.name}</a> ${out}" />
		</c:if>
	</c:forEach>
	<div id="position">
		您现在的位置: <a href="/" name="linkHome">南雄数码网</a>
		<c:out value="${out}" escapeXml="false"></c:out>
		（${pageViewData.totalRecord}个）
	</div>

	<!--页面左侧分类浏览部分-->
	<div class="browse_left">
		<div class="browse">
			<div class="browse_t">${producttype.name}</div>

			<h2>
				<span class="gray">浏览下级分类</span>
			</h2>
			<ul>
				<c:forEach items="${producttype.childtype}" var="childtype">
					<li class='bj_blue'><a
						href="<html:rewrite action="/product/list/display"/>?typeId=${childtype.typeId}">${childtype.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<DIV class="browse" ><!-- id="sy_biankuang" -->
			<DIV class="browse_t">最畅销${producttype.name}</DIV>
			<DIV style="PADDING-LEFT: 10px; COLOR: #333333" id="salespromotion">
				
			</DIV>
		</DIV>
		<br />
		<div class="browse">
			<div class="browse_t">您浏览过的商品</div>
			<ul id="viewHistory"></ul>
		</div>
	</div>
	<!--页面右侧分类列表部分开始-->
	<div class="browse_right">
		<div class="select_reorder">
			<div class="reorder_l">
				请选择排序方式：
				<c:if test="${'selldesc'==param.sort}">
					<strong><em>销量多到少</em></strong>
				</c:if>
				<c:if test="${'selldesc'!=param.sort}">
					<a title='按销量降序'
						href="<html:rewrite action="/product/list/display"/>?sort=selldesc&typeId=${param.typeId}&style=${param.style}">销量多到少</a>
				</c:if>
				|
				<c:if test="${'sellpricedesc'==param.sort}">
					<strong><em>价格高到低</em></strong>
				</c:if>
				<c:if test="${'sellpricedesc'!=param.sort}">
					<a title='价格高到低'
						href="<html:rewrite action="/product/list/display"/>?sort=sellpricedesc&typeId=${param.typeId}&style=${param.style}">价格高到低</a>
				</c:if>
				|
				<c:if test="${'sellpriceasc'==param.sort}">
					<strong><em>价格低到低</em></strong>
				</c:if>
				<c:if test="${'sellpriceasc'!=param.sort}">
					<a title='价格低到高'
						href="<html:rewrite action="/product/list/display"/>?sort=sellpriceasc&typeId=${param.typeId}&style=${param.style}">价格低到高</a>
				</c:if>
				|
				<c:if test="${empty param.sort}">
					<strong><em>最近上架时间</em></strong>
				</c:if>
				<c:if test="${!empty param.sort}">
					<a title='价格低到高'
						href="<html:rewrite action="/product/list/display"/>?sort=&typeId=${param.typeId}&style=${param.style}">最近上架时间</a>
				</c:if>
			</div>

			<div class="reorder_r">
				显示方式：
				<c:if test="${param.style=='imagetext'}">
					<strong><em>图文版</em></strong>
				</c:if>
				<c:if test="${param.style!='imagetext'}">
					<a
						href="<html:rewrite action="/product/list/display"/>?sort=${param.sort}&typeId=${param.typeId}&sex=${param.sex }&brandid=${param.brandid}&style=imagetext">图文版</a>
				</c:if>
				|
				<c:if test="${param.style=='imagetext'}">
					<a
						href="<html:rewrite action="/product/list/display"/>?sort=${param.sort}&typeId=${param.typeId}&sex=${param.sex }&brandid=${param.brandid}&style=image">图片版</a>
				</c:if>
				<c:if test="${param.style!='imagetext'}">
					<strong><em>图片版</em></strong>
				</c:if>
			</div>
			<div class="emptybox"></div>
			<div class="brand">
				<div class="FindByHint">
					按<strong>品牌</strong>选择：
				</div>
				<ul class="CategoryListTableLevel1">
					<c:forEach items="${brands}" var="brand">
						<li><a
							href="<html:rewrite action="/product/list/display"/>?sort=${param.sort}&typeId=${param.typeId}&brandid=${brand.code}&sex=${param.sex}&style=${param.style}">${brand.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="SubCategoryBox">
				<div class="FindByHint">
					按<strong>男女款</strong>选择：
				</div>
				<ul class="CategoryListTableLevel1">
					<li><a
						href="<html:rewrite action="/product/list/display"/>?sort=sellpriceasc&typeId=${param.typeId}&sex=MAN&brandid=${param.brandid}&style=${param.style}">男款</a></li>
					<li><a
						href="<html:rewrite action="/product/list/display"/>?sort=sellpriceasc&typeId=${param.typeId}&sex=WOMAN&brandid=${param.brandid}&style=${param.style}">女款</a></li>
					<li><a
						href="<html:rewrite action="/product/list/display"/>?sort=sellpriceasc&typeId=${param.typeId}&sex=NONE&brandid=${param.brandid}&style=${param.style}">男女均可</a></li>
					<li><a class="red"
						href="<html:rewrite action="/product/list/display"/>?sort=sellpriceasc&typeId=${param.typeId}&style=${param.style}">全部</a></li>
				</ul>
			</div>
		</div>
		<div id="divNaviTop" class="number">
			<div class="number_l">
				以下<span class='number_white'>${pageViewData.totalRecord}</span>条结果按<span
					class="number_white"> <c:choose>
						<c:when test="${'selldesc'==param.sort}">销量多到少</c:when>
						<c:when test="${'sellpricedesc'==param.sort}">价格高到低</c:when>
						<c:when test="${'sellpriceasc'==param.sort}">价格低到高</c:when>
						<c:otherwise>最近上架时间</c:otherwise>
					</c:choose>
				</span>排列，显示方式是<span class="number_white"><c:if
						test="${param.style=='imagetext'}">图文版</c:if>
					<c:if test="${param.style!='imagetext'}">图片版</c:if></span> 每页显示<span
					class="number_white">${pageViewData.maxResult}</span>条
			</div>
			<div class="turnpage">
				<div>
					<c:if test="${pageViewData.currentPage == 0 }"><em>第1页</em></c:if> 
					<c:if test="${pageViewData.currentPage > 0 }"><em>第${pageViewData.currentPage}页</em></c:if> 
				</div>
			</div>
		</div>
		
		<div class='goods_pic'>
			<!---------------------------LOOP START------------------------------>
			<c:forEach items="${pageViewData.records}" var="entry">
				<div class="detail" align="center">
					<div class="goods" align="center" 
						style=" cursor:pointer;background:url(<c:forEach items="${entry.productStyles}" var="pic">${pic.image140FullPath}</c:forEach>) center  center no-repeat;width:200px;">
						<a href="/static/product/${entry.type.typeId}/${entry.id}.shtml"
							target="_blank"> <img src="/images/global/product_blank.gif"
							alt="${entry.name}" width="140" height="168" border="0" /></a>
					</div>
					<h2>
						<a href="/static/product/${entry.type.typeId}/${entry.id}.shtml"
							target="_blank" title="${entry.name}">${entry.name}</a>
					</h2>
					<div class="save_number">
						<s>￥${entry.marketprice}</s> <strong><em>￥${entry.sellprice}</em></strong>
						<c:if test="${entry.savedPrice>0}">
							 节省：<strong style="color: red;font-size:14px;">￥${entry.savedPrice}</strong> 
						</c:if>
						
					</div>
					<div class="an_img" align="center">
						<a href="/static/product/${entry.type.typeId}/${entry.id}.shtml"><img
							src='/images/sale.gif' width='84' height='24' border='0'
							class='a_1' /></a>
					</div>
				</div>
			</c:forEach>
			<!----------------------LOOP END------------------------------->
			<div class='emptybox'></div>
		</div>
		<div id="divNaviBottom" class="page_number">
			<div class="turnpage turnpage_bottom">
				<c:forEach begin="${pageViewData.pageIndex.startindex}"
					end="${pageViewData.pageIndex.endindex}" var="wp">
					<c:if test="${pageViewData.currentPage==wp}">
						<div class='red'>${wp}</div>
					</c:if>
					<c:if test="${pageViewData.currentPage!=wp}">
						<div class="page">
							<a
								href="<html:rewrite action="/product/list/display"/>?page=${wp}&brandid=${param.brandid}&sex=${param.sex}&sort=selldesc&style=${param.style}&typeId=${param.typeId}">[${wp}]</a>
						</div>
					</c:if>
				</c:forEach>
				<div>&nbsp;&nbsp;</div>
				跳转到第 <select name="selectPage" class="kuang"
					onChange="javaScript:topage(this.value)">
					<c:forEach begin="1" end="${pageViewData.totalPage}" var="wp">
						<option value="${wp}"
							<c:if test="${pageViewData.currentPage==wp}">selected</c:if>>
							${wp}</option>
					</c:forEach>
				</Select>页
				<SCRIPT LANGUAGE="JavaScript">
				<!--
					function topage(pagenum) {
						window.location.href = '<html:rewrite action="/product/list/display"/>?sort=selldesc&style=${param.style}&brandid=${param.brandid}&sex=${param.sex}&typeId=${param.typeId}&page='
								+ pagenum;
					}
				//-->
				</SCRIPT>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>