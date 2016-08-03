<%@page import="cn.cdahua.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.input.css" type="text/css" />
<script type="text/javascript" charset="utf-8"
	src="js/plugins/jquery-1.7.min.js"></script>
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
			</span> <span> 状态：&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"> <s:if
						test="msg_status == 1">
					未审核
				</s:if> <s:elseif test="msg_status == 2">
					已录用
				</s:elseif> <s:else>
					未通过
				</s:else>
			</font>
			</span>
		</div>
		<br>
		<div class="content" id="content">${msg_content }</div>
		<a href="javascript:readFile()"><span><font color="red" size="3">查看附件</font></span></a> <br>
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
<script type="text/javascript">
	function getUploadFileName() {
		var obj = $("#content a");
		var jsonData ='{';
		for (var i = 0; i < obj.length; i++){
			if(i+1 == obj.length){
				jsonData = jsonData+'"path'+i+'":"'+encodeURI(obj[i].text)+'?'+obj[i]+'"';
			}
			else{
				jsonData = jsonData+'"path'+i+'":"'+encodeURI(obj[i].text)+'?'+obj[i]+'",';
			}
		}
		jsonData = jsonData +'}';
		return $.parseJSON(jsonData);
	}
	
	function readFile() {
		var json = getUploadFileName();
		var jsonStr = encodeURI(JSON.stringify(json));
		if(jsonStr='{}')
			return;
		$.get("${pageContext.request.contextPath}/message_readFile.action",
				json
				,
				function(result) {
					if (result.success) {
						location.href = "${pageContext.request.contextPath}/message_readFileList.action?jsonData="+jsonStr;
					}else{
						return;
					}
				},
				"json"
		);
	}
</script>
</html>