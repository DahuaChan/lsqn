<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	.page{
		font-size: 18px;
		text-align: center;
		margin: 0 auto;
		padding-top: 30px;
		
	}
</style>
<div class="page">
	<pg:pager maxPageItems="15" items="${param.items }"
		export="curPage=pageNumber" url="${param.url }">
		<c:forEach items="${param.params }" var="p">
			<pg:param name="${p }" />
		</c:forEach>
		<pg:last>
共${param.items }记录,共${pageNumber }页,
</pg:last>
当前第${curPage }页
<pg:first>
			<a href="${pageUrl }">首页</a>&nbsp;&nbsp;
		</pg:first>
		<pg:prev>
			<a href="${pageUrl }">上一页</a>&nbsp;&nbsp;
		</pg:prev>
		<pg:pages>

			<c:if test="${curPage eq pageNumber }">
	[${pageNumber }]
</c:if>
			<c:if test="${curPage ne pageNumber }">
				<a href="${pageUrl }">${pageNumber }</a>
			</c:if>
			&nbsp;&nbsp;
		</pg:pages>
		<pg:next>
			<a href="${pageUrl }">下一页</a>&nbsp;&nbsp;
		</pg:next>
		<pg:last>
			<a href="${pageUrl }">尾页</a>
		</pg:last>
	</pg:pager>
</div>