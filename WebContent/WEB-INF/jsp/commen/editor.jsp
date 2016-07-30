<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<style>
.box{
	padding-top: 10px;
}
.box a{width:75px;background:url(http://www.newxing.com/img/201011/button1.gif); border:0; height:21px; display:-moz-inline-box;display:inline-block;font-size:13px; text-decoration: none;
text-align:center;line-height:21px;}
.box a:hover{background:url(http://www.newxing.com/img/201011/button1.gif) 0 -21px;}
</style>

<script type="text/javascript" charset="utf-8"
	src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="UEditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function submitData() {
		var title = $("#msg_title").val();
		var content = UE.getEditor('editor').getContent();

		if (title == null || title == '') {
			alert("请输入标题！");
		} else if (content == null || content == '') {
			alert("请输入内容！");
		} else {
			$.post(
							"${pageContext.request.contextPath}/message_add.action",
							{
								'msg_title' : title,
								'msg_content' : content
							},
							function(result) {

								if (result.success) {
									location.href = "${pageContext.request.contextPath}/message_listByUnRead.action";
									alert("发布成功！");
									UE.getEditor('editor').setContent('',
											isAppendTo);
								} else {
									alert("发布失败！");
								}
							}, "json");
		}
	}
	function updateData() {
		var title = $("#msg_title").val();
		var content = UE.getEditor('editor').getContent();

		if (title == null || title == '') {
			alert("请输入标题！");
		} else if (content == null || content == '') {
			alert("请输入内容！");
		} else {
			$.post(
							"${pageContext.request.contextPath}/message_authorUpdate.action?msg_id=${msg_id }",
							{
								'msg_title' : title,
								'msg_content' : content
							},
							function(result) {

								if (result.success) {
									location.href = "${pageContext.request.contextPath}/message_listByUnRead.action";
									alert("修改成功！");
								} else {
									alert("修改失败！");
								}
							}, "json");
		}
	}
</script>

<form action="message_add.action" method="post">
	<div>
		<table>
			<tr>
				<td>题目：</td>
				<td><input type="text" name="msg_title" id="msg_title"
					size="100px" value="${msg_title }"></td>
			</tr>
			<tr>
				<td colspan="2">
					<span> 投稿日期：${msg_date }&nbsp;&nbsp;&nbsp;&nbsp; </span>
					<span>作者：${author.a_nickname }&nbsp;&nbsp;&nbsp;&nbsp;</span> 
					<span> 状态：&nbsp;&nbsp;&nbsp;&nbsp; 
					<s:if test="msg_status=1">
					未审核
					</s:if> <s:elseif test="msg_status=2">
					已录用
					</s:elseif> <s:else>
					未通过
					</s:else>
					</span>
					</td>
			</tr>
		</table>
	</div>
	<div style="float: none;">
		<script id="editor" type="text/plain"
			style="width: 80%; height: 500px;">
		${msg_content}
		</script>
	</div>
	<div class="box">
		<s:if test="msg_title==null">
			
			<a href="javascript:submitData()">点我投稿</a>
		</s:if><s:else>
			<h3><a href="javascript:updateData()">点我修改</a></h3>
		</s:else>
	</div>
</form>
<script type="text/javascript">
	var ue = UE.getEditor('editor');
</script>