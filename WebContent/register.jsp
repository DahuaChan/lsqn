<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8 ]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>岭师青年欢迎您</title>


<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<link href="css/normalize.css" rel="stylesheet" />
<link href="css/jquery-ui.css" rel="stylesheet" />
<link href="css/jquery.idealforms.min.css" rel="stylesheet"
	media="screen" />

<style type="text/css">
body {
	font: normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;
	color: #222;
	background: url(images/patternbg1.png);
	background-size: 100%;
	overflow-y: scroll;
	padding: 60px 0 0 0;
}

#my-form {
	width: 755px;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 3em;
	border-radius: 3px;
	box-shadow: 0 0 2px rgba(0, 0, 0, .2);
}

#formheadline {
	width: 755px;
	margin: 0 auto;
}

#comments {
	width: 350px;
	height: 100px;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.idealforms.js"></script>
</head>
<body>

	<div class="row">

		<div class="eightcol last">

			<!-- Begin Form -->
			<div id="formheadline">
				<h1>岭师青年欢迎您</h1>
				<h3>
					<font color="red" size="">注册后邮箱作为登陆账号且不支持更改</font>
				</h3>
			</div>
			<form action="author_register.action" method="post" id="my-form">

				<section name="第一步">

				<div>
					<label>邮箱:</label><input id="email" name="a_email"
						data-ideal="required email" type="email" />
				</div>
				<div>
					<label>密码:</label><input id="pass" name="a_password"
						type="password" />
				</div>
				<div>
					<label>姓名:</label><input id="username" name="a_name" type="text" />
				</div>
				<div>
					<label>昵称:</label><input id="nickname" name="a_nickname"
						type="text" />
				</div>
				<div>
					<label>验证码:</label><input id="calidaNum" name="calidaNum"
						type="text" />
				</div>
				<div style="float: left; padding-top: 10px; padding-left: 20px">
					<img alt="" id="validaNumImage"
						src="login_getValidaNumImage.action"
						onclick="reflashValidImage();"> <a href="#"
						onclick="reflashValidImage();">看不清，换一张</a>
				</div>
				</section>
				<div>
					<hr />
				</div>

				<div>
					<button type="submit">提交</button>
					<button id="reset" type="button">重置</button>
				</div>

			</form>

			<!-- End Form -->

		</div>

	</div>



	<script type="text/javascript">
		var options = {

			onFail : function() {
				alert('存在' + $myform.getInvalid().length + ' 个项目没有填写或填写错误哦！！！')

			},

			inputs : {
				'a_password' : {
					filters : 'required pass',
				},
				'username' : {
					filters : 'required username',
				}
			}
		};

		var $myform = $('#my-form').idealforms(options).data('idealforms');

		$('#reset').click(function() {
			$myform.reset().fresh().focusFirst()
		});

		$myform.focusFirst();

		function reflashValidImage() {
			document.getElementById("validaNumImage").src = "login_getValidaNumImage.action?index"
					+ Math.random();

		}
	</script>
</body>
</html>