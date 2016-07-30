<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>岭师青年投稿平台欢迎您</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/index.js"></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loginpage">
	<img
		style="position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; z-Index: -1;"
		src="images/patternbg1.png" />
	<div class="loginbox">
		<div class="loginboxinner">

			<div class="logo">
				<h1 class="logo">
					岭师青年<span>投稿系统</span>
				</h1>
				<span class="slogan"><h3>管理员登陆</h3></span>
			</div>
			<form id="login" action="login_login.action" method="post">

				<div class="username">
					<div class="usernameinner">
						<input type="text" name="admin_account" id="username" />
					</div>
				</div>

				<div class="password">
					<div class="passwordinner">
						<input type="password" name="admin_password" id="password" />
					</div>
				</div>
				<div class="username">
					<div class="usernameinner">
						<input type="text" name="validNum" id="validNum" />
					</div>
				</div>
				<div style="float: right; margin-bottom: 15px; top: 0;">
					<img alt="" id="validaNumImage"
						src="login_getValidaNumImage.action"
						onclick="reflashValidImage();"> <a href="#"
						onclick="reflashValidImage();">看不清，换一张</a>
				</div>

				<button>登录</button>
				<div class="keep">
					<span> </span>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../commen/foot.jsp"></jsp:include>

	<script type="text/javascript">
		function reflashValidImage() {
			document.getElementById("validaNumImage").src = "login_getValidaNumImage.action?index"
					+ Math.random();

		}
	</script>
</body>
</html>