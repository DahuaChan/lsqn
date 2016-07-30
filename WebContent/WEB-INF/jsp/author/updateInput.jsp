<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>修改个人信息</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="centercontent">
			<div class="pageheader notab">
				<h1 class="pagetitle">修改个人信息</h1>
				<span class="pagedesc">信息错了？没事，咱可以改！！！</span>
				<span class="pagedesc">别改错咯！！！</span>
				<span class="pagedesc"><font color="red" size="15px">注意：邮箱不支持更改</font></span>
			</div>
			<div class="from" style="width: 50%">
				<s:form action="author_update" method="post">
					<s:hidden name="a_id"></s:hidden>
					<s:textfield label="邮箱" name="a_email" />
					<s:textfield label="姓名" name="a_name" />
					<s:textfield label="昵称" name="a_nickname" />
					<s:submit value="修改" />
				</s:form>
			</div>
		</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>