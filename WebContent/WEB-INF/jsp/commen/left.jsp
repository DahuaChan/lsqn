<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="vernav2 iconmenu">
		<ul>

			<s:if test="#session.admin==null">
				<li>
					<a href="message_addInput.action" class="editor">
						我要投稿
					</a>
				
				</li>
			</s:if>
			<li><a href="#gaojian" class="editor">稿件信息</a> <span
				class="arrow"></span>
				<ul id="gaojian">
					<li><a href="message_listByUnRead.action">未审稿件</a></li>
					<li><a href="message_listByIsRead.action">已审稿件</a></li>
					<li><a href="message_list.action">所有稿件</a></li>
				</ul></li>
			<li><a href="author_list.action" class="elements">投稿人信息</a></li>
			<s:if test="#session.admin!=null">
				<li><a href="admin_list.action" class="inbox">管理员信息</a></li>
			</s:if>
		</ul>
		<a class="togglemenu"></a> <br /> <br />
	</div>
</body>
</html>