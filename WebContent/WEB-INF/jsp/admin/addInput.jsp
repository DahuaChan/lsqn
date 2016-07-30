<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Map"%>
<%@page import="cn.cdahua.model.SystemContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>添加管理员</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="centercontent">
		<div class="pageheader">
			<h1 class="pagetitle">添加管理员</h1>
			<span class="pagedesc">管理员不够？那就添加吧！</span>
			<ul class="hornav">
				<li><a href="./admin_list.action">管理员信息</a></li>
				<li class="current"><a href="./admin_addInput.action">添加管理员</a></li>
			</ul>
		</div>
		<div class="from">
			<s:form action="admin_add" method="post">
				<s:textfield label="账号" name="admin_account" />
				<s:password label="密码" name="admin_password" />
				<s:select name="admin_type" list="#adminType" label="类型" />
				<s:submit value="添加管理员" />
			</s:form>
		</div>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>