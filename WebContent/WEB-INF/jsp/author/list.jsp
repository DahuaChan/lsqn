<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>投稿人信息</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="centercontent">
		<div class="pageheader notab">
			<h1 class="pagetitle">您的信息</h1>
			<span class="pagedesc">所有的投稿人都在这啦！！！</span>
		</div>
		<div class="from">
			<table class="stdtable">
				<tbody>
					<tr>
						<td><b>序号</b></td>
						<td><b>名字</b></td>
						<td><b>昵称</b></td>
						<td><b>账号</b>（邮箱）</td>
						<td><b>操作</b></td>
					</tr>
					<s:iterator value="#aup.datas">
						<tr>
							<td>${a_id }</td>
							<td>${a_name }</td>
							<td>${a_nickname }</td>
							<td>${a_email }</td>
							<s:if test="#session.admin!=null">
								<td><a href="./author_reset.action?a_id=${a_id }"><font
										color="red">重置密码</font></a></td>
							</s:if><s:else>
								<td><a href="./author_updateInput.action?a_id=${a_id }"><font
										color="red">修改信息</font></a></td>								
							</s:else>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<jsp:include page="../commen/pager.jsp">
			<jsp:param value="author_list.action" name="url" />
			<jsp:param value="${aup.totalRecord }" name="items" />
		</jsp:include>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>