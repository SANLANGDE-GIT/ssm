<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>图书列表</title>
		<%
			String basePath = request.getScheme() 
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/";
		%>
		<base href="${pageContext.request.contextPath}" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			$(function () {
				$("a.deleteBook").click(function () {
					var name =$(this).parent().parent().find("td:first").text();
					return confirm("您确定要删除【"+name+"】吗？");
				})
			});
		</script>
		<style type="text/css">
			table {
				border: 1px blue solid;
				width: 700px;
				border-collapse: collapse;
			}
			td,th{
				border: 1px green solid;
			}
			div.menu {
				width: 700px;
				text-align: right;
			}
		</style>
	</head>
	<body>
		<center>
			<h2>图书列表管理页面</h2>
			<div class="menu"><a href="${pageContext.request.contextPath}/book/bookEdit.jsp">添加图书</a></div>
			<table>
				<tr bgcolor="#FF8888">
					<th>书名</th>
					<th>作者</th>
					<th>价格</th>
					<th>销量</th>
					<th>库存</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${requestScope.page.items}" var="book">
					<tr>
						<td>${book.name}</td>
						<td>${book.author}</td>
						<td>${book.price}</td>
						<td>${book.sales}</td>
						<td>${book.stock}</td>
						<td>
							<a class="deleteBook" href="${pageContext.request.contextPath}/book/delete?id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>、
							<a href="${pageContext.request.contextPath}/book/queryBook?id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%-- 分页开始 --%>
			<div id="page_nav">
				<c:choose>
					<c:when test="${requestScope.page.pageTotal<=5}">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="${requestScope.page.pageTotal}"/>
					</c:when>
					<c:when test="${requestScope.page.pageTotal>5}">
						<c:choose>
							<c:when test="${requestScope.page.pageNo<=3}">
								<c:set var="begin" value="1"/>
								<c:set var="end" value="5"/>
							</c:when>
							<c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
								<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
								<c:set var="end" value="${requestScope.page.pageTotal}"/>
							</c:when>
							<c:otherwise>
								<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
								<c:set var="end" value="${requestScope.page.pageNo+2}"/>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>

				<a href="${pageContext.request.contextPath}/${requestScope.page.url}?pageNo=1">首页</a>&nbsp;&nbsp;
				<c:if test="${requestScope.page.pageNo!=1}">
					<a id="page_pro" href="${pageContext.request.contextPath}/${requestScope.page.url}?pageNo=${requestScope.page.pageNo-1}">上一页</a>&nbsp;&nbsp;
				</c:if>
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:if test="${i==requestScope.page.pageNo}">
						【${i}】&nbsp;
					</c:if>
					<c:if test="${i!=requestScope.page.pageNo}">
						<a href="${pageContext.request.contextPath}/${requestScope.page.url}?pageNo=${i}">${ i }</a>&nbsp;
					</c:if>
				</c:forEach>
				<c:if test="${requestScope.page.pageNo!=requestScope.page.pageTotal}">
					<a id="page_next" href="${pageContext.request.contextPath}/${requestScope.page.url}?pageNo=${requestScope.page.pageNo+1}">下一页</a>&nbsp;&nbsp;
				</c:if>
				<a href="${pageContext.request.contextPath}/${requestScope.page.url}?pageNo=${requestScope.page.pageTotal}">末页</a>&nbsp;&nbsp;
				共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
				到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页&nbsp;&nbsp;
				<input id="searchPageBtn" type="button" value="确定">

				<script type="text/javascript">
					$(function () {
						var pageTotal=${requestScope.page.pageTotal};
						var pageNo=${requestScope.page.pageTotal};
						//
						if(pageNo==1){
							$("#page_pro").attr("disabled","true");
						}
						if(pageNo==pageTotal){
							$("#page_pro").attr("disabled","true");
						}
						//跳转页面
						$("#searchPageBtn").click(function () {
							pageNo=$("#pn_input").val();
							var numberPatten=/^\d*$/
							if(numberPatten.test(pageNo)){
								if(pageNo>0&&pageNo<=pageTotal){
									location.href="${pageContext.request.contextPath}/${pageScope.basePath}${requestScope.page.url}?pageNo="+pageNo;
								}else {
									alert("您输入的数字不在范围内！");
									$("#pn_input").val("");
								}
							}else {
								alert("请输入数字！");
								$("#pn_input").val("");
							}
						});
					});
				</script>

			</div>
			<%-- 分页结束 --%>
		</center>
	</body>
</html>