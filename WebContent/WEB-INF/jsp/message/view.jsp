<%@page import="cn.cdahua.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>查看文章</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="from">
		<div class="title">
			<h1>${msg_title }</h1>
		</div>
		<div class="info">
			<span> 投稿日期：${msg_date }&nbsp;&nbsp;&nbsp;&nbsp; </span> <span>作者：${author.a_nickname }&nbsp;&nbsp;&nbsp;&nbsp;
			</span> <span> 状态：&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"> <s:if test="msg_status == 1">
					未审核
				</s:if>
				<s:elseif test="msg_status == 2">
					已录用
				</s:elseif>
				<s:else>
					未通过
				</s:else>
			</font>
			</span>
		</div>
		<br>
		<div class="content">${msg_content }</div>
		<br>
		<s:if test="#session.admin != null && msg_status == 1">
			<div class="operation">
				<s:form action="message_adminUpdate" method="post">
					<s:hidden name="msg_id" />
					<s:radio name="msg_status" list="#{2:'通过',0:'不通过'}" listKey="key"
						listValue="value" />
					<s:submit value="提交" />
				</s:form>
			</div>
		</s:if>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>