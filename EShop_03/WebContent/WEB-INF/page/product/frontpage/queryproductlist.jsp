<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>产品搜索 -南雄数码网</title>    
	<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
	<link href="/css/product/list.css" rel="stylesheet" type="text/css" />	
	<link href="/css/global/topsell.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function getViewHistory(){
		var viewHistoryUI = document.getElementById('viewHistory');		
		if(viewHistoryUI){
			viewHistoryUI.innerHTML= "数据正在加载...";
			send_request(function(value){viewHistoryUI.innerHTML=value},
					 "<html:rewrite action="/product/switch"/>?method=getViewHistory", true);
		}
	}
	function pageInit(){
		getViewHistory();
	}
	
	function topage(pagenum){
		var form = document.forms["productquery"];
		form.page.value= pagenum;
		form.submit();
	}
//-->
</SCRIPT>
</head>

<body class="ProducTypeHome2" onload="javascript:pageInit()">
	<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
	<div id="main">
    <div id="position">您现在的位置: <a href="/" name="linkHome">南雄数码网</a> &gt;&gt; <em>产品查询结果</em> （${pageViewData.totalRecord}个）
	</div>

    <!--页面左侧分类浏览部分-->
    <div class="browse_left">
		 <div class="browse">
	          <div class="browse_t">您浏览过的商品</div>
			  <ul id="viewHistory"></ul>
	     </div>
    </div>
    <!--页面右侧分类列表部分开始-->
    <div class="browse_right">
	     <div id="divNaviTop" class="number">
	          <div class="number_l">以下查询到关于<span class="number_white" style="color: red;font-weight: bold;font-size: 12px;">“${productQueryForm.word }”</span>关键字的<span class='number_white'>${pageViewData.totalRecord}</span>条结果　每页显示<span class="number_white">${pageViewData.maxResult}</span>条</div>
		      <div class="turnpage">
                <div><em>第${pageViewData.currentPage}页</em></div>
		      </div>
	     </div>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageViewData.records}" var="entry">	
		<div class="goodslist">
          <div class="goods" style="cursor:pointer;background:url(<c:forEach items="${entry.productStyles}" var="pic">${pic.image140FullPath}</c:forEach>) center center no-repeat"><a href="/static/product/${entry.type.typeId}/${entry.id}.shtml" target="_blank">
            <img src="/images/global/product_blank.gif" alt="${entry.name}" width="140" height="168"  border="0"/></a></div>
          <div class="goods_right">
                <h2><a href="/static/product/${entry.type.typeId}/${entry.id}.shtml" target="_blank" title="${entry.name}">${entry.name}</a></h2>
	           <div class="message"><ul>
			  <c:if test="${!empty entry.brand}"> <li>品牌：${entry.brand.name}</li></c:if>
			   </ul></div>
	           <div class="content">&nbsp;&nbsp;&nbsp;<c:out value="${fn:substring(entry.simpleDescription,0,200)}" escapeXml="false"/></div>
	           <div class="message_bottom">
	                <div class="save"><s>￥${entry.marketprice}</s>　<strong><em>￥${entry.sellprice}</em></strong>　节省：${entry.savedPrice}</div>
			        <div class="buy"><a href="/static/product/${entry.type.typeId}/${entry.id}.shtml"><img src='/images/sale.gif' width='84' height='24' border='0' /></a></div>
	           </div>
          </div>
          <div class="empty_box"></div>
        </div>
</c:forEach>
<!----------------------LOOP END------------------------------->	
	     <div id="divNaviBottom" class="page_number">
	     <div class="turnpage turnpage_bottom">	
	     <c:forEach begin="${pageViewData.pageIndex.startindex}" end="${pageViewData.pageIndex.endindex}" var="wp">
		    <c:if test="${pageViewData.currentPage==wp}"><div class='red'>${wp}</div></c:if>
		    <c:if test="${pageViewData.currentPage!=wp}"><div class="page"><a href="javaScript:topage(${wp})">[${wp}]</a></div></c:if>
		</c:forEach>
		<div>&nbsp;&nbsp;</div><form action="<html:rewrite action="/product/query"/>" method="post" name="productquery">
		<input type="hidden" name="word" value="${productQueryForm.word }"/>
		<input type="hidden" name="page" value="${productQueryForm.page }"/>
		跳转到第
		<select name="selectPage" class="kuang" onChange="javaScript:topage(this.value)">
				<c:forEach begin="1" end="${pageViewData.totalPage}" var="wp">
				<option value="${wp}" <c:if test="${pageViewData.currentPage==wp}">selected</c:if>> ${wp} </option></c:forEach>
		</Select>页</form>
         </div>
	     </div>
    </div></div>
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>