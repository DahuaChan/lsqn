<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>修改管理员</title>
</head>
<body>
	<s:debug></s:debug>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<div>
		<jsp:include page="../commen/left.jsp"></jsp:include>
		<div class="centercontent">
			<div class="pageheader notab">
				<h1 class="pagetitle">修改管理员信息</h1>
				<span class="pagedesc">管理员信息错了？没事，咱可以改！！！</span>
				<span class="pagedesc">别改错咯！！！</span>
			</div>
			<div class="from">
				<s:form action="admin_update" method="post">
					<s:hidden name="admin_id"></s:hidden>
					<s:textfield label="账号" name="admin_account" />
					<s:select name="admin_type" list="#adminType" label="类型"/>
					<s:submit value="修改管理员" />
				</s:form>
			</div>
		</div>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>