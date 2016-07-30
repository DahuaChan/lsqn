<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>发现异常:
${exception.message }
</h1>
<br>
<a href="./index.jsp">3秒后系统会自动跳转，也可点击本处直接跳</a>
<%
response.setHeader("refresh", "3;URL=index.jsp");
%>
</body>
</html>