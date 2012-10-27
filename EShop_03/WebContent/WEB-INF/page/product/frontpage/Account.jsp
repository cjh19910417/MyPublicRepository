<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>

						<tr>
							<td width="60%" align="center">如果您修改了商品数量，请点击 <img
								style="CURSOR: pointer;" alt="修改数量"
								src="/images/buy/update-t-sm.gif" value="修改数量" border="0"
								onClick="javascript:modifyAmount()"></td>
							<td width="9%" align="right"><DIV align="right">
									<SPAN class="price"><STRONG><B><FONT
												color="black">共计:</FONT></B></STRONG></SPAN>
								</DIV></td>
							<td width="11%" align="right"><DIV align="center">
									<SPAN class="price"><STRONG><B class="price"><FONT
												color="black">${buyCart.totalSellPrice } 元</FONT></B></STRONG></SPAN>
								</DIV></td>
							<td width="8%" align="right"><DIV align="right">
									<SPAN class="price"><STRONG><B><FONT
												color="black">节省:</FONT></B></STRONG></SPAN>
								</DIV></td>
							<td width="12%" align="right"><DIV align="center">
									<SPAN class="price"><STRONG><B class="price">${buyCart.totalSavePrice
												} 元</B></STRONG></SPAN>
								</DIV></td>
						</tr>
						<tr>
							<td colspan="3" align="right">&nbsp;</td>
							<td colspan="2" align="right"><img style="CURSOR: pointer;"
								src="/images/buy/az-by-split.gif" width="116" height="22"
								onClick="javascript:settleAccounts()"></td>
						</tr>
					