<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp" %>
<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="/" target=_top><img alt="南雄最大、最安全的数码产品网上交易平台！" src="/images/global/logo.png" border=0 /></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/shopping/cart.do" ><font color="blue"><Strong>购物车</Strong></font></a> </li>
        <li><a href="/user/reg.do?method=regUI" >新用户注册</a> </li>
        <c:if test="${empty user}"><li><a href="/user/logon.do" >用户登录</a> </li></c:if>
        <c:if test="${!empty user}"> <li><a href="/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="">帮助中心</a> </li>
        <li class="phone">客服QQ ：331075644</li>
      </ul>
    </div>
  </div>
</div>
<!-- Head End -->