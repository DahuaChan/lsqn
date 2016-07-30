<%@page import="cn.cdahua.model.Pager"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<title>所有稿件</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<div class="centercontent">
		<div class="pageheader">
			<h1 class="pagetitle">稿件信息</h1>
			<span class="pagedesc">已经审核的稿件都在这啦！！！</span>
			<ul class="hornav">
				<li><a href="./message_listByUnRead.action">未审稿件</a></li>
				<li class="current"><a href="./message_listByIsRead.action">已录稿件</a></li>
				<li><a href="./message_list.action">所有稿件</a></li>
			</ul>
		</div>
		<div class="from">
			<table class="stdtable">
				<tbody>
					<tr>
						<td><b>序号</b></td>
						<td width="30%"><b>题目</b></td>
						<td><b>状态</b></td>
						<td><b>作者</b></td>
						<td><b>审核人员</b></td>
						<td><b>投稿时间</b></td>
						<td><b>操作</b></td>
					</tr>
					<s:iterator value="#msgp.datas">
						<tr>
							<td>${msg_id }</td>
							<td>${msg_title }</td>
							<td><s:if test="msg_status=='0'.toString()">
									<font color="gray">未录用</font>
								</s:if> <s:elseif test="msg_status=='1'.toString()">
									<font color="blue">未审核</font>
								</s:elseif>
								<s:else>
									<font color="red">已录用</font>
								</s:else></td>
							<td>${author.getA_name() }</td>
							<td>${admin.getAdmin_account() }</td>
							<td>${msg_date }</td>
							<td><a href="./message_view.action?msg_id=${msg_id }">查看</a>
								<a href="./message_delete.action?msg_id=${msg_id }"><font
									color="red">删除</font></a></td>
						</tr>
					</s:iterator>

				</tbody>
			</table>
		</div>
		<jsp:include page="../commen/pager.jsp">
			<jsp:param value="message_list.action" name="url" />
			<jsp:param value="${msgp.totalRecord }" name="items" />
		</jsp:include>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
</body>
</html>