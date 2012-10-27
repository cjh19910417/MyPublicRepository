<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
	<c:forEach items="${viewHistory}" var="viewproduct" >
		<li class="bj_blue"><a href="/static/product/${viewproduct.type.typeId}/${viewproduct.id}.shtml" target="_blank" title="${viewproduct.name}">${viewproduct.name}</a></li></c:forEach>			