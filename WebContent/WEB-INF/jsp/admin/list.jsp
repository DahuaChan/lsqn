<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>管理员账号管理</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="centercontent">
		<div class="pageheader">
			<h1 class="pagetitle">管理员信息</h1>
			<span class="pagedesc">所有的管理员都在这啦！！！</span>
			<ul class="hornav">
                <li class="current"><a href="./admin_list.action">管理员信息</a></li>
                <li><a href="./admin_addInput.action">添加管理员</a></li>
            </ul>
		</div>
		<div class="from">
			<table class="stdtable">
				<tbody>
					<tr>
						<td><b>序号</b></td>
						<td><b>账号</b></td>
						<td><b>类型</b></td>
						<td><b>操作</b></td>
					</tr>
					<s:iterator value="#adp.datas">
						<tr>
							<td>${admin_id }</td>
							<td>${admin_account }</td>
							<td>
								<s:if test="admin_type=='0'.toString()">
									<font color="blue">普通</font>管理员
								</s:if>
								<s:else>
									<font color="red">超级</font>管理员
								</s:else>
							</td>
							<td>
							<a href="./admin_updateInput.action?admin_id=${admin_id }">修改</a>
							<a href="./admin_delete.action?admin_id=${admin_id }"><font color="red">删除</font></a>							
							</td>
						</tr>
					</s:iterator>

				</tbody>
			</table>
			</div>
		<jsp:include page="../commen/pager.jsp">
			<jsp:param value="admin_list.action" name="url" />
			<jsp:param value="${adp.totalRecord }" name="items" />
		</jsp:include>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>