<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>岭师青年欢迎你</title>
</head>
<%
	if(session.getAttribute("author")==null)
		response.sendRedirect("index.jsp");
%>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>