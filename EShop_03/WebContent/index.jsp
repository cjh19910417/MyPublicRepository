<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>南雄数码网-南雄最大的数码购物网站!</title>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="南雄,数码,电商,购物,手机,数码相机">
				<meta http-equiv="description" content="南雄数码网-南雄最大的数码购物网站!">
					<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
					<link href="/css/index1.css" rel="stylesheet" type="text/css">
					
				
</head>

<body>
	<jsp:include page="/WEB-INF/page/share/Head.jsp" />
	<div id="main">
		<br><br>

				<div id="xmFocus" class="xmFocus cfl" style="width:960px; margin: 0 auto;">
					<div class="1"
						style=" z-index:2; background-image: url(/images/brand/2012/10/17/16/646e1f6d-996a-4d82-81b0-8054d3e5f677.jpg); border:1px; background-color: rgb(255, 255, 255); display: block; background-position: 50% 50%; background-repeat: no-repeat no-repeat;">
						<a href="/static/product/87/3.shtml" target="_blank"
							style="display: inline-block; width: 960px; height: 430px;"></a>
					</div>
					<div class="2"
						style=" z-index:1; background-image: url(http://p.www.xiaomi.com/images/xmFocus/xmFocus_12092902.jpg); background-color: rgb(255, 255, 255); display: block; background-position: 50% 50%; background-repeat: no-repeat no-repeat;">
						<a href="/static/product/87/1.shtml" target="_blank"
							style="display: inline-block; width: 960px; height: 430px;"></a>
					</div>
					<div class="3" 
						style=" z-index:1; background-image: url(http://p.www.xiaomi.com/images/xmFocus/xmFocus_12101202.jpg); background-color: rgb(255, 255, 255); display: block; background-position: 50% 50%; background-repeat: no-repeat no-repeat;">
						<a
							href="/static/product/130/13.shtml"
							target="_blank"
							style="display: inline-block; width: 960px; height: 430px;"></a>
					</div>
					<ul style="width: 80px; right: 50%; margin-right: -465px;">
						
						<li value="3" class="" onmouseover="showImage(this)" onmouseout="hiddenImage(this)"></li>
						<li value="2" class="" onmouseover="showImage(this)" onmouseout="hiddenImage(this)"></li>
						<li value="1" class="" onmouseover="showImage(this)" onmouseout="hiddenImage(this)"></li>
					</ul>
				</div>
	</div>
	<br><br>
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>
<SCRIPT language="JavaScript">
		function showImage(object)
		{
			
			object.className="on";
			var images = document.getElementById("xmFocus").getElementsByTagName("div");
			for(var i=0;i<images.length;i++)
			{
				if(images[i].className==object.value)
				{
					images[i].style.display="inline-block";
					
				}else{
					images[i].style.display="none";
				}
				
			}
		}
		function hiddenImage(object) {
			object.className="";
		} 
	</script>	