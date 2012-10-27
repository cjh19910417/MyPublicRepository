<%@ page language="java" pageEncoding="UTF-8"%>
总记录数:${pageViewData.totalRecord }&nbsp&nbsp|&nbsp 总页数:${pageViewData.totalPage }&nbsp&nbsp|&nbsp  当前页:${pageViewData.currentPage }
					<c:forEach begin="${pageViewData.pageIndex.startindex }" end="${pageViewData.pageIndex.endindex }" var="wp">
						<c:if test="${pageViewData.currentPage == wp }">
							<a class="a03" style="font-weight: bold;color: red;font-size: 15px;" href="javascript:topage('${wp}')">[第${wp }页]</a>
						</c:if>
						<c:if test="${pageViewData.currentPage != wp }">
							<a class="a03" href="javascript:topage('${wp}')">[第${wp }页]</a>
						</c:if>
					</c:forEach>