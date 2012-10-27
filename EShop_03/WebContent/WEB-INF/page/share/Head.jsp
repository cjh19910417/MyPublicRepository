<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/page/share/taglib.jsp" %>
<!--<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="http://localhost/" target=_top><img alt=南雄最大、最安全的手机、数码用品网上交易平台！ src=".../images/global/logo.png" border=0 /></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/shopping/cart.do" ><font color="blue"><Strong>购物车</Strong></font></a> </li>
        <li><a href="/user/regUI.do" >新用户注册</a> </li>
        <c:if test="${ empty user}"><li><a href="/user/logonUI.do" >用户登录</a> </li></c:if> 
         <c:if test="${!empty user}"><li><a href="/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="">帮助中心</a> </li>
        <li class="phone">客服QQ: 331075644</li>
      </ul>
    </div>
  </div>
  <div id="ChannelMenu">
	<UL id="ChannelMenuItems">
		<LI id="MenuHome" class='<c:if test="${empty param.typeId }">focus</c:if><c:if test="${!empty param.typeId }">ChannelMenuItems</c:if>'><a href="/"><span>首页</span></a></LI>
		<LI id="ProducType2Home" class='<c:if test="${param.typeId==3 }">focus</c:if><c:if test="${param.typeId!=3 }">ChannelMenuItems</c:if>'><a href="/product/list/display.do?typeId=3"><span>手机通讯</span></a></LI>
		<LI id="ProducType1Home" class='<c:if test="${param.typeId==4 }">focus</c:if><c:if test="${param.typeId!=4 }">ChannelMenuItems</c:if>'><a href="/product/list/display.do?typeId=4"><span>手机配件</span></a></LI>
		<LI id="ProducType8Home" class='<c:if test="${param.typeId==7 }">focus</c:if> <c:if test="${param.typeId!=7 }">ChannelMenuItems</c:if> '><a href="/product/list/display.do?typeId=7"><span>时尚影音</span></a></LI>
		<LI id="ProducType9Home" class='<c:if test="${param.typeId==6 }">focus</c:if><c:if test="${param.typeId!=6 }">ChannelMenuItems</c:if>'><a href="/product/list/display.do?typeId=6"><span>数码配件</span></a></LI>
		<LI id="MyAccountHome" class='<c:if test="${param.typeId==11 }">focus</c:if><c:if test="${param.typeId!=11 }">ChannelMenuItems</c:if>'><a href="#"><span>我的账户</span></a></LI>
	</UL>
  SearchBox
<div id="SearchBox">
	  <div id="SearchBoxTop">
		  <div id="SearchForm">
			<form action="/product/query.do" method="post" name="search" id="search">

			 <span class="name">商品搜索: </span><input id="word" name="word" accesskey="s" size="100" maxlength="100" value="${productQueryForm.word }"/>

			  <input type="submit" value="搜 索" id="DoSearch"/>
			</form>
		  </div>
	  </div>
      <div id="HotKeywords">
			<ul>
				<li><span>
今天是:<jian:date pattern="yyyy年MM月dd日">${now }</jian:date>&nbsp&nbsp
 您好<SCRIPT language=JavaScript src="/js/welcome.js"></SCRIPT>，欢迎来到EShop!</span></li>
				<li>热门搜索：</li>
				<%
					request.setAttribute("hotkey1", "小米");
					request.setAttribute("hotkey2", "iphone");
					request.setAttribute("hotkey3", "三星Galaxy SIII");
				%>
<li> <html:link action="/product/query" paramId="word" paramName="hotkey1">${hotkey1 }</html:link></li>
<li> <html:link action="/product/query" paramId="word" paramName="hotkey2">${hotkey2 }</html:link></li>
<li> <html:link action="/product/query" paramId="word" paramName="hotkey3">${hotkey3 }</html:link> </li>

			</ul>
      </div>
   </div>
</div>
</div>

<!-- Head End -->
<div class="profile" id="profile">
  <div id="profileContext"><span>
今天是:<jian:date pattern="yyyy年MM月dd日">${now }</jian:date>&nbsp&nbsp
 您好<SCRIPT language=JavaScript src="/js/welcome.js"></SCRIPT>，欢迎来到EShop!</span>
 <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/shopping/cart.do" ><font color="blue"><Strong>购物车</Strong></font></a> </li>
        <li><a href="/user/regUI.do" >新用户注册</a> </li>
        <c:if test="${ empty user}"><li><a href="/user/logonUI.do" >用户登录</a> </li></c:if> 
         <c:if test="${!empty user}"><li><a href="#">${user.username }</a><a href="/user/logout.do" >[退出]</a> </li></c:if>
        <li><a href="">帮助中心</a> </li>
        <li class="phone">客服QQ: 331075644</li>
      </ul>
    </div>
 </div>
 
</div>
<label></label>
<div class="container" id="container">
  <a href="http://localhost/" target=_top><img alt=南雄最大、最安全的手机、数码用品网上交易平台！ width="250" height="35"  src="/images/global/logo.png" border=0 /></a>
  <!-- <img src="file:///D|/用户目录/Documents/南雄数码网/logo.png" width="250" height="35" /> -->
  <div id="searchdiv">
    <form id="searchform" name="search" method="post" action="/product/query.do">
      <input name="Submit" type="submit" id="submit" value="搜 索" />
      <input id="searchbar" type="text" name="word" placeholder="输入'小米'试试" value="${productQueryForm.word }" />
    </form>
  </div>
  <div id="navbar">
  
			
			<ul id="navitems">
				<LI id="nav-home" class='<c:if test="${empty param.typeId }">focus</c:if>' onclick="javascript:location.href='/'"><a href="/"><span>首页</span></a></LI>
				<LI id="nav-mobile" class='<c:if test="${param.typeId==3 }">focus</c:if>' onclick="javascript:location.href='/product/list/display.do?typeId=3'"><a href="/product/list/display.do?typeId=3"><span>手机通讯</span></a></LI>
				<LI id="nav-accessories" class='<c:if test="${param.typeId==4 }">focus</c:if>' onclick="javascript:location.href='/product/list/display.do?typeId=4'"><a href="/product/list/display.do?typeId=4"><span>手机配件</span></a></LI>
				<LI id="nav-media" class='<c:if test="${param.typeId==7 }">focus</c:if>  ' onclick="javascript:location.href='/product/list/display.do?typeId=7'"><a href="/product/list/display.do?typeId=7"><span>时尚影音</span></a></LI>
				<LI id="nav-digitaround" class='<c:if test="${param.typeId==6 }">focus</c:if>' onclick="javascript:location.href='/product/list/display.do?typeId=6'"><a href="/product/list/display.do?typeId=6"><span>数码配件</span></a></LI>
				<LI id="nav-cart" class='<c:if test="${param.typeId==11 }">focus</c:if>' onclick="javascript:location.href='#'"><a href="#"><span>我的账户</span></a></LI>
								
			</ul>
		
  </div>
</div>