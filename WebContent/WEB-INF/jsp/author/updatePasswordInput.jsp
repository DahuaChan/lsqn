<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<title>修改密码</title>
</head>
<body>
	<jsp:include page="../commen/head.jsp"></jsp:include>
	<jsp:include page="../commen/left.jsp"></jsp:include>
	<form action="author_update.action" method="post">
	<input type="hidden" name="a_id" value="${session.author.getA_id() }">
		<table>
			<tr bgcolor="#FFFFFF">
				<td width="25%" bgcolor="#FFFFFF" align="right">原密码：</td>
				<td width="75%" bgcolor="#FFFFFF"><input type="password"
					id="oldpassword" name="oldpassword" size="22" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="25%" bgcolor="#FFFFFF" align="right">新密码：</td>
				<td width="75%" bgcolor="#FFFFFF"><input type="password"
					id="userPw1" size="22" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="25%" bgcolor="#FFFFFF" align="right">确认密码：</td>
				<td width="75%" bgcolor="#FFFFFF"><input type="password"
					id="userPw2" name="a_password" size="22" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="25%" bgcolor="#FFFFFF" align="right">&nbsp;</td>
				<td width="75%" bgcolor="#FFFFFF">
				<input type="submit" value="修改" onclick="check()" /> &nbsp;&nbsp;&nbsp;
				<input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
	<jsp:include page="../commen/foot.jsp"></jsp:include>
	<script language="javascript">
		function check() {
			var userPwReal = "${session.author.a_password}";
			if ($("input#oldpassword").val() != userPwReal) {
				alert("原密码不正确");
				return;
			}

			if ($("input#userPw1").val() == "") {
				alert("新密码不能空");
				return;
			}
			if ($("input#userPw1").val() != $("input#userPw2").val()) {
				alert("两次输入的密码不一致");
				return;
			}
				alert("密码已修改，请妥善保管！");
		}
	</script>
</body>
</html>